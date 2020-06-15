package com.sinodata.example.cassandra.controller;

import com.sinodata.example.cassandra.entity.requset.InsertTest;
import com.sinodata.example.cassandra.repository.LotteryRepository;
import com.sinodata.example.cassandra.service.LotteryService;
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

//    @GetMapping("/insert/{num}")
//    public String insertTest(@PathVariable("num") Integer num){
//        Long time = 0L;
//        for(Integer i=0; i<num; i++){
//            String bookNo = String.format("%07d", i);
//            Lottery lottery = new Lottery("G0132", "20123", bookNo, LocalDateTime.now(), 2, "10990001", "23990000");
//            Long t1 = System.currentTimeMillis();
//            List<Lottery> lotteries = lotteryService.getLotterys("G0132", "20123", bookNo);
//            //System.out.println(lotteries);
//            lotteryService.save(lottery);
//            Long t2 = System.currentTimeMillis();
//            //System.out.println(String.format("花费时间:%-6dms", t2-t1));
//            time += t2-t1;
//        }
//        System.out.println(String.format("平均插入时间:%d", time/num));
//        return String.format("平均插入时间:%d", time/num);
//    }
//
//    @GetMapping("/insertBatch")
//    public String insertBatchTest(){
//
//        ArrayList<Lottery> lotteries = new ArrayList<>();
//        Integer testNumber = 1000;
//        Long t1 = System.currentTimeMillis();
//        System.out.println(String.format("开始时间:%d", t1));
//        for(Integer i = 0; i < testNumber; i++){
//
//            String bookNo = String.format("%07d", i);
//            Lottery lottery = new Lottery("G0132", "20123", bookNo, LocalDateTime.now(), 2, "10990001", "23990000");
//            lotteries.add(lottery);
//            if(lotteries.size() == 10000){
//                lotteryService.saveBatch(lotteries);
//                lotteries.clear();
//            }
//        }
//        if(lotteries.size() > 0){
//            lotteryService.saveBatch(lotteries);
//        }
//        Long t2 = System.currentTimeMillis();
//        System.out.println(String.format("结束时间:%d", t2));
//        System.out.println(String.format("耗费时间:%dms", t2-t1));
//        return String.format("插入%d条耗时:%dms.", testNumber, t2-t1);
//    }
}
