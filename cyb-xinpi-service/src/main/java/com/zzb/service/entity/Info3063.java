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
 * 监管机构公告
 * </p>
 *
 * @author rui
 * @since 2022-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("INFO3063")
@ApiModel(description="监管机构公告")
public class Info3063 implements Serializable {

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

    /**
     * 正文ID
     */
    @ApiModelProperty(value = "正文ID")
    @TableId(value = "TEXTID", type = IdType.ASSIGN_ID)
    private BigDecimal textid;

    /**
     * 证券代码
     */
    @ApiModelProperty(value = "证券代码")
    @TableField("SECCODE")
    private String seccode;

    /**
     * 证券简称
     */
    @ApiModelProperty(value = "证券简称")
    @TableField("SECNAME")
    private String secname;

    /**
     * 公告日期
     */
    @ApiModelProperty(value = "公告日期")
    @TableField("F001D")
    private Date f001d;

    /**
     * 公告标题
     */
    @ApiModelProperty(value = "公告标题")
    @TableField("F002V")
    private String f002v;

    /**
     * 公告地址
     */
    @ApiModelProperty(value = "公告地址")
    @TableField("F003V")
    private String f003v;

    /**
     * 公告格式
     */
    @ApiModelProperty(value = "公告格式")
    @TableField("F004V")
    private String f004v;

    /**
     * 公告大小
     */
    @ApiModelProperty(value = "公告大小")
    @TableField("F005N")
    private BigDecimal f005n;

    /**
     * 公告类型
     */
    @ApiModelProperty(value = "公告类型")
    @TableField("F006V")
    private String f006v;

    /**
     * 证券种类编码
     */
    @ApiModelProperty(value = "证券种类编码")
    @TableField("F007V")
    private String f007v;

    /**
     * 证类种类
     */
    @ApiModelProperty(value = "证类种类")
    @TableField("F008V")
    private String f008v;

    /**
     * 证券代码（标题提取）
     */
    @ApiModelProperty(value = "证券代码（标题提取）")
    @TableField("F009V")
    private String f009v;

    /**
     * 证券简称（关联中心库）
     */
    @ApiModelProperty(value = "证券简称（关联中心库）")
    @TableField("F010V")
    private String f010v;

    /**
     * 备注(原文类型，分为正文、文件、附件)
     */
    @ApiModelProperty(value = "备注(原文类型，分为正文、文件、附件)")
    @TableField("MEMO")
    private String memo;

    /**
     * 录入时间
     */
    @ApiModelProperty(value = "录入时间")
    @TableField("RECTIME")
    private Date rectime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("MODTIME")
    private Date modtime;

    /**
     * 数据日期
     */
    @ApiModelProperty(value = "数据日期")
    @TableField("ETLTIME")
    private Date etltime;

    /**
     * 操作标识
     */
    @ApiModelProperty(value = "操作标识")
    @TableField("OPTYPE")
    private String optype;

    /**
     * 主键字段MD5值
     */
    @ApiModelProperty(value = "主键字段MD5值")
    @TableField("ROWKEY")
    private String rowkey;


}
