package com.company;

public class Main {

    public static void main(String[] args) {


        int cores = 0;

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
                                        //INSERT CODE FOR FCFS ALGORITHM------------------------------------------------------------------------------
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
                        //INSERT CODE FOR FCFS ALGORITHM-----------------------------------------------------------------------------------------------
                    }
                } else if (args[1].equals("2")) { //RR Algorithm

                    int timeQuantum;

                    //Checks to see if there are more arguments
                    if (args.length > 2) {
                        //Checks to see if the third argument is an integer
                        try {
                            timeQuantum = Integer.parseInt(String.valueOf(args[2]));
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
                                                System.out.println("Time quantum: " + timeQuantum);
                                                //INSERT CODE FOR RR ALGORITHM------------------------------------------------------------------------
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
                                System.out.println("Time quantum: " + timeQuantum);
                                //INSERT CODE FOR RR ALGORITHM-----------------------------------------------------------------------------------------
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
                                        //INSERT CODE FOR NSJF ALGORITHM------------------------------------------------------------------------------
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
                        //INSERT CODE FOR NSJF ALGORITHM-----------------------------------------------------------------------------------------------
                    }
                } else if (args[1].equals("4")) { //SJF Algorithm
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
                                        System.out.println("Scheduler Algorithm Select: Shortest Job First");
                                        System.out.println("# of cores: " + cores);
                                        //INSERT CODE FOR SJF ALGORITHM--------------------------------------------------------------------------------
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
                        System.out.println("Scheduler Algorithm Select: Shortest Job First");
                        System.out.println("# of cores: " + cores);
                        //INSERT CODE FOR SJF ALGORITHM-----------------------------------------------------------------------------------------------
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
                                        //INSERT CODE FOR FCFS ALGORITHM-------------------------------------------------------------------------------
                                    } else if (args[3].equals("2")) { // RR Algorithm
                                        //Check if time quantum exists
                                        if(args.length > 4){
                                            //Check the 5th argument to see if its a valid integer
                                            try{
                                                int timeQuantum = Integer.parseInt(String.valueOf(args[4]));
                                                System.out.println("Scheduler Algorithm Select: Round Robin");
                                                System.out.println("# of cores: " + cores);
                                                System.out.println("Time quantum: " + timeQuantum);
                                                //INSERT CODE FOR RR ALGORITHM------------------------------------------------------------------------
                                            }catch(NumberFormatException nfe){
                                                System.out.println("Exception -----> " + nfe);
                                            }
                                        }else{
                                            System.out.println("Invalid fifth argument");
                                        }
                                    } else if (args[3].equals("3")) { //NSJF Algorithm
                                        System.out.println("Scheduler Algorithm Select: Non Preemptive Shortest Job First");
                                        System.out.println("# of cores: " + cores);
                                        //INSERT CODE FOR NSJF ALGORITHM-------------------------------------------------------------------------------
                                    } else if (args[3].equals("4")) { //SJF
                                        //Should only allow for 1 core, no multicore
                                        if(args[1].equals("1")){
                                            System.out.println("Scheduler Algorithm Select: Shortest Job First");
                                            System.out.println("# of cores: " + cores);
                                            //INSERT CODE FOR SJF ALGORITHM--------------------------------------------------------------------------------
                                        }else{
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


    }


}

