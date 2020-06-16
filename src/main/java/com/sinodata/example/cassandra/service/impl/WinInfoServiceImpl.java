package com.sinodata.example.cassandra.service.impl;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.sinodata.example.cassandra.entity.WinInfo;
import com.sinodata.example.cassandra.entity.requset.WinInfoRequest;
import com.sinodata.example.cassandra.service.WinInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.EntityWriteResult;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * FileName: WinInfoServiceImpl
 * Author:  liuyang
 * Date:    2020/6/169:59
 * Description: 中奖信息服务实现
 * History:
 * <author>     <time>      <version>       <desc>
 */

@Repository
public class WinInfoServiceImpl implements WinInfoService {

    @Autowired
    private CassandraTemplate cassandraTemplate;
    @Autowired
    private AsyncCassandraTemplate asyncCassandraTemplate;

    @Override
    public void prepareWinInfoSync(WinInfoRequest winInfoRequest) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(ConsistencyLevel.ANY).build();
        for(int winHead = winInfoRequest.getBeginWinHead(); winHead <= winInfoRequest.getEndWinHead(); winHead ++){
            for(int winTail = winInfoRequest.getBeginWinTail(); winTail <= winInfoRequest.getEndWinTail(); winTail ++){
                WinInfo winInfo = new WinInfo(winInfoRequest.getProjCode(), winInfoRequest.getProdBatchCode(), String.format("%07d", winHead), String.format("%03d", winTail), String.format("%d等奖", winTail));
                try{
                    cassandraTemplate.insert(winInfo, options);
                } catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        }
    }

    @Override
    public void prepareWininfoAsync(WinInfoRequest winInfoRequest) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(ConsistencyLevel.ANY).build();
        for(int winHead = winInfoRequest.getBeginWinHead(); winHead <= winInfoRequest.getEndWinHead(); winHead ++){
            for(int winTail = winInfoRequest.getBeginWinTail(); winTail <= winInfoRequest.getEndWinTail(); winTail ++){
                WinInfo winInfo = new WinInfo(winInfoRequest.getProjCode(), winInfoRequest.getProdBatchCode(), String.format("%07d", winHead), String.format("%03d", winTail), String.format("%d等奖", winTail));
                ListenableFuture<EntityWriteResult<WinInfo>> listenableFuture = asyncCassandraTemplate.insert(winInfo, options);
                listenableFuture.addCallback(new ListenableFutureCallback<EntityWriteResult<WinInfo>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.toString());
                    }

                    @Override
                    public void onSuccess(EntityWriteResult<WinInfo> result) {
                        return;
                    }
                });
            }
        }
    }


}
