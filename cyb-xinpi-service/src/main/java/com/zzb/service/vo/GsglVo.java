package com.zzb.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GsglVo implements Serializable {

    private String seccode;//证卷代码
    private String secname;//证券简称
}
