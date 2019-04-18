package com.cisco.eds.salesiq.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import kafka.common.OffsetAndMetadata;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class LocalConsumer {
	
	public static void main(String[] args) {
		 Properties props = new Properties();
		 props.put("bootstrap.servers", "localhost:9092");
		 //props.put("bootstrap.servers", "eds-kafka-dev-001:9092");		 
		 //props.put("group.id", "local");
		 props.put("group.id", "salesiq_test");
	     props.put("enable.auto.commit", "true");
	     //props.put("auto.commit.interval.ms", 0);
	     //props.put("auto.offset.reset", "earliest");
	     props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);	     
	     consumer.subscribe(Arrays.asList("edss_sales_ngf_forecast"));
	     //consumer.subscribe(Arrays.asList("edss_sales_ngf_rmforecast"));
	     boolean flag=true;
	     while (true) {
		     try{	
		    	 //Keep polling the topic every 5 seconds		    	 
		         ConsumerRecords<String, String> records = consumer.poll(100);
		         for (ConsumerRecord<String, String> record : records) {
		        	 System.out.println("partiton:  " + record.partition()+" : "+" offset: "+record.offset());
		             System.out.println("value = " + record.value());		             
		         }
		         //TopicPartition partition=new TopicPartition("my_topic", 0);
		         /*if(flag) {
		        	 consumer.seek(new TopicPartition("my_topic", 0), 1); 
		        	 flag=false;
		        }*/
		    	 
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
