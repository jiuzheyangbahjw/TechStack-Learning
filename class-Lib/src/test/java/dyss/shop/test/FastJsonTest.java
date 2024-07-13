package dyss.shop.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson2.filter.SimplePropertyPreFilter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

/**
 * @author dyss
 * @date 2024/7/13 19:55
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class FastJsonTest {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserEntity{
//        @JSONField(name = "userName",serialize = false)
        private String userName;
        private Integer userAge;
    }

    @Test
    public void serializableTest(){
        String strJson = JSON.toJSONString(UserEntity.builder().userName("zhangsan").build());
        log.info("序列化结果为：{}",strJson);
        UserEntity userEntity = JSON.parseObject(strJson, UserEntity.class);
        log.info("反序列化结果为：{}",userEntity);
    }

    @Test
    public void serializableTest2(){
        UserEntity userEntity = UserEntity.builder().userName("zhangsan").build();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Collections.addAll(filter.getExcludes(),"userName");
        log.info(com.alibaba.fastjson2.JSON.toJSONString(userEntity,filter));
    }

}
