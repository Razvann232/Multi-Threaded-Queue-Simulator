package strategies;

import dataTypes.Server;
import dataTypes.Task;

import java.util.List;

public interface Strategy {
    public void addTask(List<Server> servers, Task t);  //interfata implementata de fiecare strategie
}
