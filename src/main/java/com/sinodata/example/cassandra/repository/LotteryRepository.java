package com.sinodata.example.cassandra.repository;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.Lottery;
import org.springframework.data.cassandra.core.WriteResult;

import java.util.List;

/**
 * FileName: LotteryRepository
 * Author:  liuyang
 * Date:    2020/6/911:19
 * Description: 操作Lottery对象的接口
 * History:
 * <author>     <time>      <version>       <desc>
 */

public interface LotteryRepository {

    List<Lottery> getLotterys(String projCode, String prodBatchCode, String bookNo, ConsistencyLevel consistencyLevel);

    WriteResult saveBatch(List<Lottery> lotteries, ConsistencyLevel consistencyLevel);

    WriteResult save(Lottery lottery, ConsistencyLevel consistencyLevel);

    WriteResult pureInsert(Lottery lottery);


}
