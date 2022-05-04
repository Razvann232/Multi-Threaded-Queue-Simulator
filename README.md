# Multi-Threaded-Queue-Simulator
Exemplifying how threads work and interact by solving a given problem in java. A helpful UI is also implemented.

The folowing scenario is simulated:

In a supermarked there are a given number of cash registers. A given number of clients are at that supermarket during the simulation. Each of them will
arrive eventually at a cash register at a specific time after finishing groceries. Some clients may need to spend more time than others depending on how
much it will take to reach their turn at the cash register after they placed themselves in the queue and how much time does it take for the supermarket
employee to scan all the groceries the client has picked up.

A MVC architecture and a strategy pattern were used.

Each of the cash registers is represented by a thread and each client will be placed by the simulation at the cash register with the lowest waiting time.
The threads will work in parallel, each processing the clients waiting in it's queue.

***The main function in the SimulationManager class must be run for the app to work.***
To start the application the following data is needed:
1. The number of clients
2. The number of cash registers (queues)
3. How much will the simulation last
4. The minimum arrival time required for a client to place himself at the queue of a cash register (minimum time required for picking up groceries)
5. The maximum arrival time (similar to the one above)
6. The minimum service time required for scanning the groceries
7. The maximum service time (similar to the one above)

If the data is correct a simulation will commence. The clients will be generated randomly. Each client is represented by a console output of the type (ID, Arr, Serv) where "ID" is a unique identifier, "Arr" is the time they will arrive at the cash register queue, and "Serv" is the service time needed to scan the groceries.

The console will display the simplified simulation in real-time. ***The more important data is found in the "log.txt" file, containing the client at each queue at each given time.*** The service time ("Serv") will decrease as time increases, signifying that the client's groceries are being processed. When it reaches 0, the queue will become empty or the client that is waiting next to him will be processed. Useful data such as the average wait time, average service time(processing groceries at the cash register) and peak hour(time with the most clients waiting in queues) can be found in the same document.

At each time the console will display the first client in the queue, with the (ID, Arr, Serv) format. 

