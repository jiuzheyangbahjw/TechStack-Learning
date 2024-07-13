package dyss.shop.test;

import com.alibaba.fastjson.JSON;
import dyss.shop.RocketMQ.BaseEvent;
import dyss.shop.RocketMQ.EventPublisher;
import dyss.shop.RocketMQ.UserBuyEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author dyss
 * @date 2024/7/13 21:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RocketMQTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Resource
    private EventPublisher eventPublisher;

    @Test
    public void MQSend_Test(){
        rocketMQTemplate.convertAndSend("DYSS-TOPIC","我是消息");
        new CountDownLatch(1);
    }

    @Test
    public void MQSend2_Test(){
        eventPublisher.publish(UserBuyEvent.TOPIC, new UserBuyEvent.userMessage());
    }
}
