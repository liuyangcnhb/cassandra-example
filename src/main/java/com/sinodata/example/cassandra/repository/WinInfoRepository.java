package com.sinodata.example.cassandra.repository;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.Lottery;
import com.sinodata.example.cassandra.entity.WinInfo;
import org.springframework.data.cassandra.core.WriteResult;

import java.util.List;

/**
 * FileName: WinInfoRepository
 * Author:  liuyang
 * Date:    2020/6/1610:13
 * Description: 中奖信息存读接口
 * History:
 * <author>     <time>      <version>       <desc>
 */
public interface WinInfoRepository {
    List<WinInfo> getWinInfo(String projCode, String prodBatchCode, String winHead, String winTail, ConsistencyLevel consistencyLevel);

    WriteResult save(WinInfo winInfo, ConsistencyLevel consistencyLevel);

    WriteResult pureInsert(WinInfo winInfo);
}
