package dataTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Task implements Comparable<Task>{
    private final AtomicInteger taskID = new AtomicInteger();               // variabilele fiecarui client
    private final AtomicInteger arrivTime = new AtomicInteger(0);
    private final AtomicInteger processTime = new AtomicInteger(0);
    private final AtomicInteger finishTime = new AtomicInteger(0);

    public Task(int arrivTime, int processTime){
        this.arrivTime.set(arrivTime);
        this.processTime.set(processTime);
    }

    @Override
    public int compareTo(Task o) {
        return this.arrivTime.get()-o.arrivTime.get();
    }

    public AtomicInteger getArrivTime(){
        return arrivTime;
    }

    public AtomicInteger getProcessTime(){
        return processTime;
    }

    public AtomicInteger getFinishTime(){
        return finishTime;
    }

    public void setFinishTime(int waitingPeriod){
        finishTime.set(arrivTime.get() + processTime.get() + waitingPeriod); // cand pleaca clientul din sistem
    }

    public void setTaskID(int nr){
        taskID.set(nr);
    }

    public String makeString(){
        return "(" + taskID.get() + ", " + arrivTime.get() + ", " + processTime.get() + ")";
    }

}
