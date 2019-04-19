package kafka.example.kafka;


import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.avro.Schema;

public class JDBCConnectConsumer {
	
	public static void main(String[] args) {
	
	Properties props = new Properties();
		
	props.put("bootstrap.servers", "eds-kafka-dev-001:9092"); 
	props.put("group.id", "test123"); 
	props.put("key.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer"); 
	props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer"); 
	props.put("schema.registry.url", "http://eds-kafka-dev-001:8081"); 
		
	KafkaConsumer<Object, Object> consumer = new KafkaConsumer<Object, Object>(props);
	
	//subscribe to the topic
	consumer.subscribe(Arrays.asList("cpr-ora-jdbc-XXCPR_EVENT_PUB_DETAILS"));
	
	GenericRecord  avroRecord = null;
		
	while (true) {
			try{
				//poll the topic every 5 seconds
				ConsumerRecords<Object, Object> records = consumer.poll(5000);
				
				System.out.println("records====="+records.count());
				
				for (ConsumerRecord<Object, Object> record : records){
					System.out.println("record value====="+record.value());
					
					// Get the value part from consumer record
					avroRecord = (GenericRecord) record.value();
					
					// Get the byte value of the numeric column.
					ByteBuffer byteBuffervalue = (ByteBuffer) avroRecord.get("TRANSACTION_ID"); 
					 
					if(byteBuffervalue != null){
						byte[] byteValueArray = byteBuffervalue.array();
							
						BigDecimal converted = new BigDecimal(new BigInteger(byteValueArray));
						
						System.out.println("Col1 value ==="+converted);
						System.out.println("Col2 value ==="+avroRecord.get("EVENT_NAME"));
				
					}
				}
			}
			catch (Exception e){
			
				e.printStackTrace();
			}
			
		}
	}

}

