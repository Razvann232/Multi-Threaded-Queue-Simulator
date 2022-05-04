package control;

import dataTypes.*;
import gui.*;
import strategies.SelectionPolicy;
import java.io.*;
import java.util.*;

public class SimulationManager implements Runnable{
    private final Object monitor = new Object();    // monitorul principal pentru toate threadurile

    private final int nrTasks, nrServers, timeLimit, minArrTime, maxArrTime, minProcTime, maxProcTime;  // datele de intrare

    private final File logFile = new File("log.txt");   // pentru scrierea in fisier
    private FileWriter fw = null;
    private final PrintWriter pw;

    private final SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private final Scheduler scheduler;

    private final ArrayList<Task> generatedTasks = new ArrayList<>();

    private final Thread[] serverThreads;

    private int peakTime = 0, peakTasks = 0;    // variabile pentru calculul timpilor medii

    public SimulationManager(String [] inputData){
        try {                                   // pentru scrierea in fisiere
            fw = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pw = new PrintWriter(fw);

        nrTasks = Integer.parseInt(inputData[0]);       // asignarea datelor de intrare
        nrServers = Integer.parseInt(inputData[1]);
        timeLimit = Integer.parseInt(inputData[2]);
        minArrTime = Integer.parseInt(inputData[3]);
        maxArrTime = Integer.parseInt(inputData[4]);
        minProcTime = Integer.parseInt(inputData[5]);
        maxProcTime = Integer.parseInt(inputData[6]);

        generateNRandomTasks();                         // se genereaza clientii aleatoriu

        scheduler = new Scheduler(nrServers, monitor);
        scheduler.changeStrategy(selectionPolicy);

        serverThreads = new Thread[nrServers];
        for(int i=0;i<nrServers;i++){                   // initializam si pornim fiecare thread
            serverThreads[i] = new Thread(scheduler.getServers().get(i), "Q" + i);
            serverThreads[i].start();
        }
    }

    private void generateNRandomTasks(){    // generare aleatorie clienti
        Random random = new Random();
        int arrivTime, procTime, i = 0;
        
        for(int j=0;j<nrTasks;j++){
            arrivTime = random.nextInt(maxArrTime-minArrTime+1)+minArrTime;
            procTime = random.nextInt(maxProcTime-minProcTime+1)+minProcTime;
            generatedTasks.add(new Task(arrivTime, procTime));
        }

        Collections.sort(generatedTasks);
        for(Task t: generatedTasks){        // sortare dupa arrivTime
            t.setTaskID(i);
            i++;
            System.out.print(t.makeString() + " ");
        }
    }

    @Override
    public void run() {
        float avgServTime = 0;  // pentru timpi medii
        int currentTime = 0;

        synchronized (monitor){     // monitorul al carui control il primeste dupa queue-uri
            while(++currentTime<=timeLimit) {
                pw.println("*********************************************");
                pw.print("Time: "+ currentTime + "\nWaiting clients: ");
                System.out.println("Time: " + currentTime);

                for (Task t : generatedTasks) {     // pentru fiecare task la timpul potrivit se distribuie
                    if (t.getArrivTime().get() == currentTime) {
                        avgServTime += t.getProcessTime().get();
                        scheduler.dispatchTask(t);
                    }
                    if(t.getArrivTime().get() > currentTime) pw.print(t.makeString());
                }

                try {
                    monitor.notifyAll();        // notifica fiecare queue ca isi poate continua executia
                    monitor.wait(1000);      // asteapta o secunda(timer principal)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printQueues();
                peakTime(currentTime);  // calculul orei de varf pentru fiecare secunda
            }
        }
        calculateAvg(avgServTime);      // timpi medii
        stopThreads();                  // oprirea threadurilor
    }

    public void stopThreads(){
        pw.close();                         // oprirea threadurilor
        for(Server s: scheduler.getServers()){
            s.setRun(false);
        }
    }

    private void printQueues(){
        int j = 0;                          // printarea listei de asteptare din fiecare queue in ordine
        pw.print("\n");
        for(Server s: scheduler.getServers()){
            pw.print("Q" + j + ": ");
            s.printTasks(pw);
            j++;
        }
    }

    public void peakTime(int currentTime){          // calculul orei de varf
        int curTasks = 0;
        for(Server s: scheduler.getServers()){
            curTasks += s.getNrQueuedTasks();
        }
        if(curTasks>peakTasks){
            peakTasks = curTasks;
            peakTime = currentTime;
        }
    }

    public void calculateAvg(float avgServTime){        // timpii medii
        float avgWaitTime = 0;

        for(Task t: generatedTasks){
            avgWaitTime += t.getFinishTime().get() - t.getArrivTime().get();
        }

        avgServTime /= nrTasks;
        avgWaitTime /= nrTasks;
        pw.println("*********************************************");
        pw.println("Average service time: " + avgServTime);
        pw.println("Average waiting time: " + avgWaitTime);
        pw.println("Peak hour: " + peakTime + " with " + peakTasks + " clients.");
    }

    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new View());
    }
}
