package n.n2;

class DemoWaitPool {
    private static final Object lock = new Object();
    public static void main(final String args[]) throws InterruptedException {
        final DemoTask waiter = new DemoTask(lock);
        new Thread(waiter).start();
        Thread.sleep(1000);
        synchronized (lock) {
            lock.notify();
        }
    }
}
