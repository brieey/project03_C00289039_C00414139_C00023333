package com.company;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
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
    public void run() {
        switch (method) {
            case 1://FCFS logic here by Brianna Jordan
                //Add tasks to the ready queue
                if(id == 0){

                    try{
                        ReadyQueueSem.acquire();
                    } catch(InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    ReadyQueue.addAll(TaskList);
                    ReadyQueueSem.release();

                }else{
                    try{
                        wait(1);
                    }catch(InterruptedException e){
                        throw new RuntimeException(e);
                    }
                    try{
                        ReadyQueueSem.acquire();
                    }catch(InterruptedException e){
                        throw new RuntimeException(e);
                    }
                }

                //Executes until there are no more tasks in the queue
                while(ReadyQueue.size() > 0){
                    if(ReadyQueue.get(id) != null){
                        //Iterates through the queue and executes fully
                        for(int i = 0; i < ReadyQueue.size(); i++){
                            //Go to each task and decrement the burst time fully
                            int burstTime = ReadyQueue.get(i).Btime;
                            System.out.println("Thread " + ReadyQueue.get(i).id + "        | Burst Time = " + burstTime + ", Current Burst = 0");
                            for(int j = 0; j < burstTime; j++){
                                System.out.println("Proc. Thread " + ReadyQueue.get(i).id + "  | On burst " + j);
                                ReadyQueue.get(i).DecrementBtime();
                            }
                        }
                    }else{
                        break;
                    }

                }
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
                    } catch (InterruptedException e) {throw new RuntimeException(e);}
                    finally {System.out.println(this.getName() + "1");}//test line
                    //ReadyQueue.addAll(TaskList);
                    //ReadyQueueSem.release();
                } else {
                    //try {wait(80);}//to ensure that the first dispatcher can get the semaphore first
                    //catch (InterruptedException e) {throw new RuntimeException(e);}
                    //finally {System.out.println(this.getName()+"2");}//test line
                    try {
                        ReadyQueueSem.acquire();
                        ReadyQueueSem.release();
                    } catch (InterruptedException e) {throw new RuntimeException(e);}
                    //finally {System.out.println(this.getName()+"3");}//test line
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
                        } catch (InterruptedException e) {throw new RuntimeException(e);}
                        finally {System.out.println(this.getName() + "4");}//test line
                        //temp = ReadyQueue.get(id);
                        //ReadyQueue.remove(id);
                        //ReadyQueueSem.release();

                        for (int i = 0; i < quanta; i++) {
                            temp.DecrementBtime();

                            //this catches the case where the task is done before the quanta is reached to end the quanta loop early
                            if (temp.getBtime() < 1) {i = quanta;}
                        }
                        //place task at end of ReadyQueue as it still has some Btime left ot process, otherwise don't add it back in
                        if (temp.getBtime() > 0) {
                            try {
                                ReadyQueueSem.acquire();
                                ReadyQueue.add(temp);
                                ReadyQueueSem.release();
                            } catch (InterruptedException e) {throw new RuntimeException(e);}
                            finally {System.out.println(this.getName() + "5");}//test line
                            //ReadyQueue.add(temp);
                            //ReadyQueueSem.release();
                        }
                        if(!ReadyQueue.isEmpty()){System.out.println(ReadyQueue);}//test line
                    } else {
                        System.out.println(this.getName() + "less than 4 tasks");
                        break;
                        //this goes back to the statements I made of tasks remaining < # of cores leading to
                        //dedication so the dispatchers can shut down early instead of running into nulls
                    }
                }
                System.out.println(this.getName() + " is done");System.out.println(ReadyQueue);//test line
                break;
            case 3://Non-preemptive Shortest Job First by Alexandre' Davis
                Scanner scan1 = new Scanner(System.in);
                System.out.println ("enter no of process:");
                int n = scan1.nextInt();
                int processID[] = new int[n];
                int arrivalTime[] = new int[n];
                int burstTime[] = new int[n];
                int ct[] = new int[n];
                int ta[] = new int[n];
                int waitTime[] = new int[n];
                int f[] = new int[n];
                int st = 0;
                int total = 0;
                float avgWeight = 0;
                float avgturnTime = 0;
                for (int i = 0; i < n; i++) {
                    System.out.println("Enter process" + (i + 1) + " arrival time:");
                    arrivalTime[i] = scan1.nextInt();
                    System.out.println("Enter process" + (i + 1) + " burst time:");
                    burstTime[i] = scan1.nextInt();
                    processID[i] = i + 1;
                    f[i] = 0;
                }
                boolean a = true;
                while (true) {
                    int c = n;
                    int min = 999;
                    if (total == n)
                        break;
                    for (int i = 0; i < n; i++) {
                        if ((arrivalTime[i] <= st) && (f[i] == 0) && (burstTime[i] < min)) {
                            min = burstTime[i];
                            c = i;
                        }
                    }
                    if (c == n) {
                        st++;
                    } else {
                        ct[c] = st + burstTime[c];
                        st += burstTime[c];
                        ta[c] = ct[c] - arrivalTime[c];
                        waitTime[c] = ta[c] - burstTime[c];
                        f[c] = 1;
                        total++;
                    }
                }
                System.out.println("\nprocessID  arrival burst  complete turn waiting");
                for (int i = 0; i < n; i++) {
                    avgWeight += waitTime[i];
                    avgturnTime += ta[i];
                    System.out.println(processID[i]+"\t"+arrivalTime[i]+"\t"+burstTime[i]+"\t"+ct[i]+"\t"+ta[i]+"\t"+waitTime[i]);
                    System.out.println ("\naverage tat is "+ (float)(avgturnTime/n));
                    System.out.println ("average wt is "+ (float)(avgWeight/n));
                    scan1.close();
                }
                break;
            case 4://P-SJF logic here by Alexandre' Davis
                Scanner scan2  = new Scanner(System.in);
                System.out.println ("enter no of process:");
                int x = scan2.nextInt();
                int preemptiveID[] = new int[x];
                int arrival[] = new int[x];
                int burst[] = new int[x];
                int complete[] = new int[x];
                int turnaround[] = new int[x];
                int wait[] = new int[x];
                int v[] = new int[x];
                int k[]= new int[x];
                int i, value=0, tot=0;
                float avgwait = 0, avgturnaround = 0;
                for (int z = 0; z < x; z++) {
                    preemptiveID[z] = z + 1;
                    System.out.println("Enter process" + (z + 1) + " arrival time:");
                    arrival[z] = scan2.nextInt();
                    System.out.println("Enter process" + (z + 1) + " burst time:");
                    burst[z] = scan2.nextInt();
                    k[z] = burst[z];
                    v[z] = 0;
                }
                while (true) {
                    int min = 99;
                    int c = x;
                    if (tot == x)
                        break;
                    for (int z = 0; z < x; z++) {
                        if ((arrival[z] <= value) && (v[z] == 0) && (burst[z] < min)) {
                            min = burst[z];
                            c = z;
                        }
                    }
                    
                    if (c == x)
                        value++;
                    else {
                        burst[c]--;
                        value++;
                        if (burst[c]==0)
                        {
                            complete[c] = value;
                            v[c] = 1;
                            tot++;
                        }
                        for (int j = 0; j < x; j++) {
                            turnaround[j] = complete[j] - arrival[j];
                            wait[j] = turnaround[j] - k[j];
                            avgwait += wait[j];
                            avgturnaround += turnaround[j];
                        }
                        System.out.println("processID  arrival  burst  complete turn waiting");
                        for (int j = 0; j < x; j++) {
                            System.out.println(preemptiveID[j] +"\t"+ arrival[j]+"\t"+ k[j] +"\t"+ complete[j] +"\t"+ turnaround[j] +"\t"+ wait[j]);
                            System.out.println("\naverage tat is "+ (float)(avgturnaround/x));
                            System.out.println("average wt is "+ (float)(avgwait/x));
                            scan2.close();
                        }
                    }
                }
                break;
            default:
                break;
        }
    }
}