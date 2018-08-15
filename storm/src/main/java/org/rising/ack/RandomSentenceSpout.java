package org.rising.ack;

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
 * Spout类需要实现BaseRichSpout
 */
public class RandomSentenceSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private String[] sentences = new String[]{
            "the cow jumped over the moon", "an apple a day keeps the doctor away",
            "four score and seven years ago", "snow white and the seven dwarfs",
            "i am at two with nature",
            "her name called xuwei"
    };

    /**
     * 初始化一些操作放到这里
     *
     * @param map                  配置信息
     * @param topologyContext      应用的上下文
     * @param spoutOutputCollector 向下游输出数据的收集器
     */
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    /**
     * 处理业务逻辑，在最后向下游输出数据
     */
    public void nextTuple() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String sentence = this.sentences[new Random().nextInt(sentences.length)];
        System.out.println("生成的句子为 -->" + sentence);

        // 生成消息id，并且把数据存放到messages的map中
        String msgId = UUID.randomUUID().toString();
//        messages.put(msgId, sentence);
        // 下游输出，msgId是消息ID
        this.collector.emit(new Values(sentence), msgId);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        // 定义向下游输出的名称
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }

    @Override
    public void ack(Object msgId) {
        System.out.println(msgId + " --> 处理成功！");
//        messages.remove(msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println(msgId + " --> 处理失败！ 需要进行重试");
        // 进行重试
//        this.collector.emit(new Values(messages.get(msgId)),msgId);
//        System.out.println(msgId + " --> 重试完成！");
    }
}
