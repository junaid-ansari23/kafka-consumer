/**
 * 
 */
package kafka.example.kafka;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.example.util.Util;

/**
 * @author juansari
 * Consumer thread class for topic.Each consumer process should be implemented as thread and can provide their own process definition
 *
 */
public class ForecastDataConsumerThread implements Runnable {
	private static Logger logger = LoggerFactory.getLogger(ForecastDataConsumerThread.class);
	
	private List<String> topics;
	private Properties prop;
	KafkaConsumer<String, String> consumer = null;
	
	public ForecastDataConsumerThread(List<String> topics,Properties prop) {
		this.topics=topics;
		this.prop=prop;
		consumer = new KafkaConsumer<String, String>(prop);
		consumer.subscribe(topics);
	}
	
	public void processConsumer() {		
	     while (true) {
		     try{	
		    	 //Keep polling the topic every 5 seconds
		    	 logger.debug("Polling kafka broker-->");
		         ConsumerRecords<String, String> records = consumer.poll(5000);
		         Util.processForecastData(records, consumer, topics.get(0));		         
		     }
		     catch (Exception e) {		    		
	             logger.error("Error while processing forcats data for topic {}",topics.get(0), e);
		     }
	     }
	}
	public void testProcessConsumer() {		
	     while (true) {
		     try{	
		    	 //Keep polling the topic every 5 seconds
		    	 logger.info("Polling kafka broker for topic: {}",topics.get(0));
		    	 Thread.sleep(5000);
		         //ConsumerRecords<String, String> records = consumer.poll(5000);
		         /*for (ConsumerRecord<String, String> record : records) {		        	 
		        	 logger.debug("partition:- {}, offset:- {}",record.partition(),record.offset());
		        	 logger.debug("value = {}" , record.value());
		         }*/
		     }
		     catch (Exception e) {		    		
	             logger.error("Error while processing data", e);
		     }
	     }
	}

	@Override
	public void run() {
		this.processConsumer();
		//testProcessConsumer();
	}

}
