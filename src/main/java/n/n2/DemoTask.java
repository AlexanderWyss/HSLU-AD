package n.n2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class DemoTask implements Runnable {
    private static final Logger LOG = LogManager.getLogger(DemoTask.class);
    private final Object lock;
    DemoTask(final Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        LOG.info("warten...");
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException ex) {
                return;
            }
        }
        LOG.info("...aufgewacht");
    }
}
