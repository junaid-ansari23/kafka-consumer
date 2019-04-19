package kafka.example.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.example.kafka.ForecastDataConsumerThread;
import kafka.example.thread.WorkerThreadPool;
import kafka.example.util.DBCPManager;
import kafka.example.util.Util;

/**
 * @author juansari
 * Main invoker class to initialize kafka and other configuration and start the job
 *
 */
public class InvokeConsumerJob {
	private static Logger logger = LoggerFactory.getLogger(InvokeConsumerJob.class);
	
	public static void main(String a[]) {
		logger.info("Starting SalesIQ Kafka forcast job at: {}",new Date());
		try {
			//initialize required configuration and start separate consumer process for each topic
			initializeJobConfiguration();
		}catch(Exception ex) {
			logger.error("Error while starting SalesIQ EDS Kafka job",ex);
			//send notification before exiting JVM
			System.exit(1);
		}
	}
	
	private static void initializeJobConfiguration() throws Exception {
		try {
			//initialize property file
			Util.initProperty();
			//initialize thread pool
			WorkerThreadPool.getInstance();
			//initialize database connection pool
			DBCPManager.initialize();
			//initialize and start kafka consumer process thread
			initializeKafkaConsumerProcess();
			
			logger.info("SalesIQ-kafka job initialized and started successfully..!!");
			
		} catch (Exception e) {
			throw new Exception("Error while initializing SalesIQ-kafka job configuration ",e);
		}
		
	}
	
	private static void initializeKafkaConsumerProcess() throws Exception {
		try {
			String edssForecastTopic=Util.getPropertyValue(Util.KAFKA_TOPIC_FORECATS);
			String edssForecastRmTopic=Util.getPropertyValue(Util.KAFKA_TOPIC_RM_FORECATS);

			List<String> edssForecastTopicList =new ArrayList<>();
			edssForecastTopicList.add(edssForecastTopic);

			List<String> edssForecastRmTopicList =new ArrayList<>();
			edssForecastRmTopicList.add(edssForecastRmTopic);

			//forecast
			Thread forecastThread=new Thread(new ForecastDataConsumerThread(edssForecastTopicList,Util.getConsumerConfig()));
			forecastThread.start();
			logger.info("ForecastThread started..");

			//RM forecast
			Thread forecastRmThread=new Thread(new ForecastDataConsumerThread(edssForecastRmTopicList,Util.getConsumerConfig()));
			forecastRmThread.start();
			logger.info("RM forecastThread started..");
		} catch (Exception e) {
			logger.error("Error while initializing kafka consumer process ",e);
			throw new Exception("Error while initializing kafka consumer process ",e);
		}
		
	}

}
