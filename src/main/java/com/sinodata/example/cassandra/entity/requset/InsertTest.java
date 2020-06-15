package com.sinodata.example.cassandra.entity.requset;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jnr.ffi.annotations.In;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FileName: InsertTest
 * Author:  liuyang
 * Date:    2020/6/1115:58
 * Description: 插入测试
 * History:
 * <author>     <time>      <version>       <desc>
 */
@ApiModel(value = "插入测试请求")
@Data
@NoArgsConstructor
public class InsertTest {

    @ApiModelProperty(value = "方案代码", example = "G0132")
    private String projCode;

    @ApiModelProperty(value = "生产批次", example = "20123")
    private String prodBatchCode;

    @ApiModelProperty(value = "开始本号", example = "1")
    private Integer beginBookNo;

    @ApiModelProperty(value = "结束本号", example = "1000")
    private Integer endBookNo;

    @ApiModelProperty(value = "持久化策略", example = "1")
    private Integer concurrency;

}
