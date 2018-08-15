package org.rising.ack;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现Bolt，需要继承BaseRichBolt
 */
public class WordCountBolt extends BaseRichBolt {
    Map<String, Integer> mpas = new HashMap<String, Integer>();

    private OutputCollector collector;

    public void prepare(Map mpas, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Integer count = this.mpas.get(word);
        if (null == count) {
            count = 0;
        }
        count++;
        this.mpas.put(word, count);

        // 向下游输出数据
        this.collector.emit(tuple, new Values(word, count));

        // 这里模拟有时成功，有时失败
        // System.currentTimeMillis()当前系统时间的毫秒值
        if (System.currentTimeMillis() % 2 == 0) {
            // 偶数，成功
            this.collector.ack(tuple);
        } else {
            // 奇数，失败
            this.collector.fail(tuple);
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word", "count"));
    }
}
