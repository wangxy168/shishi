package rising;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.Test;

import java.util.Properties;

/**
 * 订单的生产者代码
 */
public class OrderProducer {

    // 测试生产者
    @Test
    public void testProducer() throws InterruptedException {
        Properties conf = new Properties();

        // 设置kafka服务列表，多个用逗号分隔
        conf.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "node1:9092,node2:9092");
        // 设置序列化消息key类
        conf.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 设置序列化消息value的类
        conf.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 初始化
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(conf);

        for (int i = 0; i < 100; i++) {
            // 发送数据，需要一个ProducerRecord对象，最少的参数：String topic, V value
            ProducerRecord record = new ProducerRecord("kafka-topic", "data........" + i);
            // 发送消息
            kafkaProducer.send(record);
            System.out.println("发送消息" + i);
            Thread.sleep(100);
        }

        kafkaProducer.close();

    }

    public static void main(String[] args) throws InterruptedException {
        /**
         *  1，连接集群，通过配置文件的方式
         *  2，发送数据-topic:order,value
         */
        // 1，准备配置文件
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "node1:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        // 2，创建KafkaProducer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);

        // 3，发送数据，需要一个ProducerRecord对象，最少的参数：String topic, V value
        //    其中my-kafka-topic为创建的订单topic对象
        for (int i = 0; i < 1000; i++) {
            kafkaProducer.send(new ProducerRecord<String, String>("my-kafka-topic", "发送信息" + i));
            Thread.sleep(100);
        }
    }

}