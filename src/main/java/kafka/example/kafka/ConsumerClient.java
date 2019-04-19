/**
 * 
 */
package kafka.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author juansari
 *
 */
public abstract class ConsumerClient {
	private static Logger logger = LoggerFactory.getLogger(ConsumerClient.class);
	
	public abstract void processConsumer();
}
