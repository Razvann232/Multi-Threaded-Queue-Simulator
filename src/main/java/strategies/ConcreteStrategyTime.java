package strategies;

import dataTypes.*;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t) {
        int minTime = Integer.MAX_VALUE;
        Server minServ = null;

        for(Server s: servers){
            if(s.getWaitingPeriod().get() < minTime){       // cauta serverul cu cea mai scurta durata de astepare
                minTime = s.getWaitingPeriod().get();
                minServ = s;
            }
        }
        assert minServ != null;
        minServ.addTask(t);          // distribuie clientul si seteaza timpul de finish
        t.setFinishTime(minTime);
    }
}
