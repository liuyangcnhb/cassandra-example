package com.sinodata.example.cassandra.service;

import com.sinodata.example.cassandra.entity.requset.WinInfoRequest;
import org.springframework.stereotype.Service;

/**
 * FileName: WinInfoService
 * Author:  liuyang
 * Date:    2020/6/169:32
 * Description: 中奖信息服务
 * History:
 * <author>     <time>      <version>       <desc>
 */
public interface WinInfoService {
    /**
     * 利用同步方案准备中奖数据
     * @param winInfoRequest 包含中奖起止信息的类
     */
    void prepareWinInfoSync(WinInfoRequest winInfoRequest);

    /**
     * 利用异步方法准备中奖数据
     * @param winInfoRequest 包含中奖起止信息的类
     */
    void prepareWininfoAsync(WinInfoRequest winInfoRequest);
}
