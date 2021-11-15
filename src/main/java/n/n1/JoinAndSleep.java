package n.n1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JoinAndSleep implements Runnable {
    private static final Logger logger = LogManager.getLogger("JoinAndSleep");

    private Thread waitFor;
    private int sleepTime;

    JoinAndSleep(Thread waitFor, int sleepTime) {
        this.waitFor = waitFor;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        if (waitFor != null) {
            logger.info(Thread.currentThread().getName() + " will now wait for " + waitFor.getName());

            try {
                waitFor.join();
            } catch (InterruptedException e) {
                logger.info(waitFor.getName() + " interrupted");
            }
        }
        else {
            logger.info(Thread.currentThread().getName() + " has no thread to wait for");
        }

        logger.info(Thread.currentThread().getName() + " sleeping for " + sleepTime);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            logger.info(waitFor.getName() + " interrupted");
        }

        logger.info(Thread.currentThread().getName() + " ended");
    }
}
