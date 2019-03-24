package ThreadTest;

public class RunnableTest {
    public static void main(String[] args) {
        RunnableDemo r1 = new RunnableDemo("thread-1");
        r1.start();

        RunnableDemo r2 = new RunnableDemo("thread-2");
        r2.start();
    }
}

class RunnableDemo extends Thread {
    private Thread t;
    private String threadName;

    public RunnableDemo(String name) {
        threadName = name;
    }

    public void run() {
        System.out.println("running " + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " + threadName + " interrupted");
        }
        System.out.println("Thread " + threadName + " exiting");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}