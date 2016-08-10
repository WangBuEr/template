package cn.buer.util.snowflake;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhujuan From: https://github.com/twitter/snowflake An object that
 *         generates IDs. This is broken into a separate class in case we ever
 *         want to support multiple worker threads per process
 */
public class IdWorker {

	protected static final Logger LOG = LoggerFactory.getLogger(IdWorker.class);

	private long workerId;
	private long datacenterId;
	private long sequence = 0L;

	private long twepoch = 1288834974657L;

	private long workerIdBits = 5L;
	private long datacenterIdBits = 5L;
	private long maxWorkerId = -1L ^ (-1L << workerIdBits);
	private long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
	private long sequenceBits = 12L;

	private long workerIdShift = sequenceBits;
	private long datacenterIdShift = sequenceBits + workerIdBits;
	private long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;
	private long sequenceMask = -1L ^ (-1L << sequenceBits);

	private long lastTimestamp = -1L;

	public IdWorker(long workerId, long datacenterId) {
		// sanity check for workerId
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(
					String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		if (datacenterId > maxDatacenterId || datacenterId < 0) {
			throw new IllegalArgumentException(
					String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
		}
		this.workerId = workerId;
		this.datacenterId = datacenterId;
		LOG.info(String.format(
				"worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
				timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId));
	}

	public synchronized long nextId() {
		long timestamp = timeGen();

		if (timestamp < lastTimestamp) {
			LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
			throw new RuntimeException(String.format(
					"Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
		}

		if (lastTimestamp == timestamp) {
			sequence = (sequence + 1) & sequenceMask;
			if (sequence == 0) {
				timestamp = tilNextMillis(lastTimestamp);
			}
		} else {
			sequence = 0L;
		}

		lastTimestamp = timestamp;

		return ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift)
				| (workerId << workerIdShift) | sequence;
	}

	protected long tilNextMillis(long lastTimestamp) {
		long timestamp = timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = timeGen();
		}
		return timestamp;
	}

	protected long timeGen() {
		return System.currentTimeMillis();
	}
	public static int FNVHash(String data) {  
		  final int p = 16777619;  
		  int hash = (int) 2166136261L;  
		  for (int i = 0; i < data.length(); i++)  
		   hash = (hash ^ data.charAt(i)) * p;  
		  hash += hash << 13;  
		  hash ^= hash >> 7;  
		  hash += hash << 3;  
		  hash ^= hash >> 17;  
		  hash += hash << 5;  
		  return hash;  
		 }  
	public static void main(String[] args) {
		final IdWorker idWorker = new IdWorker(0, 0);
		Long id = idWorker.nextId();
		System.out.println("id=" + id + ",hash=" + FNVHash(String.valueOf(id)));
		long mod = 758965818821182441L% 16;
		
		System.out.println(mod);
		System.out.println(new BigDecimal(mod).divide(new BigDecimal(16/4)));
		
	}
}