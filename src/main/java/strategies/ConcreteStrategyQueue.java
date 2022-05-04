package strategies;

import dataTypes.Server;
import dataTypes.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        int minQueueSize = Integer.MAX_VALUE;
        Server minServ = null;

        for(Server s: servers){
            if(s.getNrQueuedTasks() < minQueueSize){    // cauta serverul cu cei mai putini clienti
                minQueueSize = s.getNrQueuedTasks();
                minServ = s;
            }
        }
        assert minServ != null;
        minServ.addTask(t);                             // distribuie clientul si calculeaza timpul de finish
        t.setFinishTime(minServ.getWaitingPeriod().get());
    }
}
