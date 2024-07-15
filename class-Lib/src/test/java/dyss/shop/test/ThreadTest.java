package dyss.shop.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author dyss
 * @date 2024/7/15 20:24
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ThreadTest {

    @Test
    public void test_start1(){
        new Thread(()->{
            System.out.println("线程启动啦");
        }).start();
    }

    @Test
    public void test_start2(){
        Thread thread = new Thread(() -> {
            System.out.println("线程的new状态");
        });
        //
        thread.run();
        System.out.println(thread.getState());
    }
}
