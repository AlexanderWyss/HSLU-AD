package n.n1;

import org.junit.jupiter.api.Test;

class CrossSumTest {
    @Test
    void test_CalculateCrossSumInMultipleThreads() throws InterruptedException {
        int number = 100;

        Runnable[] tasks = new CrossSumTask[number];
        Thread[] threads = new Thread[number];
        for(int i = 0; i < number; i++) {
            tasks[i] = new CrossSumTask(3*i, 5*i);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        Thread.sleep(1000);

        for(Runnable task : tasks) {
            ((CrossSumTask)task).stopRequest();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
