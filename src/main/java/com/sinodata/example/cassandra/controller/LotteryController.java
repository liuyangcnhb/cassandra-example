package com.sinodata.example.cassandra.controller;

import com.sinodata.example.cassandra.entity.requset.InsertTest;
import com.sinodata.example.cassandra.entity.requset.WinInfoRequest;
import com.sinodata.example.cassandra.service.LotteryService;
import com.sinodata.example.cassandra.service.WinInfoService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: LotteryController
 * Author:  liuyang
 * Date:    2020/6/911:30
 * Description: 彩票操作接口
 * History:
 * <author>     <time>      <version>       <desc>
 */
@ApiModel(value = "cassandra测试")
@RestController
@RequestMapping("/cassandra")
public class LotteryController {

    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private WinInfoService winInfoService;

    @ApiOperation(value = "插入测试")
    @PostMapping("/insertTest")
    public String insertTest(@RequestBody InsertTest insertTest){
        return lotteryService.insertTest(insertTest);

    }

    @ApiOperation(value = "单纯插入测试")
    @PostMapping("/pureInsertTest")
    public String pureInsertTest(@RequestBody InsertTest insertTest){
        lotteryService.pureInsertTest(insertTest);
        return "";
    }
    @ApiOperation(value = "查询测试")
    @GetMapping("/getTest")
    public String getTest(@RequestBody InsertTest insertTest){
        return lotteryService.getTest(insertTest);

    }

    @ApiOperation(value = "同步方法准备中奖信息")
    @PostMapping("/prepareWinInfoSync")
    public String prepareWinInfoSync(@RequestBody WinInfoRequest winInfoRequest){
        winInfoService.prepareWinInfoSync(winInfoRequest);
        return "OK";
    }

    @ApiOperation(value = "异步方法准备中奖信息")
    @PostMapping("/prepareWinInfoAsync")
    public String prepareWinInfoAsync(@RequestBody WinInfoRequest winInfoRequest){
        Long t1 = System.currentTimeMillis();
        System.out.println(String.format("当前时间:%d", t1));
        winInfoService.prepareWininfoAsync(winInfoRequest);
        Long t2 = System.currentTimeMillis();
        System.out.println(String.format("当前时间:%d", t2));
        System.out.println(String.format("花费时间:%d", t2-t1));
        return String.format("花费时间:%d", t2-t1);
    }

}
