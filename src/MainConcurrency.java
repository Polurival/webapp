import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by GKislin on 06.11.2015.
 */
public class MainConcurrency {
    static volatile int sum;
    private static final Object LOCK = new Object();
    static final AtomicInteger ATOMIC_SUM = new AtomicInteger();
    static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDown = new CountDownLatch(10000);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 10000; i++) {
            service.submit(
                    (Runnable) () -> {
                        for (int i1 = 0; i1 < 100; i1++) {
                            ATOMIC_SUM.incrementAndGet();

                    synchronized (LOCK) {
                        //fff
                        LOCK.notifyAll();
                        //hhh
                        sum++;
                    }

                            /*
                            REENTRANT_LOCK.lock();
                            sum++;
                            REENTRANT_LOCK.unlock();
*/

                        }
                        countDown.countDown();
                    });
        }
        countDown.await();
//        Thread.sleep(500);
        System.out.println(sum);
        System.out.println(ATOMIC_SUM);
    }

    static void testStatic() {
        synchronized (MainConcurrency.class) {

        }
    }

    void test() {
        synchronized (this) {

        }
    }
}
