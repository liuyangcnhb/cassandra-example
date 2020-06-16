package com.example.cassandra.service.impl;

import com.datastax.oss.driver.api.core.ConsistencyLevel;
import com.example.cassandra.entity.ProductInfo;
import com.example.cassandra.entity.requset.ProductInfoRequest;
import com.example.cassandra.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.AsyncCassandraTemplate;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.EntityWriteResult;
import org.springframework.data.cassandra.core.InsertOptions;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * FileName: ProductInfoServiceImpl
 * Author:  liuyang
 * Date:    2020/6/169:59
 * Description: 中奖信息服务实现
 * History:
 * <author>     <time>      <version>       <desc>
 */

@Repository
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private CassandraTemplate cassandraTemplate;
    @Autowired
    private AsyncCassandraTemplate asyncCassandraTemplate;

    @Override
    public void prepareWinInfoSync(ProductInfoRequest productInfoRequest) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(ConsistencyLevel.ANY).build();
        for(int winHead = productInfoRequest.getBeginWinHead(); winHead <= productInfoRequest.getEndWinHead(); winHead ++){
            for(int winTail = productInfoRequest.getBeginWinTail(); winTail <= productInfoRequest.getEndWinTail(); winTail ++){
                ProductInfo productInfo = new ProductInfo(productInfoRequest.getProjCode(), productInfoRequest.getProdBatchCode(), String.format("%07d", winHead), String.format("%03d", winTail), String.format("%d等奖", winTail));
                try{
                    cassandraTemplate.insert(productInfo, options);
                } catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        }
    }

    @Override
    public void prepareWininfoAsync(ProductInfoRequest productInfoRequest) {
        InsertOptions options = InsertOptions.builder().consistencyLevel(ConsistencyLevel.ANY).build();
        for(int winHead = productInfoRequest.getBeginWinHead(); winHead <= productInfoRequest.getEndWinHead(); winHead ++){
            for(int winTail = productInfoRequest.getBeginWinTail(); winTail <= productInfoRequest.getEndWinTail(); winTail ++){
                ProductInfo productInfo = new ProductInfo(productInfoRequest.getProjCode(), productInfoRequest.getProdBatchCode(), String.format("%07d", winHead), String.format("%03d", winTail), String.format("%d等奖", winTail));
                ListenableFuture<EntityWriteResult<ProductInfo>> listenableFuture = asyncCassandraTemplate.insert(productInfo, options);
                listenableFuture.addCallback(new ListenableFutureCallback<EntityWriteResult<ProductInfo>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.toString());
                    }

                    @Override
                    public void onSuccess(EntityWriteResult<ProductInfo> result) {
                        return;
                    }
                });
            }
        }
    }


}
