package testing;

public class ThreadType1 implements Runnable{
    private int i;

    private final Object monitor;

    public ThreadType1(Object monitor){
        this.monitor = monitor;
    }

    public void run(){
        synchronized (monitor){
            for(int it=0;it<100;it++){
                System.out.println(Thread.currentThread().getName() + " " + it);
                try {
                    monitor.notify();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
