package org.rising.ack3;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.FailedException;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Random;

/**
 * 按照空格对字符串的分割
 */
public class SplitSentenceBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String value = input.getStringByField("value");
        if(null == value){
            return ;
        }
        String[] values = value.split(" ");
        for (String v : values) {
            //向下游发送单词
            collector.emit(new Values(v));
        }

        //这里模拟有时成功有时失败
        int num = new Random().nextInt(100);
        if(num % 2 != 0){
            // 奇数，失败
            throw new FailedException();
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }


}
