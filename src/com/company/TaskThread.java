package com.company;

public class TaskThread extends Thread implements Comparable<TaskThread>{
    //made by Daniel Duthu
    int Btime;
    int id;

    TaskThread(int BurstTime, int IDnum){
        this.Btime=BurstTime;
        this.id=IDnum;

    }
    //get method for Btime
    public int getBtime(){return Btime;}
    //decrement method to reduce Btime by 1 and stopping at 0
    public void DecrementBtime(){if(Btime>0){Btime-=1;}}
    //get method for id
    public int getTid(){return id;}

    @Override
    public int compareTo(TaskThread other) { return this.Btime - other.getBtime(); }
    //[-1 if other > this, 0 if other = this, 1 if other < this]
    //can be sorted by Collections.sort(ArrayList<TaskThreads>);

    public void run(){
        while(Btime>0) {
            //this just keeps the task open until it'd done its Btime
        }
    }
}
