package kafka.example.thread;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base thread class for any server service
 */
public abstract class BaseThread implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(BaseThread.class);
	private final CountDownLatch doneSignal;

	protected BaseThread(final CountDownLatch doneSignal) {
		super();
		this.doneSignal = doneSignal;	
	}

	public abstract void execute() throws Exception;

	private String getThreadName() {
		return this.getClass().getSimpleName() + "-" + Thread.currentThread().getId();
	}

	public void run() {
		String threadName = null;
		try {
			threadName = this.getThreadName();
			Thread.currentThread().setName(threadName);
			this.execute();
			
		} catch (final Exception ex) {
			logger.error("Got error while executing threads..", ex);
		} finally {
			if (this.doneSignal != null) {
				this.doneSignal.countDown();
			}
		}
	}
}

