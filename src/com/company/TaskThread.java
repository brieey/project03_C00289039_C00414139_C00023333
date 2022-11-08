package com.company;

public class TaskThread  extends Thread{
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
}
