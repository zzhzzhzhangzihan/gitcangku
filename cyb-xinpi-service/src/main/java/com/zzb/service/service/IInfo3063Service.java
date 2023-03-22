package com.zzb.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3063;

/**
 * <p>
 * 监管机构公告 服务类
 * </p>
 *
 * @author rui
 * @since 2022-07-20
 */
public interface IInfo3063Service extends IService<Info3063> {

    ResponseResult find9900046195List(Integer pageNum, Integer pageSize);

    ResponseResult findjysz0000001AllList(Integer pageNum, Integer pageSize);
}
