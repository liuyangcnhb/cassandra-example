package com.sinodata.example.cassandra.service;

import com.sinodata.example.cassandra.entity.requset.InsertTest;
import org.springframework.stereotype.Service;

/**
 * FileName: LotteryService
 * Author:  liuyang
 * Date:    2020/6/912:57
 * Description: 彩票操作接口
 * History:
 * <author>     <time>      <version>       <desc>
 */
@Service
public interface LotteryService {
    /**
     * 入站
     */
    Boolean inWarehouse(String projCode, String prodBatchCode, String startBookNo, String endBookNo, String whNo);

    /**
     * 插入测试
     */
    String insertTest(InsertTest insertTest);

    void pureInsertTest(InsertTest insertTest);

    String getTest(InsertTest insertTest);
}
