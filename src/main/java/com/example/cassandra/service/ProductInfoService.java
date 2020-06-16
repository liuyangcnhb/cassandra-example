package com.example.cassandra.service;

import com.example.cassandra.entity.requset.ProductInfoRequest;

/**
 * FileName: ProductInfoService
 * Author:  liuyang
 * Date:    2020/6/169:32
 * Description: 货物信息服务
 * History:
 * <author>     <time>      <version>       <desc>
 */
public interface ProductInfoService {
    /**Product
     * 利用同步方案准备测试数据
     * @param productInfoRequest 包含起止信息的类
     */
    void prepareWinInfoSync(ProductInfoRequest productInfoRequest);

    /**
     * 利用异步方法准备测试数据
     * @param productInfoRequest 包含起止信息的类
     */
    void prepareWininfoAsync(ProductInfoRequest productInfoRequest);
}
