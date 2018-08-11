package org.rising;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

public class WordCountTopology {
    public static void main(String[] args) {
        // 第一步，定义TopologyBuilder对象，用于构建拓扑
        TopologyBuilder builder = new TopologyBuilder();

        // 第二步，设置spout和bolt
        // 设置线程数为2,Tasks数为2
        builder.setSpout("randomSentenceSpout", new RandomSentenceSpout(), 2).setNumTasks(2);
        builder.setBolt("splitSentenceBolt", new SplitSentenceBolt(), 4).shuffleGrouping("randomSentenceSpout").setNumTasks(4);
        builder.setBolt("wordCountBolt", new WordCountBolt(), 2).partialKeyGrouping("splitSentenceBolt", new Fields("word"));
        builder.setBolt("printBolt", new PrintBolt()).shuffleGrouping("wordCountBolt");

        // 第三步，构建Topology对象
        StormTopology topology = builder.createTopology();
        Config config = new Config();
        // 设置工作进程数
        config.setNumWorkers(2);

        // 第四步，提交拓扑到集群，这里先提交到本地的模拟环境进行测试
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("wordCountTopology", config, topology);

        // 提交到集群
//        try {
//            StormSubmitter.submitTopology("wordCountTopology2", config, topology);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
