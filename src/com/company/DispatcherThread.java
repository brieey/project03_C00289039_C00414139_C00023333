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

    DispatcherThread(int met, LinkedList<TaskThread> RQ, Semaphore RQSem,ArrayList<TaskThread> TL, ArrayList<Semaphore> TLSem, int i){
        this.method=met;
        this.ReadyQueue=RQ;
        this.ReadyQueueSem=RQSem;
        this.TaskList=TL;
        this.TaskListSem=TLSem;
        this.id=i;
    }


    public void run(){

        switch(method) {
            case 1://FCFS logic here
                //Code changes start Brianna Jordan
                //Add all tasks into the readyQueue
                if (id == 0) {
                    try {ReadyQueueSem.acquire();}
                    catch (InterruptedException e) {throw new RuntimeException(e);}
                    ReadyQueue.addAll(TaskList);
                    ReadyQueueSem.release();
                } else {
                    try {wait(1);}
                    catch (InterruptedException e) {throw new RuntimeException(e);}
                    try {ReadyQueueSem.acquire();}
                    catch (InterruptedException e) {throw new RuntimeException(e);}
                }

                //Executes until there is no more left in the queue
                while(ReadyQueue.size() > 0){
                    if(ReadyQueue.get(id) != null){
                        //Iterates through the queue and executes fully
                        for(int i = 0; i < ReadyQueue.size(); i++){
                            //Go to each task and decrement the burst times FULLY
                            int burstTime = ReadyQueue.get(i).Btime;
                            System.out.println("Thread " + ReadyQueue.get(i).id + "        | Burst Time = " + burstTime + ", Current Burst = 0");
                            for(int j = 0; j < burstTime; j++){
                                System.out.println("Thread " + ReadyQueue.get(i).id + "  | On burst " + j);
                                ReadyQueue.get(i).DecrementBtime();
                            }
                        }

                    }else{
                        break;
                    }
                }
                break;
                //Code Changes End Brianna Jordan
            case 2://RR logic here
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