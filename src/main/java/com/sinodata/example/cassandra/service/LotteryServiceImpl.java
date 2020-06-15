package com.sinodata.example.cassandra.service;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.Lottery;
import com.sinodata.example.cassandra.entity.requset.InsertTest;
import com.sinodata.example.cassandra.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.WriteResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FileName: LotteryServiceImpl
 * Author:  liuyang
 * Date:    2020/6/913:00
 * Description: 彩票操作接口实现
 * History:
 * <author>     <time>      <version>       <desc>
 */
@Service
public class LotteryServiceImpl implements LotteryService{
    @Autowired
    private LotteryRepository lotteryRepository;


    @Override
    public Boolean inWarehouse(String projCode, String prodBatchCode, String startBookNo, String endBookNo, String whNo) {
        for(int bookNo = Integer.parseInt(startBookNo); bookNo < Integer.parseInt(endBookNo); bookNo ++){
            List<Lottery> lotteries = lotteryRepository.getLotterys(projCode, prodBatchCode, String.format("%07d", bookNo), ConsistencyLevel.TWO);
            if(lotteries == null || lotteries.size() ==0){
                Lottery lottery = new Lottery(projCode, prodBatchCode, String.format("%07d", bookNo), 1, 2, whNo, "");
                WriteResult result = lotteryRepository.save(lottery, ConsistencyLevel.TWO);
                if(!result.wasApplied()){
                    return false;
                }
            }else {
                System.out.println(String.format("彩票%s%s%s已存在!", projCode, prodBatchCode, String.format("%07d", bookNo)));
            }
        }
        return null;
    }

    @Override
    public String  insertTest(InsertTest insertTest) {
        ConsistencyLevel consistencyLevel;
        if(insertTest.getConcurrency() == 2){
            consistencyLevel = ConsistencyLevel.TWO;
        } else {
            consistencyLevel = ConsistencyLevel.ONE;
        }
        Long time = 0L;
        Integer sucessNumber = 0;
        for(int bookNo=insertTest.getBeginBookNo(); bookNo<=insertTest.getEndBookNo(); bookNo++){
            Lottery lottery = new Lottery(insertTest.getProjCode(), insertTest.getProdBatchCode(), String.format("%07d", bookNo), 1, 2, "", "");
            Long t1 = System.currentTimeMillis();
            WriteResult result = lotteryRepository.save(lottery, consistencyLevel);
            Long t2 = System.currentTimeMillis();
            time += t2-t1;
            if(result.wasApplied()){
                sucessNumber ++;
            }
        }
        String str = String.format("平均插入时间:%6dms!插入成功:%6d笔!", time/(insertTest.getEndBookNo() - insertTest.getBeginBookNo() + 1), sucessNumber);
        return str;
    }

    @Override
    public void pureInsertTest(InsertTest insertTest) {
        Long time = 0L;
        Integer sucessNumber = 0;
        for(int bookNo=insertTest.getBeginBookNo(); bookNo<=insertTest.getEndBookNo(); bookNo++){
            Lottery lottery = new Lottery(insertTest.getProjCode(), insertTest.getProdBatchCode(), String.format("%07d", bookNo), 1, 2, "", "");
            Long t1 = System.currentTimeMillis();
            WriteResult result = lotteryRepository.pureInsert(lottery);
            Long t2 = System.currentTimeMillis();
            time += t2-t1;
            if(result.wasApplied()){
                sucessNumber ++;
            }
        }
        return;
    }

    @Override
    public String getTest(InsertTest insertTest) {
        ConsistencyLevel consistencyLevel;
        if(insertTest.getConcurrency() == 2){
            consistencyLevel = ConsistencyLevel.TWO;
        } else {
            consistencyLevel = ConsistencyLevel.ONE;
        }
        Long time = 0L;
        Integer sucessNumber = 0;
        for(int bookNo=insertTest.getBeginBookNo(); bookNo<=insertTest.getEndBookNo(); bookNo++){
            Lottery lottery = new Lottery(insertTest.getProjCode(), insertTest.getProdBatchCode(), String.format("%07d", bookNo), 1, 2, "", "");
            Long t1 = System.currentTimeMillis();
            List<Lottery> lotteries = lotteryRepository.getLotterys(insertTest.getProjCode(), insertTest.getProdBatchCode(), String.format("%07d", bookNo), consistencyLevel);
            Long t2 = System.currentTimeMillis();
            time += t2-t1;
            sucessNumber ++;
        }
        String str = String.format("平均插入时间:%dms!\n插入成功:%d笔!", time/(insertTest.getEndBookNo() - insertTest.getBeginBookNo() + 1), sucessNumber);
        return str;

    }
}
