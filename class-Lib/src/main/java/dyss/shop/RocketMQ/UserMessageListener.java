package dyss.shop.RocketMQ;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author dyss
 * @date 2024/7/14 9:35

 @Description 必读:这里是监听者类，MQ推送消息后，监听特定主题的监听者会收到信息
                consumerGroup在MQ面板进行指定


 */
//@Component
@Slf4j
@RocketMQMessageListener(topic = "DYSS-MQ",consumerGroup = "DYSS-Group")
public class UserMessageListener implements RocketMQListener<String> {
    /**
     * 一旦该监听者接收到对应主题的消息，则会进行消费，获得其中的内容
     * 该方法进行接收
     * @param s 消息内容
     */
    @Override
    public void onMessage(String s) {
        log.info("MQ接收到信息");
    }
}
