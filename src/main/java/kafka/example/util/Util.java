package kafka.example.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.example.thread.KafkaProcessThread;
import kafka.example.thread.WorkerThreadPool;


public class Util {

	private static Logger logger = LoggerFactory.getLogger(Util.class);
    public static final String CISCO_LIFE = "cisco.life";
    private static final String SPL_EMPTY_STR = "";
    private static final String LOCAL = "LOCAL";
    
    private static final String BOOTSTRAP_SERVERS="bootstrap.servers";
    private static final String GROUP_ID="group.id";
    private static final String ENABLE_AUTO_COMMIT="enable.auto.commit";
    private static final String KEY_DESERIALIZER="key.deserializer";
    private static final String VALUE_DESERIALIZER="value.deserializer";
    private static final String DESERIALIZER_KEY_VALUE_CLASS="org.apache.kafka.common.serialization.StringDeserializer";
    private static final String KAFKA_BOOTSTRAP_SERVERS= "kafka.bootstrap.servers";
    private static final String KAFKA_GROUP_ID="kafka.group.id";
    public static final String KAFKA_TOPIC_FORECATS="kafka.topic.edss.sales.forecats";
    public static final String KAFKA_TOPIC_RM_FORECATS="kafka.topic.edss.sales.rm.forecats";

    public enum EnvironmentType {
        DEV, LT, PROD, STAGE, LOCAL, TRN, FPR, POE, DR
    }
	
    private static Properties props=null;
    private static final String PROP_NAME="application.properties";

 /*   static {
    	try {
    		props=Util.loadEnvDetails();
    	} catch (FileNotFoundException e) {			
    		logger.error("Error while loading environment details", e);
    		//send notification before exiting JVM
    		System.exit(1);
    	}catch (Exception e) {			
    		logger.error("Error while loading environment details", e);
    		//send notification before exiting JVM
    		System.exit(1);
    	}
    }*/

    /**
     * Private constructor for utility class
     */
    private Util() {

    }

    /**
     * Returns empty collection if input is null
     * 
     * @param iterable
     * @return
     */
    public static <T> Iterable<T> emptyIfNull(Iterable<T> iterable) {
        return iterable == null ? Collections.<T> emptyList() : iterable;
    }

    /**
     * Returns true if input string is null or empty
     * 
     * @param inputString
     * @return boolean
     */
    public static boolean isNull(String inputString) {
        String str = SPL_EMPTY_STR;
        if (inputString != null) {
            str = inputString.trim();
        }
        return str.isEmpty();
    }

    /**
     * Method to get properties from a property file
     * 
     * @fileName The file to read properties from
     * @return The property object
     * @throws FileNotFoundException
     */

    public static Properties getProperties(String fileName) throws FileNotFoundException {

        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(fileName);

        Properties property = new Properties();

        if (inputStream != null) {
            try {
                property.load(inputStream);
            } catch (IOException e) {
            	logger.error("Error reading properties from connection property file: {}" , fileName,e);
            }
        } else {
            throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
        }

        return property;
    }

    /**
     * Method to return a file content as a String
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String getFileContentAsString(String fileName) throws IOException {
        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(fileName);
        return IOUtils.toString(inputStream, "UTF-8");

    }
    /**
     * Returns what is set in cisco.life for the environment. If cisco.life is
     * not set, it returns "LOCAL"
     * 
     * @return
     */
    public static String getLifeCycle() {
        String life = System.getProperty(CISCO_LIFE);
        if (isNull(life)) {
            life = LOCAL;
        }
        return life;
    }

    public static String getEnvironmentType() {
        String life = getLifeCycle();
        EnvironmentType environmentType = EnvironmentType.LOCAL;
        if (life.contains(EnvironmentType.DEV.toString())) { // NOSONAR
            environmentType = EnvironmentType.DEV;
        } else if (life.contains(EnvironmentType.STAGE.toString())) {
            environmentType = EnvironmentType.STAGE;
        } else if (life.contains(EnvironmentType.LT.toString())) {
            environmentType = EnvironmentType.LT;
        } else if (life.contains(EnvironmentType.PROD.toString())) {
            environmentType = EnvironmentType.PROD;
        } else if (life.contains(EnvironmentType.POE.toString())) {
            environmentType = EnvironmentType.POE;
        } else if (life.contains(EnvironmentType.TRN.toString())) {
            environmentType = EnvironmentType.TRN;
        } else if (life.contains(EnvironmentType.DR.toString())) {
            environmentType = EnvironmentType.DR;
        }
        return environmentType.toString();
    }
    
    public static Properties getConsumerConfig() throws FileNotFoundException {
    	Properties props = new Properties();
    	
		 props.put(BOOTSTRAP_SERVERS, getPropertyValue(appendEnv(KAFKA_BOOTSTRAP_SERVERS)));		 
		 props.put(GROUP_ID, getPropertyValue(KAFKA_GROUP_ID));
	     props.put(ENABLE_AUTO_COMMIT, "false");
	     props.put(KEY_DESERIALIZER, DESERIALIZER_KEY_VALUE_CLASS);
	     props.put(VALUE_DESERIALIZER, DESERIALIZER_KEY_VALUE_CLASS);
	     return props;
    }
    
    public static String getPropertyValue(String key) throws FileNotFoundException {
    	return (String) Util.getProperty().get(key);
    }
    public static String appendEnv(String key){
    	return (key+"-"+getLifeCycle());
    }
	
	public static Properties loadEnvDetails() throws FileNotFoundException {
		return Util.getProperties(PROP_NAME);
	}
	public static Properties getProperty() throws FileNotFoundException {
		return props;
	}
	
	/**
	 * This method will take of entire processing of kafka messages getting from each poll.Processing will involve,parsing json messages,inserting
	 * into database table and re-set kafka offset position in case of any failure or error.Each message from a single poll will be processes in
	 * a separate thread obtain from pre-configured thread pool.
	 * 
	 * @param records
	 * @param consumer
	 * @param topic
	 * @return
	 */
	public static boolean processForecastData(ConsumerRecords<String, String> records,KafkaConsumer<String, String> consumer,String topic) {		
		int partition=0;
		long offset=0;
		Connection conn=null;
		WorkerThreadPool pool=null;		
		List<KafkaProcessThread> threadList=null;		
		try {
			pool=WorkerThreadPool.getInstance();
			conn=DBCPManager.getConnection();
			//set auto commit to false
			conn.setAutoCommit(false);
			//set the latch size as 'no of messages' per poll
			int recordCount=records.count();
			CountDownLatch latch=new CountDownLatch(recordCount);
			KafkaProcessThread processThread=null;
			threadList=new ArrayList<>();
			for (ConsumerRecord<String, String> record : records) {
				partition=record.partition();
				offset=record.offset();

				logger.debug("partition:- {}, offset:- {}",partition,offset);
				//logger.debug("value = {}" , record.value());
				String message=record.value();
				//set status to 'false' by default
				processThread=new KafkaProcessThread(latch, Boolean.FALSE,conn,partition,offset,message);
				//list to keep the track of process status
				threadList.add(processThread);
				//adding will also start executing thread's run method
				pool.addTask(processThread);				
				//wait for all process thread to complete
				latch.await(120, TimeUnit.SECONDS);
			}
			latch.await();
			if(checkProcessStatus(consumer, topic,  threadList)) {
				/*Commit the records in database if there are no error/failure for all message threads.
				*/
			conn.commit();
			}
		} catch (Exception e) {
			//reset,due to some db exception
			resetOffsetOnDbfailure(consumer, topic, threadList);
			logger.error("Error while processing kafka messages", e);
			logger.error("Error while processing kafka messages for partition: {},offset: {}", partition,offset);
			return false;
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("Error while closing db connection", e);
				}
			}
		}
		return true;
	}
	
	/**Method to get all the failure threads and corresponding partition number.This is required to reset offset position,so that same message
	 * can be fetched in subsequent poll.
	 * Commit the records in database if there are no error/failure for all message threads.If any of the message thread fails then
				offset position has to be reset for all messages,across partitions in current poll even for successful thread.
				Since common DB connection is shared so offset should be reset to successful thread as well,otherwise it will not appear 
				in next poll and due to other failed messages it was not inserted in DB also.
				
	 * @param consumer
	 * @param topic
	 * @param threadList
	 * @return
	 */
	private static boolean checkProcessStatus(KafkaConsumer<String, String> consumer,String topic,
			List<KafkaProcessThread> threadList) {
		boolean status=true;		
		Map<Integer,List<Long>> statusMap=new HashMap<>();
		for(KafkaProcessThread process:threadList) {
			//offsetList.add(process.getMessageOffset());
			if(!process.getProcessStatus()) {
				if(statusMap.get(process.getPartitionNumber())!=null) {					
					statusMap.get(process.getPartitionNumber()).add(process.getMessageOffset());
				}else {
					List<Long> offMessList=new ArrayList<>();
					offMessList.add(process.getMessageOffset());
					statusMap.put(process.getPartitionNumber(), offMessList);
				}
			}
		}
		//there are some failed process
		if(!statusMap.isEmpty()) {
			status=false;
			Set<Entry<Integer, List<Long>>> entry= statusMap.entrySet();
			for(Entry<Integer, List<Long>> offMessage:entry) {
				//sort the list to get lowest starting offset position to reset the message read
				List<Long> offsetMessgList=offMessage.getValue();
				Collections.sort(offsetMessgList);
				resetOffsetOnfailure(consumer,topic,offMessage.getKey(),offsetMessgList.get(0));
			}
		}
		return status;
	}
	
	private static void resetOffsetOnDbfailure(KafkaConsumer<String, String> consumer,String topic,
			List<KafkaProcessThread> threadList) {				
		Map<Integer,List<Long>> statusMap=new HashMap<>();
		for(KafkaProcessThread process:threadList) {
			if(statusMap.get(process.getPartitionNumber())!=null) {					
				statusMap.get(process.getPartitionNumber()).add(process.getMessageOffset());
			}else {
				List<Long> offMessList=new ArrayList<>();
				offMessList.add(process.getMessageOffset());
				statusMap.put(process.getPartitionNumber(), offMessList);
			}
		}
		Set<Entry<Integer, List<Long>>> entry= statusMap.entrySet();
		for(Entry<Integer, List<Long>> offMessage:entry) {
			//sort the list to get lowest starting offset position to reset the next message read
			List<Long> offsetMessgList=offMessage.getValue();
			Collections.sort(offsetMessgList);
			// offsetMessgList.get(0) gives lowest offset in this poll 
			resetOffsetOnfailure(consumer,topic,offMessage.getKey(),offsetMessgList.get(0));
		}
	}
	
	private static void resetOffsetOnfailure(KafkaConsumer<String, String> consumer,String topic,int partition,long offset) {
		TopicPartition tp = new TopicPartition(topic, partition);
		consumer.seek(tp, offset);
	}
	
	public static void initProperty() throws Exception{
		try {
			props=Util.loadEnvDetails();
		} catch (FileNotFoundException e) {			
			throw new Exception("Error while loading environment details", e);    		
		}catch (Exception e) {			
			throw new Exception("Error while loading environment details", e);
		}    
	}

}
