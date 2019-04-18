package com.cisco.eds.salesiq.util;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalProducer {
	private static Logger logger = LoggerFactory.getLogger(LocalProducer.class);

	public static void main1(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "eds-kafka-dev-001:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		try {
			for(int i = 0; i < 10; i++) {
				//producer.send(new ProducerRecord<String, String>("salesiq_test_topic", Integer.toString(i), "Message Number "+Integer.toString(i)));
				producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), "Message Number "+Integer.toString(i)));
			}
		}
		catch (Exception e) {
			logger.error("Error", e);
		}finally {
			producer.close();
		}
		logger.info("Done");

	}
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "edsskafka-dev:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("edss_sales_ngf_forecast1.json");
			String jsonData= IOUtils.toString(inputStream);
			for(int i=0;i<5;i++) {
				//producer.send(new ProducerRecord<String, String>("edss_sales_ngf_forecast", "hana", jsonData));
				producer.send(new ProducerRecord<String, String>("edss_sales_ngf_forecast", "local", jsonData));
				Thread.sleep(5000);
			}			
		}
		catch (Exception e) {
			logger.error("Error", e);
		}finally {
			producer.close();
		}
		logger.info("Done");
	}
	//to convert string message to Java object
	public static NgfKafkaModel parsekafkaMessage() {
		System.out.println("hello");
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(Feature.class, false);
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		mapper.setDateFormat(df);
		NgfKafkaModel ngfKafkaModel=null;
		String jsonData=null;
		try { 
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("edss_sales_ngf_forecast1.json");
			jsonData= IOUtils.toString(inputStream);
			//get json data from Kafka consumer API and pass on line no- 74
			ngfKafkaModel=mapper.readValue(jsonData, NgfKafkaModel.class);
		}catch(Exception ex) {
			logger.error("Error while parsing json to java object", ex);
		}
		return ngfKafkaModel;
	}
}
