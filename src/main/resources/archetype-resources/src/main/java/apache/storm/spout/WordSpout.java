package apache.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class WordSpout extends BaseRichSpout {
    public static final String[] words = new String[]{"apple", "orange", "pineapple", "banana", "watermelon"};
    SpoutOutputCollector collector;

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }

    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
    }

    public void nextTuple() {
        final Random rand = new Random();
        final String word = words[rand.nextInt(words.length)];
        this.collector.emit(new Values(word), UUID.randomUUID());
        Thread.yield();
    }
}
