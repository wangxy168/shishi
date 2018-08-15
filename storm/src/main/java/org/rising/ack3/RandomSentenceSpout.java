package org.rising.ack3;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * 随机产生一些字符串
 *
 */
public class RandomSentenceSpout extends BaseRichSpout {

    private String[] sentences = new String[]{"the cow jumped over the moon", "an apple a day keeps the doctor away",
            "four score and seven years ago", "snow white and the seven dwarfs", "i am at two with nature"};

    private SpoutOutputCollector collector;

    /**
     * 在初始化时会执行一次
     *
     * @param conf
     * @param context
     * @param collector
     */
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    /**
     * Storm框架会不断的调用该方法
     * 处理业务逻辑
     */
    public void nextTuple() {
        //先获取到发送的字符串
        String value = sentences[new Random().nextInt(sentences.length)];

        // 生成消息id，并且把数据存放到messages的map中
        String msgId = UUID.randomUUID().toString();
        System.out.println("生成的句子为 --> " + value +", msgid = " + msgId);
        // 发送字符串
        this.collector.emit(new Values(value), msgId);

        try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

    /**
     * 向下游传递数据时，要进行的定义
     *
     * @param declarer
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("value"));
    }

    @Override
    public void ack(Object msgId) {
        System.out.println(msgId + " --> 处理成功！");
    }

    @Override
    public void fail(Object msgId) {
        System.out.println(msgId + " --> 处理失败！！！");
        // 失败后，要进行重试操作
    }
}
