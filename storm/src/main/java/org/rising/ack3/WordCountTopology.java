package org.rising.ack3;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.topology.TopologyBuilder;

public class WordCountTopology {

    public static void main(String[] args) {

        // 拓扑的构建器
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        // 设置Spout
        topologyBuilder.setSpout("RandomSentenceSpout", new RandomSentenceSpout());
        topologyBuilder.setBolt("SplitSentenceBolt", new SplitSentenceBolt()).localOrShuffleGrouping("RandomSentenceSpout");

        // 得到一个拓扑
        StormTopology topology = topologyBuilder.createTopology();

        // 提交拓扑
        Config config = new Config();

        if (args == null || args.length == 0) {
            // 本地提交
            LocalCluster localCluster = new LocalCluster();
                localCluster.submitTopology("WordCountTopology", config, topology);
            }else{
                // 集群提交
                try {
                    // 设置worker数（进程）
                    config.setNumWorkers(2);
                    config.setNumAckers(0);
                    StormSubmitter.submitTopology(args[0], config, topology);
                } catch (AlreadyAliveException e) {
                    e.printStackTrace();
                } catch (InvalidTopologyException e) {
                    e.printStackTrace();
            } catch (AuthorizationException e) {
                e.printStackTrace();
            }
        }

    }
}
