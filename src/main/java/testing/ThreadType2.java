package testing;

public class ThreadType2 implements Runnable{
    private final Object monitor;

    public ThreadType2(Object monitor){
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
