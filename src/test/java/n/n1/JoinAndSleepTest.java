package n.n1;

import org.junit.jupiter.api.Test;

class JoinAndSleepTest {
    @Test
    void test_joinAndSleep() {
        Thread t3 = new Thread(new JoinAndSleep(null, 4000)); // Thread-1
        Thread t2 = new Thread(new JoinAndSleep(t3, 3000)); // Thread-2
        Thread t1 = new Thread(new JoinAndSleep(t2, 2000)); // Thread-3

        t3.setName("Thread 3");
        t2.setName("Thread 2");
        t1.setName("Thread 1");

        t1.start();
        t2.start();
        t3.start();

        // Uncomment to try thread interruption
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();*/
    }
}
