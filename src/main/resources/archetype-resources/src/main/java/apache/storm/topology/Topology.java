package apache.storm.topology;

import apache.storm.bolt.AggregateCountBolt;
import apache.storm.spout.WordSpout;
import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

public class Topology {

    public static void main(String[] args) throws Exception {

        Config config = new Config();
        WordSpout spout = new WordSpout();
        AggregateCountBolt bolt = new AggregateCountBolt();

        // wordSpout ==> countBolt
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("wordSpout", spout);
        builder.setBolt("wordCountBolt", bolt).shuffleGrouping("wordSpout");

        if (args.length == 0) {
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("test", config, builder.createTopology());
            Thread.sleep(60000);
            cluster.killTopology("test");
            cluster.shutdown();
            System.exit(0);
        } else if (args.length == 1) {
            StormSubmitter.submitTopology(args[0], config, builder.createTopology());
        } else {
            System.out.println("Usage: Topology <topologyName>");
        }
    }
}
