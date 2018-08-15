package org.rising;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class MyMessageListener implements MessageListener<String, String> {

    public void onMessage(ConsumerRecord<String, String> data) {
        System.out.println("接收到的消息为：" + data);
    }
}