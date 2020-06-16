package com.sinodata.example.cassandra.repository.impl;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.WinInfo;
import com.sinodata.example.cassandra.repository.WinInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.data.cassandra.core.WriteResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FileName: WinInfoRepositoryImpl
 * Author:  liuyang
 * Date:    2020/6/1610:13
 * Description: 中奖信息存读实现
 * History:
 * <author>     <time>      <version>       <desc>
 */
@Repository
public class WinInfoRepositoryImpl implements WinInfoRepository {
    @Autowired
    private CassandraOperations cassandraTemplate;

    @Override
    public List<WinInfo> getWinInfo(String projCode, String prodBatchCode, String winHead, String winTail, ConsistencyLevel consistencyLevel) {
        return null;
    }

    @Override
    public WriteResult save(WinInfo winInfo, ConsistencyLevel consistencyLevel) {
        return null;
    }

    @Override
    public WriteResult pureInsert(WinInfo winInfo) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(ConsistencyLevel.ANY).build();
        return cassandraTemplate.insert(winInfo, options);
    }
}
