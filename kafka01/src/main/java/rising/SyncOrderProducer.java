package rising;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 订单的生产者代码
 */
public class SyncOrderProducer {

    // 异步实现，测试生产者
    @Test
    public void testProducer() throws InterruptedException {
        Properties conf = new Properties();

        // 设置kafka服务列表，多个用逗号分隔
        conf.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092");
        // 设置序列化消息key类
        conf.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 设置序列化消息value的类
        conf.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 缓冲区数量
        conf.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "1000");
        // 等待时间
        conf.setProperty(ProducerConfig.LINGER_MS_CONFIG, "10000");

        // 初始化
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(conf);

        for (int i = 0; i < 100; i++) {
            final ProducerRecord record = new ProducerRecord("kafka-topic", "data........" + i);
            // 发送消息
            kafkaProducer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("消息的callback：" + recordMetadata);
                }
            });
            System.out.println("发送消息" + i);
            Thread.sleep(100);
        }

        kafkaProducer.close();

    }

    // 同步实现，测试生产者
    @Test
    public void testProducer2() throws InterruptedException, ExecutionException {
        Properties conf = new Properties();

        // 设置kafka服务列表，多个用逗号分隔
        conf.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092");
        // 设置序列化消息key类
        conf.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 设置序列化消息value的类
        conf.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 缓冲区数量
//        conf.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, "1000");
        // 等待时间
//        conf.setProperty(ProducerConfig.LINGER_MS_CONFIG, "10000");

        // 初始化
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(conf);

        for (int i = 0; i < 100; i++) {
            final ProducerRecord record = new ProducerRecord("kafka-topic", "data........" + i);
            // 发送消息
            Future future = kafkaProducer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("消息的callback：" + recordMetadata);
                }
            });
            // 同步模式进行阻塞
            future.get();
            System.out.println("发送消息" + i);
            Thread.sleep(100);
        }

        kafkaProducer.close();

    }
}