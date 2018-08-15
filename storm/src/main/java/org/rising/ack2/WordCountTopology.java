package org.rising.ack2;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;
import org.rising.stormjdbc.RandomSentenceSpout;
import org.rising.stormjdbc.SplitSentenceBolt;

public class WordCountTopology {
    public static void main(String[] args) {
        // 第一步，定义TopologyBuilder对象，用于构建拓扑
        TopologyBuilder builder = new TopologyBuilder();

        // 第二步，设置spout和bolt
        // 设置线程数为2（也就是并行度数，但是并不是topology的并行度，是在worker执行的线程）,Tasks数为2
        builder.setSpout("randomSentenceSpout", new RandomSentenceSpout());
        builder.setBolt("splitSentenceBolt", new SplitSentenceBolt()).localOrShuffleGrouping("randomSentenceSpout");

        // 第三步，构建Topology对象
        StormTopology topology = builder.createTopology();
        Config config = new Config();

        if (args == null || args.length == 0) {
            // 第四步，提交拓扑到集群，这里先提交到本地的模拟环境进行测试
            LocalCluster localCluster = new LocalCluster();
            localCluster.submitTopology("wordCountTopology", config, topology);

        } else {
            // 设置工作进程数，适合集群模式
            config.setNumWorkers(2);
            // 关闭acker机制
            config.setNumAckers(0);
            //提交到集群，并且将参数作为拓扑的名称wordCountTopology
            try {
                StormSubmitter.submitTopology(args[0], config, topology);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
