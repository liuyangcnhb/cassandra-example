package com.sinodata.example.cassandra;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.Lottery;
import com.sinodata.example.cassandra.repository.LotteryRepository;
import com.sinodata.example.cassandra.service.LotteryService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.WriteResult;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@SpringBootTest
class CassandraDemoApplicationTests {

	@Autowired
	private LotteryService lotteryService;

	@Autowired
	private LotteryRepository lotteryRepository;

	@Autowired
	private CqlTemplate cqlTemplate;

	@Autowired
	private RedissonClient redissonClient;

	@Test
	void contextLoads() {

//		String key = "test";
//		RLock rLock = redissonClient.getLock(key);
//		try{
//			rLock.lock(20000, TimeUnit.MILLISECONDS);
//			Boolean result2 = rLock.tryLock(20000, TimeUnit.MILLISECONDS);
//			rLock.unlock();
//			result2 = rLock.tryLock(2000, TimeUnit.MILLISECONDS);
//		} catch (Exception e) {
//			e.printStackTrace();
//			rLock.unlock();
//		}

		//repeatInsertTest(100, ConsistencyLevel.ONE);
	}

	void repeatInsertTest(Integer number, ConsistencyLevel consistencyLevel){
		Integer successNumber = 0;
		Long time = 0L;
		for(Integer bookNo=1; bookNo<=number; bookNo++){
			Lottery lottery = defaultLottery(bookNo);
			Long t1 = System.currentTimeMillis();
			String cqlStr = String.format("insert into lottery " +
					"(proj_code, prod_batch_code, book_no, action_sequence, action_status, action_date_time, lock_date_time, lock_flag, lock_user_no, logistics_no, relation_wh_no, wh_no) " +
					"values" +
					"('%s', '%s', '%s', 1, 2, null, null, 1, null, null, null, null)", lottery.getProjCode(), lottery.getProdBatchCode(), lottery.getBookNo());
			//cqlTemplate.execute(cqlStr);
			WriteResult result = lotteryRepository.pureInsert(lottery);
			Long t2 = System.currentTimeMillis();
			time += t2-t1;
			if(result.wasApplied()){
				successNumber ++;
			}
		}
		System.out.println(String.format("插入成功%d笔!", successNumber));
		System.out.println(String.format("平均插入时间%dms!", time/number));
	}

	void insertTest(Integer num){
		Long time = 0L;
		for(Integer i=0; i<num; i++){
			String bookNo = String.format("%07d", i);
			Lottery lottery = defaultLottery(i);
			Long t1 = System.currentTimeMillis();
			List<Lottery> lotteries = lotteryRepository.getLotterys("G0132", "20123", bookNo, ConsistencyLevel.ANY);
			//System.out.println(lotteries);
			lotteryRepository.save(lottery, ConsistencyLevel.ALL);
			Long t2 = System.currentTimeMillis();
			//System.out.println(String.format("花费时间:%-6dms", t2-t1));
			time += t2-t1;
		}
		System.out.println(String.format("平均插入时间:%d", time/num));
	}

	void batchInsertTest(Integer num){
		ArrayList<Lottery> lotteries = new ArrayList<>();
		for (Integer i=0; i<num; i++){
			String bookNo = String.format("%07d", i);
			Lottery lottery = defaultLottery(i);
			lotteries.add(lottery);
			if(lotteries.size() == 100){
				//Long t1 = System.currentTimeMillis();
				lotteryRepository.saveBatch(lotteries, ConsistencyLevel.ALL);
				//Long t2 = System.currentTimeMillis();
				//System.out.println(String.format("花费时间:%6dms", t2-t1));
				lotteries.clear();
			}

		}
	}

	Lottery defaultLottery(int bookNo){
		Lottery lottery = new Lottery("G0132", "20123", String.format("%07d", bookNo), 1, 2, "23990000", "10990001");
		return lottery;
	}

}
