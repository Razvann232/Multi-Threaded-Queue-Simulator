package control;

import dataTypes.*;
import strategies.*;

import java.util.*;

public class Scheduler {
    private final ArrayList<Server> servers;        // lista de Servere, numarul dat de servere si strategia
    private final int maxServers;
    private Strategy strategy;

    public Scheduler(int maxServers , Object monitor){
        this.maxServers = maxServers;
        servers = new ArrayList<>(maxServers);

        for(int i=0;i<maxServers;i++){  // se initializeaza lista de servere
            servers.add(new Server(monitor));
        }
    }

    protected void changeStrategy(SelectionPolicy policy){  // se alege strategia de distribuire
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }

        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }

    protected void dispatchTask(Task t){
        strategy.addTask(servers,t);
    }   // distribuie un task

    public List<Server> getServers(){
        return servers;
    }
}
