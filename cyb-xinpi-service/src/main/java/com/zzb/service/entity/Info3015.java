
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
 * 沪深AB股公告链接表
 * </p>
 *
 * @author rui
 * @since 2022-06-20
 */

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("INFO3015")
@ApiModel(description="沪深AB股公告链接表")
public class Info3015 implements Serializable {

    private static final long serialVersionUID = 1L;


/**
     * 记录ID
     */

   @ApiModelProperty(value = "记录ID")
    @TableField("OBJECTID")
    private Long objectid;


/**
     * 主体ID
     */

    @ApiModelProperty(value = "主体ID")
    @TableField("RECID")
    private BigDecimal recid;


/*
     * 正文ID
     */

    @ApiModelProperty(value = "正文ID")
    @TableId(value = "TEXTID", type = IdType.ASSIGN_ID)
    private BigDecimal textid;


/*
     * 证券代码
     */

    @ApiModelProperty(value = "证券代码")
    @TableField("SECCODE")
    private String seccode;


/*
     * 证券简称
     */

    @ApiModelProperty(value = "证券简称")
    @TableField("SECNAME")
    private String secname;


/*
     * 公告日期
     */

    @ApiModelProperty(value = "公告日期")
    @TableField("F001D")
    private Date f001d;


/*
     * 公告标题
     */

    //@ApiModelProperty(value = "公告标题")
    @TableField("F002V")
    private String f002v;


/*
     * 公告地址
     */

    @ApiModelProperty(value = "公告地址")
    @TableField("F003V")
    private String f003v;


/*
     * 公告格式
     */

    @ApiModelProperty(value = "公告格式")
    @TableField("F004V")
    private String f004v;


/*
     * 公告大小
     */

    @ApiModelProperty(value = "公告大小")
    @TableField("F005N")
    private BigDecimal f005n;


/*
     * 信息分类
     */

    @ApiModelProperty(value = "信息分类")
    @TableField("F006V")
    private String f006v;


/*
     * 证券类别编码
     */

    @ApiModelProperty(value = "证券类别编码")
    @TableField("F007V")
    private String f007v;


/*
     * 证券类别
     */

   @ApiModelProperty(value = "证券类别")
    @TableField("F008V")
    private String f008v;


/*
     * 证券市场编码
     */

    @ApiModelProperty(value = "证券市场编码")
    @TableField("F009V")
    private String f009v;


/*
     * 证券市场
     */

   // @ApiModelProperty(value = "证券市场")
    @TableField("F010V")
    private String f010v;


/*
     * 公告大类编码
     */

   @ApiModelProperty(value = "公告大类编码")
    @TableField("F011V")
    private String f011v;


/*
     * 备注
     */

    @ApiModelProperty(value = "备注")
    @TableField("MEMO")
    private String memo;


/*
     * 修改时间
     */

   @ApiModelProperty(value = "修改时间")
    @TableField("MODTIME")
    private Date modtime;


/*
     * 录入时间
     */

    @ApiModelProperty(value = "录入时间")
    @TableField("RECTIME")
    private Date rectime;


/*
     * 数据日期
     */

  //  @ApiModelProperty(value = "数据日期")
    @TableField("ETLTIME")
    private Date etltime;


/*
     * 是否有效
     */

    @ApiModelProperty(value = "是否有效")
    @TableField("ISVALID")
    private String isvalid;


/*
     * 时间戳
     */

   @ApiModelProperty(value = "时间戳")
    @TableField("SEQID")
    private Long seqid;

    @TableField("clbz")
    private String clbz;

    @TableField("PUSHFILTER")
    private String pushfilter;

    @TableField("OPTYPE")
    private String optype;


/*
     * 主键字段MD5值
     */

   @ApiModelProperty(value = "主键字段MD5值")
    @TableField("ROWKEY")
    private String rowkey;


}

