package org.rising.ack;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

import java.util.Map;

/**
 * 打印
 */
public class PrintBolt extends BaseRichBolt {

    private OutputCollector collector;
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    public void execute(Tuple tuple) {
        String word = tuple.getStringByField("word");
        Integer count = tuple.getIntegerByField("count");

        // 打印上游的数据
//        System.out.println(word + ":" + count);

        // 注意，没有下游了，结束

        // 成功
        this.collector.ack(tuple);
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }
}
