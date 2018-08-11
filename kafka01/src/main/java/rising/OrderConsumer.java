package rising;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.Test;

import java.util.Arrays;
import java.util.Properties;

/**
 * 消费订单数据
 */
public class OrderConsumer {
    public static void main(String[] args) {
        // 1，连接集群
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "node1:9092");
        properties.put("group.id", "my-kafka-topic");
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        // 2，发送数据，需要订阅模式下的topic，order1
        kafkaConsumer.subscribe(Arrays.asList("my-kafka-topic"));
        while (true) {
            // jdk queue offer插入，poll获取数据，blooking queue插入元素，take获取元素
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
            for (ConsumerRecord<String, String> record : consumerRecords) {
                System.out.println("消费数据为：" + record.value());
            }
        }

    }

    // 测试消费者
    @Test
    public void testConsumer() {
        Properties conf = new Properties();

        // 设置kafka服务列表，多个用逗号分隔
        conf.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092");
        // 设置消费者分组id
        conf.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "yu-group1");
        // 设置客户端id
        conf.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "yu-cient1");
        // 设置偏移量自动提交
        conf.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 设置偏移量提交时间间隔
        conf.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // 设置反序列化消息key的类
        conf.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 设置反序列化消息value的类
        conf.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 初始化
        KafkaConsumer kafkaConsumer = new KafkaConsumer<String, String>(conf);

        // 订阅topic
        kafkaConsumer.subscribe(Arrays.asList("kafka-topic"));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                long offset = record.offset();
                System.out.println("value1 = " + value + ", offset1 = " + offset);
            }
        }
    }


    // 测试消费者
    @Test
    public void testConsumer2() {
        Properties conf = new Properties();

        // 设置kafka服务列表，多个用逗号分隔
        conf.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092");
        // 设置消费者分组id
        conf.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "yu-group2");
        // 设置客户端id
        conf.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "yu-cient2");
        // 设置偏移量自动提交
        conf.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 设置偏移量提交时间间隔
        conf.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");

        // 设置反序列化消息key的类
        conf.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 设置反序列化消息value的类
        conf.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 初始化
        KafkaConsumer kafkaConsumer = new KafkaConsumer<String, String>(conf);

        // 订阅topic
        kafkaConsumer.subscribe(Arrays.asList("kafka-topic"));

        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                String value = record.value();
                long offset = record.offset();
                System.out.println("value2 = " + value + ", offset2 = " + offset);
            }
        }

    }
}
