package com.cisco.eds.salesiq.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.eds.salesiq.util.Util;


/**
 * ThreadPoolExecutor for any server side background process.
 * 	
 */
public class WorkerThreadPool {
	
	private static Logger logger = LoggerFactory.getLogger(WorkerThreadPool.class);

	private static final String THREADPOOL_COREPOOLSIZE="threadpool.corePoolSiz";
	private static final String THREADPOOL_MAXPOOLSIZE="threadpool.maxPoolSize";
	private static final String THREADPOOL_TTL="threadpool.ttl";

	private static class ThreadPoolExecutorLoader {
		private static WorkerThreadPool instance = new WorkerThreadPool();
	}

	private final ThreadPoolExecutor poolExecutor;

	public static WorkerThreadPool getInstance() {
		return ThreadPoolExecutorLoader.instance;
	}

	/**
	 * Private constructor which initialize the threadPoolExecutor following properties 1) Max Number of Tasks 2)
	 * Minimum number of threads 3) Max number of threads 4) TTL for idle threads The thread pool executor has abort
	 * policy.
	 */
	private WorkerThreadPool() {
		//read this from property file
		int corePoolSize = 10;
		int maxPoolSize = 5;
		int timeToLiveIdleThread = 20;
		
		try {
			corePoolSize = Integer.valueOf(Util.getPropertyValue(THREADPOOL_COREPOOLSIZE));
			maxPoolSize = Integer.valueOf(Util.getPropertyValue(THREADPOOL_MAXPOOLSIZE));
			timeToLiveIdleThread = Integer.valueOf(Util.getPropertyValue(THREADPOOL_TTL));;
			
			logger.info("Thread Pool Initialized with : Pool Size : {}, Max Size:{}, TTL: {}",
					corePoolSize, maxPoolSize, timeToLiveIdleThread);

		} catch (final Exception e) {
			logger.error("Excepption while initializing thread pool",e);
		}

		this.poolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, timeToLiveIdleThread, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>());
	}

	/**
	 * Adds the new thread task to the queue for execution. If a thread is available, then the task gets executed.
	 * Otherwise, the task will be waiting in the queue and executes upon next available thread.
	 * 
	 * @param baseThread
	 * @throws Exception
	 */

	public void addTask(final Runnable thread) throws Exception {
		try {
			this.poolExecutor.execute(thread);
		} catch (final Exception e) {
			logger.error("Error while processing thread : ", e);
			throw new Exception("Error while processing thread");
		}
	}

	public void shutdown() {
		this.poolExecutor.shutdown();

	}

	public boolean isTerminated() {
		return this.poolExecutor.isTerminated();
	}

	public int getActiveTask() {
		return this.poolExecutor.getActiveCount();
	}

	public int getQueueSize() {
		return this.poolExecutor.getQueue().size();
	}

}


