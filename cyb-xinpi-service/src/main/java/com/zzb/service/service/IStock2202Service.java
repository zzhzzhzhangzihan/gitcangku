package com.zzb.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Stock2202;

/**
 * <p>
 * 证券交易特别提示 服务类
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
public interface IStock2202Service extends IService<Stock2202> {
   //交易提示今日
   ResponseResult findTodyAllList();
    //交易提示昨日
    ResponseResult findYesterdayAllList();
   //停复盘通知
    ResponseResult findF004vList();



}
