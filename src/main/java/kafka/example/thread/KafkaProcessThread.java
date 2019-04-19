/**
 * 
 */
package kafka.example.thread;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.example.dao.NgfDataDao;
import kafka.example.util.NgfKafkaModel;

/**
 * @author juansari
 * Worker thread for kafka message processing.This will basically perform database insertion and keep track on success and failure status
 *
 */
public class KafkaProcessThread extends BaseThread {
	private static Logger logger = LoggerFactory.getLogger(KafkaProcessThread.class);
	private boolean status;
	private long offset;
	private int partition;	
	private Connection conn;
	private String message;
	private static final String PARSE_DATE_FORMAT="dd-MMM-yyyy";

	public KafkaProcessThread(CountDownLatch doneSignal,boolean status,Connection conn,
			int partition,long offset,String message) {
		super(doneSignal);
		this.status=status;		
		this.conn=conn;
		this.partition=partition;
		this.offset=offset;
		this.message=message;
	}
	
	@Override
	public void execute() throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			DateFormat df = new SimpleDateFormat(PARSE_DATE_FORMAT);
			mapper.setDateFormat(df);
			NgfKafkaModel ngfKafkaModel=mapper.readValue(message, NgfKafkaModel.class);
			//NgfDataDao.insertNgfData(ngfKafkaModel, conn);
			status=Boolean.TRUE;
		}catch(Exception ex){
			//Error,set process status for false;
			status=Boolean.FALSE;
			logger.error("Error in while processing kafka message", ex);
		}
	}
	
	public boolean getProcessStatus() {
		return this.status;
	}
	public long getMessageOffset() {
		return this.offset;
	}
	public int getPartitionNumber() {
		return this.partition;
	}

}
