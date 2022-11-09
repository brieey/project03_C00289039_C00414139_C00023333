package com.company;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.Random;

public class Main {

    public static void main(String[] args) {


        int cores = 0;
        int method = 1; //[1=FCFS, 2=RR, 3=N-SJF, 4=P-SJF]
        int quanta = 5; //default 5 if not specified

        if(args.length>0) {//added catch for no arguments being passed in
            //Checks for the first argument if it's a -S or -C
            if (args[0].equals("-S")) {

                //Checks to see if there are any more arguments
                if (args.length > 1) {

                    //Checks for the second argument. Should be between 1 and 4 for each algorithm
                    if (args[1].equals("1")) { //FCFS Algorithm
                        //Checks to see if there are more arguments. If not, default the core(s) to 1
                        if (args.length > 2) {
                            //Third Argument: -C
                            if (args[2].equals("-C")) {
                                //Checks to see if a fourth argument for the # of cores is present
                                if (args.length > 3) {
                                    //Proceeds to check the next argument for the number of cores, Range 1-4
                                    if (args[3].equals("1") || args[3].equals("2") || args[3].equals("3") || args[3].equals("4")) {
                                        //Sets the number of cores
                                        try {
                                            cores = Integer.parseInt(String.valueOf(args[3]));
                                            System.out.println("Scheduler Algorithm Select: First Come First Serve");
                                            System.out.println("# of cores: " + cores);
                                            method=1;
                                        } catch (NumberFormatException nfe) {
                                            System.out.println("Exception -----> " + nfe);
                                        }
                                    } else {
                                        System.out.println("Invalid number of cores");
                                    }
                                } else {
                                    System.out.println("Invalid number of arguments. No cores selected");
                                }
                            } else {
                                System.out.println("Invalid third argument");
                            }
                        } else {
                            cores = 1;
                            System.out.println("Scheduler Algorithm Select: First Come First Serve");
                            System.out.println("# of cores: " + cores);
                            method=1;
                        }
                    } else if (args[1].equals("2")) { //RR Algorithm
                        //Checks to see if there are more arguments
                        if (args.length > 2) {
                            //Checks to see if the third argument is an integer
                            try {
                                quanta = Integer.parseInt(String.valueOf(args[2]));
                                //Checks for more arguments
                                if (args.length > 3) {
                                    //Checks the fourth argument
                                    if (args[3].equals("-C")) {
                                        //Checks for a valid number of cores
                                        if (args.length > 4) {
                                            //Core range: 1-4
                                            if (args[4].equals("1") || args[4].equals("2") || args[4].equals("3") || args[4].equals("4")) {
                                                //Sets the number of cores & proceeds with algorithm
                                                try {
                                                    cores = Integer.parseInt(String.valueOf(args[4]));
                                                    System.out.println("Scheduler Algorithm Select: Round Robin");
                                                    System.out.println("# of cores: " + cores);
                                                    System.out.println("Time quantum: " + quanta);
                                                    method=2;
                                                } catch (NumberFormatException nfe) {
                                                    System.out.println("Exception -----> " + nfe);
                                                    System.out.println("Invalid core number");
                                                }
                                            } else {
                                                System.out.println("Invalid number of cores");
                                            }
                                        } else {
                                            System.out.println("Invalid number of cores");
                                        }
                                    } else {
                                        System.out.println("Invalid fourth argument");
                                    }
                                } else {
                                    cores = 1;
                                    System.out.println("Scheduler Algorithm Select: Round Robin");
                                    System.out.println("# of cores: " + cores);
                                    System.out.println("Time quantum: " + quanta);
                                    method=2;
                                }
                            } catch (NumberFormatException nfe) {
                                System.out.println(nfe);
                                System.out.println("Not a valid integer");
                            }
                        } else {
                            System.out.println("No time quantum entered");
                        }
                    } else if (args[1].equals("3")) { //NSJF Algorithm
                        //Checks to see if there are more arguments. If not, default the core(s) to 1
                        if (args.length > 2) {
                            //Second argument: -C
                            if (args[2].equals("-C")) {
                                //Checks to see if a fourth argument for the # of cores is present
                                if (args.length > 3) {
                                    //Core range: 1-4
                                    if (args[3].equals("1") || args[3].equals("2") || args[3].equals("3") || args[3].equals("4")) {
                                        //Sets the number of cores
                                        try {
                                            cores = Integer.parseInt(String.valueOf(args[3]));
                                            System.out.println("Scheduler Algorithm Select: Non Preemptive Shortest Job First");
                                            System.out.println("# of cores: " + cores);
                                            method=3;
                                        } catch (NumberFormatException nfe) {
                                            System.out.println("Exception -----> " + nfe);
                                        }
                                    } else {
                                        System.out.println("Invalid number of cores");
                                    }
                                } else {
                                    System.out.println("Invalid number of arguments. No cores selected");
                                }
                            } else {
                                System.out.println("Invalid third argument");
                            }
                        } else {
                            cores = 1;
                            System.out.println("Scheduler Algorithm Select: Non Preemptive Shortest Job First");
                            System.out.println("# of cores: " + cores);
                            method=3;
                        }
                    } else if (args[1].equals("4")) { //PSJF Algorithm
                        //Checks to see if there are more arguments. If not, default the core(s) to 1
                        if (args.length > 2) {
                            //Third Argument: -C
                            if (args[2].equals("-C")) {
                                //Check to see if the # of cores is present
                                if (args.length > 3) {
                                    //Proceeds to check the next argument for the number of cores, Range 1-4
                                    if (args[3].equals("1")) {
                                        //Sets the number of cores
                                        try {
                                            cores = Integer.parseInt(String.valueOf(args[3]));
                                            System.out.println("Scheduler Algorithm Select: Preemptive Shortest Job First");
                                            System.out.println("# of cores: " + cores);
                                            method=4;
                                        } catch (NumberFormatException nfe) {
                                            System.out.println("Exception -----> " + nfe);
                                        }
                                    } else {
                                        System.out.println("Only single cores for this algorithm! Invalid input");
                                    }
                                } else {
                                    System.out.println("Invalid number of arguments. No cores selected");
                                }
                            } else {
                                System.out.println("Invalid third argument");
                            }
                        } else {
                            cores = 1;
                            System.out.println("Scheduler Algorithm Select: Preemptive Shortest Job First");
                            System.out.println("# of cores: " + cores);
                            method=4;
                        }
                    } else {
                        System.out.println("Invalid task selection");
                    }
                } else {
                    System.out.println("No scheduler task selected");
                }
            } else if (args[0].equals("-C")) {
                if (args.length > 1) {
                    //Check for the range 1-4
                    if (args[1].equals("1") || args[1].equals("2") || args[1].equals("3") || args[1].equals("4")) {
                        try {
                            cores = Integer.parseInt(String.valueOf(args[1]));
                            //Check for more arguments
                            if (args.length > 2) {
                                //Check for the third argument: -S
                                if (args[2].equals("-S")) {
                                    //Checks for more arguments
                                    if (args.length > 3) {
                                        //Checks to see which algorithm is being selected
                                        if (args[3].equals("1")) { //FCFS Algorithm
                                            System.out.println("Scheduler Algorithm Select: First Come First Serve");
                                            System.out.println("# of cores: " + cores);
                                            method=1;
                                        } else if (args[3].equals("2")) { // RR Algorithm
                                            //Check if time quantum exists
                                            if (args.length > 4) {
                                                //Check the 5th argument to see if its a valid integer
                                                try {
                                                    quanta = Integer.parseInt(String.valueOf(args[4]));
                                                    System.out.println("Scheduler Algorithm Select: Round Robin");
                                                    System.out.println("# of cores: " + cores);
                                                    System.out.println("Time quantum: " + quanta);
                                                    method=2;
                                                } catch (NumberFormatException nfe) {
                                                    System.out.println("Exception -----> " + nfe);
                                                }
                                            } else {
                                                System.out.println("Invalid fifth argument");
                                            }
                                        } else if (args[3].equals("3")) { //NSJF Algorithm
                                            System.out.println("Scheduler Algorithm Select: Non Preemptive Shortest Job First");
                                            System.out.println("# of cores: " + cores);
                                            method=3;
                                        } else if (args[3].equals("4")) { //PSJF
                                            //Should only allow for 1 core, no multicore
                                            if (args[1].equals("1")) {
                                                System.out.println("Scheduler Algorithm Select: Preemptive Shortest Job First");
                                                System.out.println("# of cores: " + cores);
                                                method=4;
                                            } else {
                                                System.out.println("Only single cores for this algorithm!");
                                            }
                                        } else {
                                            System.out.println("Invalid task selection");
                                        }
                                    } else {
                                        System.out.println("No task selected");
                                    }
                                } else {
                                    System.out.println("Invalid third argument");
                                }
                            } else {
                                System.out.println("No scheduler selected");
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Exception -----> " + nfe);
                        }
                    } else {
                        System.out.println("Invalid number of cores");
                    }
                } else {
                    System.out.println("No cores selected");
                }
            } else {
                System.out.println("Invalid first argument");
            }

            //launch threads and join them here

            //variables
            //the ready queue itself, empty until dispatcher threads add to it
            LinkedList<TaskThread> ReadyQueue=new LinkedList<TaskThread>();
            //a list of references to all the task threads
            ArrayList<TaskThread> TaskList=new ArrayList<TaskThread>();
            //a list of references to all the dispatcher threads
            ArrayList<DispatcherThread> DispatcherList= new ArrayList<DispatcherThread>();
            //only 1 dispatcher can access the ready queue at a time
            Semaphore ReadySem=new Semaphore(1);
            ArrayList<Semaphore> TaskSem=new ArrayList<Semaphore>();
            Random rand = new Random();
            int T=rand.nextInt(24)+1;//replace with 5 for the task 1 report
            int[] RandBtime={18, 7, 25, 42, 21};

            //launching Tasks
            for (int i = 0; i < T; i++) {
                TaskThread thread = new TaskThread(RandBtime[rand.nextInt(4)], i);
                thread.setName("Task: " + String.valueOf(i) + "(D" + (i + 1) + ")");
                TaskList.add(i, thread);
                thread.start();
                TaskSem.add(new Semaphore(1));
            }

            //launching Dispatchers
            for (int i = 0; i < cores; i++) {
                DispatcherThread thread = new DispatcherThread(method,ReadyQueue,ReadySem,TaskList,TaskSem, i);
                thread.setName("Dispatcher: " + String.valueOf(i) + "(D" + (i + 1) + ")");
                DispatcherList.add(i, thread);
                thread.start();
            }

            //join Tasks [might work better to move into the dispatcher thread dependant on how the logic develops
            for (int i = 0; i < T; i++) {
                try{//join needs an exception catch in java, interesting.
                    TaskList.get(i).join();
                }catch(InterruptedException e){
                    System.out.println("Exception -> " + e);
                }
            }

            //join Dispatchers
            for (int i = 0; i < cores; i++) {
                try{//join needs an exception catch in java, interesting.
                    DispatcherList.get(i).join();
                }catch(InterruptedException e){
                    System.out.println("Exception -> " + e);
                }
            }

        } else {System.out.println("No arguments passed in");}
    }
}