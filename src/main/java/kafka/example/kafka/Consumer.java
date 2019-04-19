package kafka.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import kafka.common.OffsetAndMetadata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class Consumer {
	
	public static void main(String[] args) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "eds-kafka-dev-001:9092");
		 //props.put("group.id", "test");
		 props.put("group.id", "hana");
	     props.put("enable.auto.commit", "false");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	     
	     //Subsribe to the topic
	     //eds_sales_ngf_forecast
	     //eds_sales_ngf_rmforecast

	     //consumer.subscribe(Arrays.asList("testtopic"));
	     consumer.subscribe(Arrays.asList("edss_sales_ngf_forecast"));
	     //consumer.subscribe(Arrays.asList("edss_sales_ngf_rmforecast"));
	     while (true) {
		     try{	
		    	 //Keep polling the topic every 5 seconds
		         ConsumerRecords<String, String> records = consumer.poll(100);
		         for (ConsumerRecord<String, String> record : records) {
		        	 System.out.println("partiton:- " + record.partition()+" : "+" offset:-"+record.offset());
		             System.out.println("value = " + record.value());
		         }
		     }
		     catch (Exception e) {		    		
	             e.printStackTrace();
		     }finally {
			       //consumer.close();
		     }
	     }
		

	}
	
	public static void main1(String a[]) {
		Properties props = new Properties();
		 //props.put("bootstrap.servers", "eds-kafka-dev-004:9092");
		 props.put("bootstrap.servers", "edsskafka-dev:9092");
		 //"edsskafka-dev:9092"
		 //props.put("group.id", "test");
		 props.put("group.id", "hana");
	     props.put("enable.auto.commit", "false");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
	     //TopicPartition tp = new TopicPartition("edss_sales_ngf_forecast", 1);
	     //consumer.seek(tp, 70);
	     
	     //Subsribe to the topic
	     //eds_sales_ngf_forecast
	     //eds_sales_ngf_rmforecast

	     //consumer.subscribe(Arrays.asList("testtopic"));
	     consumer.subscribe(Arrays.asList("edss_sales_ngf_forecast"));
	     //consumer.subscribe(Collections.singletonList("edss_sales_ngf_rmforecast"));
		
		try {
	         while(true) {
	             ConsumerRecords<String, String> records = consumer.poll(100);
	             for (TopicPartition partition : records.partitions()) {
	                 List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
	                 for (ConsumerRecord<String, String> record : partitionRecords) {
	                     System.out.println(record.offset() + ": " + record.value());
	                 }
	                 //long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
	                 //consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
	             }
	         }
	     } catch(Exception ex) {
	    	 ex.printStackTrace();
	     }
		finally {
	       consumer.close();
	     }
		
	}
}
