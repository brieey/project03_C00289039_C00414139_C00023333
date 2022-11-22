package com.company;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.*;

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
                case 4:
                    Scanner sc = new Scanner(System.in);
                    System.out.println ("Processes #: " );
                    int n = sc.nextInt();
                    int processID[] = new int[n];
                    int at[] = new int[n]; 
                    int bt[] = new int[n]; 
                    int ct[] = new int[n];
                    int ta[] = new int[n];
                    int wt[] = new int[n];
                    int f[] = new int[n]; 
                    int k[]= new int[n];
                    int i, st=0, tot=0;
                    float avgwt = 0, avgta = 0;
                    
                    for (i = 0; i < n; i++) {
                        processID[i] = i + 1;
                        System.out.println("enter process " + (i + 1) + " arrival time:");
                        at[i] = sc.nextInt();
                        System.out.println("enter process " + (i + 1) + " brust time:");
                        bt[i] = sc.nextInt();
                        k[i] = bt[i];
                        f[i] = 0;
                    }
                    boolean a = true;
                    while (true) {
                        int c = n, min = 999;
                        if (tot == n)
                            break;
                        for (i = 0; i < n; i++) {
                            if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                                min = bt[i];
                                c = i;
                            }
                        }
                        if (c == n) {
                            st++;
                        } else {
                            bt[c]--;
                            st++;
                            if (bt[c] == 0) {
                                ct[c] = st;
                                f[c] = 1;
                                tot++;
                            }
                        }
                    }
                    for (i = 0; i < n; i++) {
                        ta[i] = ct[i] - at[i];
                        wt[i] = ta[i] - k[i];
                        avgwt += wt[i];
                        avgta += ta[i];
                    }
                    System.out.println("processID  arrival  burst  complete  turn  waiting");
                    for (i = 0; i < n; i++) {
                        System.out.println(
                                processID[i] + "\t" + at[i] + "\t" + k[i] + "\t" + ct[i] + "\t" + ta[i] + "\t" + wt[i]);
                    }
                    System.out.println ("\naverage tat is "+ (float)(avgta/n));
                    System.out.println ("average wt is "+ (float)(avgwt/n));
                    sc.close();
                    break;
                default:
                    break;
            }
        }
    }
}