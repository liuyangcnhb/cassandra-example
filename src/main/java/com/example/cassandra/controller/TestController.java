package com.example.cassandra.controller;

import com.example.cassandra.entity.requset.ProductInfoRequest;
import com.example.cassandra.service.ProductInfoService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * FileName: TestController
 * Author:  liuyang
 * Date:    2020/6/911:30
 * Description: 彩票操作接口
 * History:
 * <author>     <time>      <version>       <desc>
 */
@ApiModel(value = "cassandra测试")
@RestController
@RequestMapping("/cassandra")
public class TestController {


    @Autowired
    private ProductInfoService productInfoService;

    @ApiOperation(value = "同步方法准备中奖信息")
    @PostMapping("/prepareWinInfoSync")
    public String prepareWinInfoSync(@RequestBody ProductInfoRequest productInfoRequest){
        productInfoService.prepareWinInfoSync(productInfoRequest);
        return "OK";
    }

    @ApiOperation(value = "异步方法准备中奖信息")
    @PostMapping("/prepareWinInfoAsync")
    public String prepareWinInfoAsync(@RequestBody ProductInfoRequest productInfoRequest){
        Long t1 = System.currentTimeMillis();
        System.out.println(String.format("当前时间:%d", t1));
        productInfoService.prepareWininfoAsync(productInfoRequest);
        Long t2 = System.currentTimeMillis();
        System.out.println(String.format("当前时间:%d", t2));
        System.out.println(String.format("花费时间:%d", t2-t1));
        return String.format("花费时间:%d", t2-t1);
    }

}
