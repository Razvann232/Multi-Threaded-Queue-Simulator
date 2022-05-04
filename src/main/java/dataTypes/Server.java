package dataTypes;

import java.io.PrintWriter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final Object monitor;               // monitorul principal
    private boolean doRun = true;               // daca poate rula
    private final BlockingQueue<Task> tasks;    // lista de taskuri
    private final AtomicInteger waitingPeriod = new AtomicInteger(0);   // timpul curent de asteptare

    public Server(Object monitor) {
        this.monitor = monitor;
        tasks = new LinkedBlockingQueue<>();
    }

    public void addTask(Task newTask) {
        try {
            tasks.put(newTask);
            waitingPeriod.getAndAdd(newTask.getProcessTime().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setRun(boolean b) {
        doRun = b;
    }

    @Override
    public void run() {
        Task t = null;

        synchronized (monitor) {
            while (doRun) {
                if ((t = tasks.peek()) == null){        // se ia cate un task din lista(fara a fi eliminat)
                    try{ monitor.wait();}
                    catch(InterruptedException e) { e.printStackTrace();}
                }
                else {
                    while (t.getProcessTime().get() != 0) {
                        System.out.printf("%s: %s\n", Thread.currentThread().getName(), t.makeString());

                        try {
                            monitor.wait();     // asteapta sa primeasca iara controlul de la manager
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        t.getProcessTime().decrementAndGet();
                        waitingPeriod.decrementAndGet();
                    }
                    tasks.poll();   // se elimina task-ul din lista
                }
            }
        }
    }

    public BlockingQueue<Task> getTasks(){
        return tasks;
    }

    public AtomicInteger getWaitingPeriod(){
        return waitingPeriod;
    }

    public int getNrQueuedTasks() {
        return tasks.size();
    }

    public void printTasks(PrintWriter pw){ // functie de printare a taskurilor
        for (Task t1 : tasks) {
            pw.print(t1.makeString() + " ");
        }
        pw.print("\n");
    }
}
