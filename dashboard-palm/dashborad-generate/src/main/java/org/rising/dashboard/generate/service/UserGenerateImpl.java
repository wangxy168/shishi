package org.rising.dashboard.generate.service;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.rising.dashboard.generate.bean.User;
import org.rising.dashboard.generate.util.CityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserGenerateImpl implements IGenerate {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserGenerateImpl.class);

    public static final String[] CHANNEL = new String[]{"广点通", "朋友推荐", "新浪", "百度", "地推", "广告", "电视广告", "报纸广告", "软文转化"};

    public static final String[] VISIT = new String[]{"浏览页面", "评论商品", "加入收藏", "加入购物车", "提交订单", "使用优惠券", "领取优惠券", "搜索", "查看订单"};

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void start() {
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        generateUser();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        generateVisitUser();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    // 生成用户数据，模拟用户注册
    private void generateUser() throws Exception {
//        Long sleep = RandomUtils.nextLong(500, 1000 * 60);
        Long sleep = RandomUtils.nextLong(500, 1000);
//        Thread.sleep(1000);
        Thread.sleep(sleep);

        User user = new User();
        user.setId(null); //由数据库生成
        user.setBirthYear(new DateTime().plusYears(-(RandomUtils.nextInt(0, 40))).toString("yyyy"));
        user.setChannel(CHANNEL[RandomUtils.nextInt(0, CHANNEL.length)]);
        user.setVipLevel(RandomUtils.nextInt(1, 10));
        user.setSex(RandomUtils.nextInt(1, 3));
        String[] citys = StringUtils.split(CityUtils.randomProvinceCity(), '|');
        user.setCity(citys[1]);
        user.setProvince(citys[0]);
        user.setFirstPay(null); //首次注册还未购物
        user.setFirstVisit(new Date());
        user.setLatestPay(null);
        user.setLatestVisit(user.getFirstVisit());
        user.setSignupTime(new Date());

        this.kafkaTemplate.send("topic-org-dashboard-generate-user-register", user);

    }

    // 模拟用户的访问
    private void generateVisitUser() throws Exception {
//        Long sleep = RandomUtils.nextLong(200, 1000 * 5);
        Long sleep = RandomUtils.nextLong(200, 1000);
        Thread.sleep(sleep);
        Long maxUserId = Long.valueOf(this.redisTemplate.opsForValue().get("org-dashboard-generate-user-max-id").toString());
        Long userId = RandomUtils.nextLong(1, maxUserId);
        String visit = VISIT[RandomUtils.nextInt(0, VISIT.length)];
        DateTime now = new DateTime();
        int maxHour = now.getHourOfDay();
        int maxMillis = now.getMinuteOfHour();
        int maxSeconds = now.getSecondOfMinute();
        String date = now.plusHours(-(RandomUtils.nextInt(0, maxHour)))
                .plusMinutes(-(RandomUtils.nextInt(0, maxMillis)))
                .plusSeconds(-(RandomUtils.nextInt(0, maxSeconds)))
                .toString("yyyy-MM-dd HH:mm:ss");

        String result = "DAU|" + userId + "|" + visit + "|" + date;
        LOGGER.info(result);
    }
}
