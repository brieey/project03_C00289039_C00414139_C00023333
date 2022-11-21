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

    DispatcherThread(int met, LinkedList<TaskThread> RQ, Semaphore RQSem, ArrayList<TaskThread> TL,
            ArrayList<Semaphore> TLSem, int i) {
        this.method = met;
        this.ReadyQueue = RQ;
        this.ReadyQueueSem = RQSem;
        this.TaskList = TL;
        this.TaskListSem = TLSem;
        this.id = i;
    }
    
    public void run(){
        while(TaskList.size()!=0){
            switch(method) {
                case 1://FCFS logic here
                    break;
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
}