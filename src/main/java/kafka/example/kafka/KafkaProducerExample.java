package kafka.example.kafka;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import kafka.example.dao.NgfDataDao;
import kafka.example.util.NgfKafkaModel;

import java.io.InputStream;
import java.util.Properties;

public class KafkaProducerExample {

	public static void main1(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "eds-kafka-dev-001:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		try {
			for(int i = 0; i < 100; i++)
				producer.send(new ProducerRecord<String, String>("testtopic", Integer.toString(i), "Message Number "+Integer.toString(i)));
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			producer.close();
		}
		System.out.println("Done");

	}
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "eds-kafka-dev-001:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		try {
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("edss_sales_ngf_forecast1.json");
			String jsonData= IOUtils.toString(inputStream);
			
			producer.send(new ProducerRecord<String, String>("edss_sales_ngf_forecast", "test_hana_key", jsonData));
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			producer.close();
		}
		System.out.println("Done");

	}
}
