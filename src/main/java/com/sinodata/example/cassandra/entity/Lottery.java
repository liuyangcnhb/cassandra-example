package com.sinodata.example.cassandra.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * FileName: Lottery
 * Author:  liuyang
 * Date:    2020/6/910:17
 * Description: 以本存储的彩票信息
 * History:
 * <author>     <time>      <version>       <desc>
 */
@Data
@NoArgsConstructor
@Table("lottery")
public class Lottery implements Serializable {

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
     * 本号
     */
    @PrimaryKeyColumn(name = "book_no", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private String bookNo;

    /**
     * 操作序列
     */
    @PrimaryKeyColumn(name = "action_sequence", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private Integer actionSequence;

    /**
     * 彩票操作：1-出库，2-入库，3-入站，4-退站， 5-激活，6-销售
     */
    @PrimaryKeyColumn(name = "action_status", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Integer actionStatus;

    /**
     * 操作时间
     */
    @Column("action_date_time")
    private LocalDateTime actionDateTime;

    /**
     * 彩票操作所在仓库
     */
    @Column("wh_no")
    private String whNo;

    /**
     * 操作关联仓库
     */
    @Column("relation_wh_no")
    private String relationWhNo;

    /**
     * 0-未锁定 1-防盗锁 2-印刷错误锁 3-票面损坏锁
     */
    @Column("lock_flag")
    private Integer lockFlag;

    /**
     * 锁票用户ID
     */
    @Column("lock_user_no")
    private String lockUserNo;

    /**
     * 彩票锁定日期时间
     */
    @Column("lock_date_time")
    private LocalDateTime lockDatetime;

    /**
     * 彩票扫描物流码
     */
    @Column("logistics_no")
    private String logisticsNo;

    public Lottery(String projCode, String prodBatchCode, String bookNo, Integer actionSequence, Integer actionStatus, String whNo, String relationWhNo){
        this.projCode = projCode;
        this.prodBatchCode = prodBatchCode;
        this.bookNo = bookNo;
        this.actionSequence = actionSequence;
        this.actionStatus = actionStatus;
        this.actionDateTime = LocalDateTime.now();
        this.whNo = whNo;
        this.relationWhNo = relationWhNo;
        this.lockFlag = 0;
        this.lockUserNo = "";
        this.lockDatetime = null;
        this.logisticsNo = "";
    }
}
