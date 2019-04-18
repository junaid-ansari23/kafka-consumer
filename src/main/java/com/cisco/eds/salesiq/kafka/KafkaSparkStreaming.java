package com.cisco.eds.salesiq.kafka;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

import scala.Tuple2;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;


public final class KafkaSparkStreaming {
	  
	
	  private KafkaSparkStreaming() {
	  }

	  public static void main(String[] args) {
    
	  
	  if(args.length != 6)
      {
          System.out.println("SparkStream <zookeeper_ip> <group_nm> <topic1,topic2,...>,duration,number of threads,hadoopPath");
          System.exit(1);
      }
     
      int numThreads = Integer.parseInt(args[4]);
      
      int duration = Integer.parseInt(args[3]);
      
      final String hadoopPath = args[5];
      
      Map<String,Integer> topicMap = new HashMap<String,Integer>();
      
      String[] topic = args[2].split(",");
      
      for(String t: topic)
      {
          //topicMap.put(t, new Integer(3));
    	  topicMap.put(t, numThreads);
      }
      
      
      SparkConf conf = new SparkConf().setAppName("SparkStreamingWithKafka");

      JavaStreamingContext jssc = new JavaStreamingContext(conf, new Duration(duration));

      JavaSparkContext jsc = jssc.sparkContext();

      final HiveContext hiveContext = new org.apache.spark.sql.hive.HiveContext(jsc.sc());

      //Connect to kafka topic and create stream
      JavaPairDStream<String, String> messages = KafkaUtils.createStream(jssc, args[0], args[1], topicMap );
      

      JavaDStream<String> data = messages.map(new Function<Tuple2<String, String>, String>() 
                                              {
                                                  public String call(Tuple2<String, String> message)
                                                  {
                                                      return message._2();
                                                  }
                                              }
                                              );
      
      
      hiveContext.sql("Use yourhiveschema");
      
      data.foreachRDD(new Function<JavaRDD<String>, Void>()
	      {
    	  public Void call(JavaRDD<String> rdd){
              List<String> messageValues = rdd.collect();
              
              System.out.println("num of messages=============="+messageValues.size());
              if(messageValues.size() > 0){
	              for(int i=0; i<messageValues.size();i++){
	            	  System.out.println("message=============="+messageValues.get(i));
	            	  hiveContext.sql("insert into table yourhiveschema.yourtable select '"+messageValues.get(i) +"' from yourhiveschema.dummytemptable ");
	            	    
	              }
              
              //Below line saves the data into HDFS at the given hadoop path. 
              //rdd.saveAsTextFile(hadoopPath);
              }
              return null;
    	  }
	   }
    		  
     );
     
     
    // sc.stop();
 
     jssc.start();
     jssc.awaitTermination();

  }
 }


