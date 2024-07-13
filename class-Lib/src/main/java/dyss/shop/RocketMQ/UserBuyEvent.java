package dyss.shop.RocketMQ;

import lombok.Data;

/**
 * @author dyss
 * @date 2024/7/13 21:31

 @Description 必读:这里就是领域层根据业务需求对消息进行定义的类了

 *
 */
@Data
public class UserBuyEvent extends BaseEvent<String>{
    public static String TOPIC = "DYSS-MQ";

    public static String create(){
        //创建对应需要的数据
        return "data";
    }

    public static class userMessage extends BaseEvent.BaseMessage{
        private String data;
    }
}
