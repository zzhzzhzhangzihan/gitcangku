package com.zzb.service.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info3060Vo implements Serializable {




   //private BigDecimal textid;//文本id
   private String seccode;//证卷代码
   private String secname;//证券简称
   private Date f001d;//公告日期
   private String f002v;//公告标题

   private String f003v;//公告地址
   private String f004v;//公告格式

   private String f006v;
   private String f007v;
   private String f009v;
   private String f010v;
   private String f011v;
   private String memo;




}
