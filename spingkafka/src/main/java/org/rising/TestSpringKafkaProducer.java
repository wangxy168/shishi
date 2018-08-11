package org.rising;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:application-kafka-producer.xml"})
public class TestSpringKafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Test
    public void testSend() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setOrderId("orderId" + i);
            order.setPrice(Long.valueOf(1000 + i));
            order.setItemIds(Arrays.asList(1001L, 1002L));
            order.setUserId(999);
            order.setCreated(new Date());
            order.setUpdated(new Date());

            this.kafkaTemplate.sendDefault(order);
            System.out.println("发送消息 --> " + i);
            Thread.sleep(100);
        }
    }

}
