package com.sinodata.example.cassandra.repository.impl;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.Lottery;
import com.sinodata.example.cassandra.repository.LotteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.WriteResult;
import org.springframework.data.cassandra.core.cql.QueryOptions;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.cassandra.core.query.Criteria.where;

/**
 * FileName: LotteryRepositoryImpl
 * Author:  liuyang
 * Date:    2020/6/911:31
 * Description: 彩票操作接口实现
 * History:
 * <author>     <time>      <version>       <desc>
 */
@Repository
public class LotteryRepositoryImpl implements LotteryRepository {

    @Autowired
    private CassandraOperations cassandraTemplate;


    @Override
    public List<Lottery> getLotterys(String projCode, String prodBatchCode, String bookNo, ConsistencyLevel consistencyLevel) {
        QueryOptions options = QueryOptions.builder().consistencyLevel(consistencyLevel).build();
        return cassandraTemplate.select(
                Query.query(where("proj_code").is(projCode)).
                        and(where("prod_batch_code").is(prodBatchCode)).
                        and(where("book_no").is(bookNo)).queryOptions(options),
                Lottery.class
        );

    }

    @Override
    public WriteResult saveBatch(List<Lottery> lotteries, ConsistencyLevel consistencyLevel) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(consistencyLevel).ifNotExists(true).build();
        return cassandraTemplate.batchOps().insert(lotteries, options).execute();
    }


    @Override
    public WriteResult save(Lottery lottery, ConsistencyLevel consistencyLevel) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(consistencyLevel).ifNotExists(true).build();
        return cassandraTemplate.insert(lottery, options);
    }

    @Override
    public WriteResult pureInsert(Lottery lottery) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(ConsistencyLevel.ANY).build();
        return cassandraTemplate.insert(lottery, options);
    }
}
