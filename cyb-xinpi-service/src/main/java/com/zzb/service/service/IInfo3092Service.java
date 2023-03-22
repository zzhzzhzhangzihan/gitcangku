package com.zzb.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3092;


/**
 * <p>
 * 深沪北交易所临时停牌公告 服务类
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
public interface IInfo3092Service extends IService<Info3092> {

    ResponseResult findAllList(Integer pageNum, Integer pageSize);
}
