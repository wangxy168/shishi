package org.rising.dashboard.generate.service;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.rising.dashboard.generate.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * 生成订单数据
 */
@Service
public class OrderGenerateImpl implements IGenerate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    createData();
                }
            }
        }).start();
    }

    public void createData() {

        // 生成订单数据，发送到kafka
        Order order = new Order();

        // 从redis中读取最大的用户id进行随机生成
        Long maxUserId = Long.valueOf(this.redisTemplate.opsForValue().
                get("org-dashboard-generate-user-max-id").toString());
        order.setUserId(RandomUtils.nextLong(1, maxUserId));

        order.setPayment(RandomUtils.nextDouble(100, 10000));
        order.setUpdateTime(new Date());
        order.setCreateTime(order.getUpdateTime());
        order.setStatus(RandomUtils.nextInt(1, 7));

        if (order.getStatus() == 1) {
            order.setPaymentTime(new Date());
        }
        order.setPostFee("0");
        order.setPaymentType(1);
        order.setOrderId(StringUtils.replace(UUID.randomUUID().toString(), "-", ""));

        if (order.getStatus() == 6) {
            order.setCloseTime(new Date());
        }

        if (order.getStatus() >= 4) {
            order.setConsignTime(new Date());
            order.setShippingName("顺丰速运");
            order.setShippingCode("185164687464" + RandomUtils.nextInt(10, 100));
        }

        if (order.getStatus() >= 5) {
            order.setEndTime(new Date());
        }

        // 发送
        kafkaTemplate.sendDefault(order);
        try {
            Thread.sleep(RandomUtils.nextLong(10, 3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
