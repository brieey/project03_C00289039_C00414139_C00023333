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
                case 3:
                    Scanner sc = new Scanner(System.in);
                    System.out.println ("Processes #: " );
                    int n = sc.nextInt();
                    int processID[] = new int[n];
                    int at[] = new int[n]; // at means arrival time
                    int bt[] = new int[n]; // bt means burst time
                    int ct[] = new int[n]; // ct means complete time
                    int ta[] = new int[n]; // ta means turn around time
                    int wt[] = new int[n];  //wt means waiting time
                    int f[] = new int[n];  // f means it is flag it checks process is completed or not
                    int st=0, tot=0;
                    float avgwt = 0, avgta = 0;
                    
                    for (int i = 0; i < n; i++) {
                        System.out.println("enter process " + (i + 1) + " arrival time:");
                        at[i] = sc.nextInt();
                        System.out.println("enter process " + (i + 1) + " brust time:");
                        bt[i] = sc.nextInt();
                        processID[i] = i + 1;
                        f[i] = 0;
                    }
                    boolean a = true;
                    while (true) {
                        int c = n, min = 999;
                        if (tot == n)
                            break;
                        for (int i = 0; i < n; i++) {
                            if ((at[i] <= st) && (f[i] == 0) && (bt[i] < min)) {
                                min = bt[i];
                                c = i;
                            }
                        }
                        if (c == n) {
                            st++;
                        } else {
                            ct[c] = st + bt[c];
                            st += bt[c];
                            ta[c] = ct[c] - at[c];
                            wt[c] = ta[c] - bt[c];
                            f[c] = 1;
                            tot++;
                        }
                    }
                    System.out.println("\nprocessID  arrival burst  complete turn waiting");
                    for (int i = 0; i < n; i++) {
                        avgwt += wt[i];
                        avgta += ta[i];
                        System.out.println(processID[i]+"\t"+at[i]+"\t"+bt[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+wt[i]);
                    }
                    System.out.println ("\naverage tat is "+ (float)(avgta/n));
                    System.out.println ("average wt is "+ (float)(avgwt/n));
                    sc.close();
                    break;
                case 4://P-SJF logic here
                    break;
                default:
                    break;
            }
        }
    }
}