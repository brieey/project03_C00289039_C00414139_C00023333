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
        while(TaskList.size()!=0){
            switch(method) {
                case 1://FCFS logic here
                    break;
                case 2://RR logic here By Daniel Duthu
                    //need to populate the ready queue with the tasks form the task thread array
                    //once populated i need to have a loop that decrements the Btime of front most task
                        //in that loop i need a way to break out of the loop incase the task hits 0 before the quanta is up
                    //once a task is out of the loop i need to place it at the end of the ready queue and move onto the next task
                      //ReadyQueueSem.aquire();
                      //ReadyQueue.append(ReadyQueue.pop(0));//should pop off first element and append it to the end.
                      //ReadyQueueSem.release();
                        //but that's only if Btime of that task != 0. if it is 0 then i need to simply remove the task from the ready queue
                          //ReadyQueueSem.aquire();
                          //ReadyQueue.pop(0);//should pop off first element
                          //ReadyQueueSem.release();
                    //at that point repeat, no boolean needed for flaging that the current task is done as i cna jsut use the gteBtime method each task has and compare it to >0
                    

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
}