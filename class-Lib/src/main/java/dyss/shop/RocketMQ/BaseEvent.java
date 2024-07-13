package dyss.shop.RocketMQ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author dyss
 * @date 2024/7/13 21:07

  @Description 必读:BaseEvent定义的是事件的基本类型，在DDD中一般放于types层，
  所有的事件消息都要继承这一抽象接口【这是一个抽象的接口，消息数据类型是不知道的】
  消息数据类型一般具体到domain层来定义

 */
@Data
public abstract class BaseEvent<T> {
    private String id;
    private Date timestamp;
    private T data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class BaseMessage<T>{
        private String data;
    }
}
