package com.company;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class DispatcherThread extends Thread{
    //framework made by Daniel Duthu
    int method;
    LinkedList<TaskThread> ReadyQueue;
    Semaphore ReadyQueueSem;
    ArrayList<TaskThread> TaskList;
    ArrayList<Semaphore> TaskListSem;
    int id;
    int quanta;

    DispatcherThread(int met, LinkedList<TaskThread> RQ, Semaphore RQSem,ArrayList<TaskThread> TL, ArrayList<Semaphore> TLSem, int i, int Q){
        this.method=met;
        this.ReadyQueue=RQ;
        this.ReadyQueueSem=RQSem;
        this.TaskList=TL;
        this.TaskListSem=TLSem;
        this.id=i;
        this.quanta=Q;
    }
    public void run(){
                switch (method) {
                    case 1://FCFS logic here
                        break;
                    case 2://RR logic here By Daniel Duthu
                  /*need to populate the ready queue with the tasks form the task thread array
                    once populated I need to have a loop that decrements the Btime of front most task
                        in that loop I need a way to break out of the loop incase the task hits 0 before the quanta is up
                    once a task is out of the loop I need to place it at the end of the ready queue and move onto the next task
                      -try{ReadyQueueSem.acquire();}
                      -ReadyQueue.append(ReadyQueue.pop(0));//should pop off first element and append it to the end.
                      -ReadyQueueSem.release();
                        but that's only if Btime of that task != 0. if it is 0 then I need to simply remove the task from the ready queue
                          -try{ReadyQueueSem.acquire();}
                          -ReadyQueue.pop(0);//should pop off first element
                          -ReadyQueueSem.release();
                    at that point repeat, no boolean needed for flaging that the current task is done as i cna just use the getBtime method each task has and compare it to >0
                    */

                        TaskThread temp;
                        //populate the ready queue. this is done 'as they arrive' with me assuming they arrive at effectively the same time and just add the Task threads into the ReadyQueue 1:1.
                        boolean firstRun = true;
                        if (id == 0 && firstRun) {
                            try {
                                ReadyQueueSem.acquire();
                                ReadyQueue.addAll(TaskList);
                                ReadyQueueSem.release();
                                firstRun = false;
                                System.out.println(ReadyQueue);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            //ReadyQueue.addAll(TaskList);
                            //ReadyQueueSem.release();
                        } else {
                            try {
                                wait(80);
                            }//to ensure that the first dispatcher can get the semaphore first
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            try {
                                ReadyQueueSem.acquire();
                                ReadyQueueSem.release();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            //ReadyQueueSem.release();
                        }

                        //keep this running until the ReadyQueue is empty, this being the Round Robin scheduling procedure.
                        while (ReadyQueue.size() > 0) {
                            //get the task off the top of the ReadyQueue
                            //temp=ReadyQueue.pop();//adding this got rig of alot of .get()'s.

                            //the idea behind this is that if there are fewer tasks than dispatchers, tasks can only be on one
                            //core at a time, you can just have the tasks go onto specific cores within the bounds of RR.
                            //this means that for 3 tasks remaining you can effectively not have the 4th core running as the
                            //last 3 tasks can get full attention on the first 3 cores.
                            if (ReadyQueue.get(id) != null) {
                                try {
                                    ReadyQueueSem.acquire();
                                    temp = ReadyQueue.get(id);
                                    ReadyQueue.remove(id);
                                    ReadyQueueSem.release();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                //temp = ReadyQueue.get(id);
                                //ReadyQueue.remove(id);
                                //ReadyQueueSem.release();

                                for (int i = 0; i < quanta; i++) {
                                    temp.DecrementBtime();

                                    //this catches the case where the task is done before the quanta is reached to end the quanta loop early
                                    if (temp.getBtime() < 1) {
                                        i = quanta;
                                    }
                                }
                                //place task at end of ReadyQueue as it still has some Btime left ot process
                                if (temp.getBtime() > 0) {
                                    try {
                                        ReadyQueueSem.acquire();
                                        ReadyQueue.add(temp);//should pop off first element and append it to the end.
                                        ReadyQueueSem.release();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    //ReadyQueue.add(temp);//should pop off first element and append it to the end.
                                    //ReadyQueueSem.release();
                                }
                                //remove the task from the ReadyQueue all together as it is done its task completely now
                                else {
                                    try {
                                        ReadyQueueSem.acquire();
                                        ReadyQueueSem.release();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    //ReadyQueueSem.release();
                                }
                            } else {
                                //this goes back to the statements I made of tasks remaining < # of cores leading to
                                //dedication so the dispatchers can shut down early instead of running into nulls
                                break;
                            }
                        }
                        break;
                    case 3://N-SJF logic here
                        break;
                    case 4://P-SJF logic here
                        break;
                    default:
                        break;
                }
    }
}