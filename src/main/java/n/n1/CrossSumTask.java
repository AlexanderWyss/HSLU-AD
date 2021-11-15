package n.n1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CrossSumTask implements Runnable {
    private static final Logger LOG = LogManager.getLogger(CrossSumTask.class);
    private final int rangeBegin;
    private final int rangeEnd;
    private Thread runThread;
    private volatile boolean isStopped = false;

    CrossSumTask(int rangeBegin, int rangeEnd) {
        this.rangeBegin = rangeBegin;
        this.rangeEnd = rangeEnd;
    }

    public void stopRequest() {
        isStopped = true;
        if(runThread != null) {
            runThread.interrupt();
        }
    }

    private boolean isStopped(){
        return isStopped;
    }

    @Override
    public void run() {
        this.runThread = Thread.currentThread();
        // Initialisierungsphase
        long sum = 0;
        // Arbeitsphase
        for (int i = this.rangeBegin; i <= this.rangeEnd && !isStopped; i++) {
            sum += i;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                isStopped = true;
            }
        }
        // Aufräumphase
        if (!isStopped()) {
            LOG.info(Thread.currentThread().getName() + ": SUM " + rangeBegin + " to " + rangeEnd + " -> " + sum);
        }
        else {
            LOG.info(Thread.currentThread().getName() + ": interrupted.");
        }
    }
}
