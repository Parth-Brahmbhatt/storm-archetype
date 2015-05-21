package apache.storm.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import org.apache.storm.guava.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AggregateCountBolt extends BaseRichBolt {
    private static final Logger LOG = LoggerFactory.getLogger(AggregateCountBolt.class);

    private Map<String, BigDecimal> wordCount;
    private OutputCollector collector;

    public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
        wordCount = new HashMap<String, BigDecimal>();
        this.collector = collector;
    }

    public void execute(Tuple input) {
        String word = input.getStringByField("word");
        if (wordCount.containsKey(word)) {
            BigDecimal val = wordCount.get(word).add(BigDecimal.ONE);
            wordCount.put(word, val);
            LOG.info("%s = %s", word, val);
            System.out.println(word + " = " + val);
            collector.emit(input, new Values(word, val));
        } else {
            wordCount.put(word, BigDecimal.ONE);
            collector.emit(input, new Values(word, BigDecimal.ONE));
        }

        collector.ack(input);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word", "count"));
    }
}

