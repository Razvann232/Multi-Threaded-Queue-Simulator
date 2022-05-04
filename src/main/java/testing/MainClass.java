package testing;

public class MainClass {
    public static void main(String[] args) {
        Object monitor = new Object();
        Object monitor2 = new Object();

        ThreadType1 ty1 =  new ThreadType1(monitor);
        Thread T1 = new Thread(ty1, "T1");

        ThreadType2 ty2 =  new ThreadType2(monitor);
        Thread T2 = new Thread(ty2, "T2");

        T1.start();
        T2.start();
    }
}
