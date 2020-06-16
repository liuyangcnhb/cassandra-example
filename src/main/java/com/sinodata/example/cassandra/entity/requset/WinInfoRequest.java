package com.sinodata.example.cassandra.entity.requset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FileName: WinInfoRequest
 * Author:  liuyang
 * Date:    2020/6/169:35
 * Description: 中奖信息测试数据
 * History:
 * <author>     <time>      <version>       <desc>
 */
@ApiModel(value = "中奖信息插入请求")
@Data
@NoArgsConstructor
public class WinInfoRequest {

    @ApiModelProperty(value = "方案代码", example = "G0132")
    private String projCode;

    @ApiModelProperty(value = "生产批次", example = "20123")
    private String prodBatchCode;

    @ApiModelProperty(value = "开始奖符头", example = "1")
    private Integer beginWinHead;

    @ApiModelProperty(value = "结束奖符头", example = "100")
    private Integer endWinHead;

    @ApiModelProperty(value = "开始奖符尾", example = "1")
    private Integer beginWinTail;

    @ApiModelProperty(value = "结束奖符尾", example = "100")
    private Integer endWinTail;

    @ApiModelProperty(value = "持久化策略", example = "1")
    private Integer concurrency;

}