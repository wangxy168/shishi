package org.rising;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 实现Bolt，需要继承BaseRichBolt
 */
public class SplitSentenceBolt extends BaseRichBolt {
    private OutputCollector collector;

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    public void execute(Tuple tuple) {
        // 通过tuple.getStringByField获取上游的数据，其中sentence是上游定义的字段名称
        String sentence = tuple.getStringByField("sentence");
        // 进行分割处理
        String[] words = sentence.split(" ");
        // 向下游输出数据
        for (String word : words) {
            this.collector.emit(new Values(word));
        }
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        // 定义向下游输出的名称
        outputFieldsDeclarer.declare(new Fields("word"));
    }
}
