package com.zzb.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * <p>
 * 证券交易特别提示
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("STOCK2202")
@ApiModel(description="证券交易特别提示")
public class Stock2202 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID      
     */
    @ApiModelProperty(value = "记录ID      ")
    @TableField("OBJECTID")
    private Long objectid;

    /**
     * 证券代码     
     */
    @ApiModelProperty(value = "证券代码     ")
    @TableId(value = "SECCODE", type = IdType.ASSIGN_ID)
    private String seccode;

    /**
     * 证券简称     
     */
    @ApiModelProperty(value = "证券简称     ")
    @TableField("SECNAME")
    private String secname;

    /**
     * 交易市场编码
     */
    @ApiModelProperty(value = "交易市场编码")
    @TableField("MARKETCODE")
    private String marketcode;

    /**
     * 证券类别     
     */
    @ApiModelProperty(value = "证券类别     ")
    @TableField("F001V")
    private String f001v;

    /**
     * 证券类别编码 
     */
    @ApiModelProperty(value = "证券类别编码 ")
    @TableField("F002V")
    private String f002v;

    /**
     * 事件种类编码 
     */
    @ApiModelProperty(value = "事件种类编码 ")
    @TableField("F003V")
    private String f003v;

    /**
     * 事件种类     
     */
    @ApiModelProperty(value = "事件种类     ")
    @TableField("F004V")
    private String f004v;

    /**
     * 发生日期     
     */
    @ApiModelProperty(value = "发生日期     ")
    @TableField("F005D")
    private Date f005d;

    /**
     * 事件内容     
     */
    @ApiModelProperty(value = "事件内容     ")
    @TableField("F006V")
    private String f006v;

    /**
     * 备注         
     */
    @ApiModelProperty(value = "备注         ")
    @TableField("MEMO")
    private String memo;

    /**
     * 录入时间     
     */
    @ApiModelProperty(value = "录入时间     ")
    @TableField("RECTIME")
    private Date rectime;

    /**
     * 修改时间     
     */
    @ApiModelProperty(value = "修改时间     ")
    @TableField("MODTIME")
    private Date modtime;

    @TableField("ISVALID")
    private String isvalid;

    /**
     * 时间戳       
     */
    @ApiModelProperty(value = "时间戳       ")
    @TableField("SEQID")
    private BigDecimal seqid;

    /**
     * 数据加工日期
     */
    @ApiModelProperty(value = "数据加工日期")
    @TableField("ETLTIME")
    private Date etltime;

    @TableField("change_code")
    private Long changeCode;

    /**
     * 主键字段MD5值
     */
    @ApiModelProperty(value = "主键字段MD5值")
    @TableField("ROWKEY")
    private String rowkey;


}
