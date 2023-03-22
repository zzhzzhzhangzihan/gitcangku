package com.zzb.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


//分页
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVo implements Serializable {



    private BigDecimal textid;//文本id
    private String seccode;//证卷代码
    private String secname;//证券简称
    private Date f001d;//公告日期
    private String f002v;//公告标题

    private String f003v;//公告地址
    private String f004v;//公告格式
    private BigDecimal f005n;//公告大小





}