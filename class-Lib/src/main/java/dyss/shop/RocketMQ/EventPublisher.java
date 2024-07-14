package dyss.shop.RocketMQ;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author dyss
 * @date 2024/7/13 21:06

   @Description 必读:BaseEvent定义的是事件的基本类型，在DDD中一般放于types层，
   所有的事件消息都要继承这一抽象接口

 */
//@Component
@Slf4j
public class EventPublisher {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 消息发送者
     * @param topic 主题
     * @param message 消息对象
     */
    public void publish(String topic,BaseEvent.BaseMessage message){
        try {
            //经过MQ发送需要转成JSON对象
            String mqMessage = JSON.toJSONString(message);
            log.info("发送RocketMQ消息,topic：{},message：{}",topic,message);
            rocketMQTemplate.convertAndSend(topic,mqMessage);
        } catch (MessagingException e) {
            log.info("发送RocketMQ消息失败,topic：{},message：{}",topic,message,e);
        }
    }

    /**
     * 延迟发送
     * @param topic 主题
     * @param message 消息对象
     */
    public void publish(String topic,BaseEvent.BaseMessage message,int delayTimeLevel){
        try {
            //经过MQ发送需要转成JSON对象
            String mqMessage = JSON.toJSONString(message);
            log.info("发送RocketMQ消息,topic：{},message：{}",topic,message);
            //第二个参数将对象转化为MQ对象再进行发送
            rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(message).build(),1000,delayTimeLevel);
        } catch (MessagingException e) {
            log.info("发送RocketMQ延迟消息失败,topic：{},message：{}",topic,JSON.toJSONString(message),e);
        }
    }
}
