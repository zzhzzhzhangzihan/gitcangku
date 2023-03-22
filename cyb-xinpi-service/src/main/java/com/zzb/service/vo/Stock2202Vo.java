package com.zzb.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock2202Vo {

    private String f004v;//时间种类
    private String f003v;//时间种类编码
    private String secname;//证券名称
    private String seccode;//证卷代码
    private Date f005d;//时间
    private String marketcode;//证券市场
    private  String url;//公司跳转个股行情地址


}
