package org.rising;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:application-kafka-consumer.xml"})
public class TestSpringKafkaConsumer {

    @Test
    public void testConsumer() {
        try {
            Thread.sleep(999999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
