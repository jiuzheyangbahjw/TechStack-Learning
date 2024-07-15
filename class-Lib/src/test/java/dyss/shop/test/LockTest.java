package dyss.shop.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dyss
 * @date 2024/7/14 11:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LockTest {
    @Test
    public void test_lock1(){
        ReentrantLock lock = new ReentrantLock(true);
        lock.lock();
        try {
            System.out.println("锁住了");
        }finally {
            System.out.println("解锁了");
            lock.unlock();
        }
    }

    @Test
    public void test_CountDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            exec.execute(() -> {
                try {
                    int millis = new Random().nextInt(10000);
                    System.out.println("等待游客上船，耗时：" + millis + "(millis)");
                    Thread.sleep(millis);
                    System.out.println("线程:"+ finalI +"睡眠完毕");
                } catch (Exception ignore) {
                } finally {
                    latch.countDown(); // 完事一个扣减一个名额
                }
            });
        }
        // 等待游客
        latch.await();
        System.out.println("船长急躁了，开船!");
        // 关闭线程池
        exec.shutdown();
    }
}
