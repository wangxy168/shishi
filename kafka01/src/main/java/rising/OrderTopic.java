package rising;

import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.apache.kafka.common.security.JaasUtils;
import org.junit.Test;

import java.util.Properties;

/**
 * 创建topic
 */
public class OrderTopic {

    @Test
    public void testTopic() {
        ZkUtils zkUtils = null;
        try {
            // 参数：zookeeper地址，session超时时间，连接超时时间，是否启用zookeeper安全机制
            zkUtils=ZkUtils.apply("node1:2181", 30000, 3000, JaasUtils.isZkSecurityEnabled());
            // topic名称
            String topicName = "kafka-topic";
            if (!AdminUtils.topicExists(zkUtils, topicName)) {
                // 参数：zkUtils，topic名称，partition数量，副本数量，参数，机架感知模式
                AdminUtils.createTopic(zkUtils, topicName, 1, 1, new Properties(), AdminUtils.createTopic$default$6());
                System.out.println(topicName + "创建成功");
            } else {
                System.out.println(topicName + "已经创建");
            }
        } catch (Exception e) {
            if (null != zkUtils) {
                zkUtils.close();
                e.printStackTrace();
            }
        }

    }

    // 删除topic
    @Test
    public void deleteTopic() {
        ZkUtils zkUtils = null;
        try {
            // 参数：zookeeper地址，session超时时间，连接超时时间，是否启用zookeeper安全机制
            zkUtils=ZkUtils.apply("node1:2181", 30000, 3000, JaasUtils.isZkSecurityEnabled());

            String topicName = "kafka-topic";
            if (AdminUtils.topicExists(zkUtils, topicName)) {
                // 参数：zkUtils，topic名称，partition数量，副本数量，参数，机架感知模式
                AdminUtils.deleteTopic(zkUtils, topicName);
                System.out.println(topicName + "删除成功");
            } else {
                System.out.println(topicName + "不存在");
            }
        } catch (Exception e) {
            if (null != zkUtils) {
                zkUtils.close();
                e.printStackTrace();
            }
        }
    }

}
