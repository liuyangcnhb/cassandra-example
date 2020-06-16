package com.sinodata.example.cassandra.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

/**
 * FileName: WinInfo
 * Author:  liuyang
 * Date:    2020/6/169:08
 * Description: 彩票中奖信息
 * History:
 * <author>     <time>      <version>       <desc>
 */

@Data
@NoArgsConstructor
@Table("win_info")
public class WinInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 方案代码
     */
    @PrimaryKeyColumn(name = "proj_code", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String projCode;

    /**
     * 生产批次
     */
    @PrimaryKeyColumn(name = "prod_batch_code", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String prodBatchCode;

    /**
     * 奖符前7位
     */
    @PrimaryKeyColumn(name = "win_head", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String winHead;

    /**
     * 奖符后2位
     */
    @PrimaryKeyColumn(name = "win_tail", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private String winTail;

    /**
     * 中奖信息
     */
    @Column("win_message")
    private String winMessage;


    public WinInfo(String projCode, String prodBatchCode, String winHead, String winTail, String winMessage){
        this.projCode = projCode;
        this.prodBatchCode = prodBatchCode;
        this.winHead = winHead;
        this.winTail = winTail;
        this.winMessage = winMessage;
    }
}
