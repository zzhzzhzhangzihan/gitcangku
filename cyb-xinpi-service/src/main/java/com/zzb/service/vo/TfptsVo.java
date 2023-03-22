package com.zzb.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TfptsVo {

    private String secname;//证券名称
    private String seccode;//证卷代码
    private Date tdata;//停盘时间
    private Date fdata; //复盘时间
    private String cause; //停复盘原因
}
