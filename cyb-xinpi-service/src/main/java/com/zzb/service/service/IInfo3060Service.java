package com.zzb.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3060;

/**
 * <p>
 * 招股说明书公告 服务类
 * </p>
 *
 * @author gang
 * @since 2023-02-20
 */

public interface IInfo3060Service extends IService<Info3060> {

    //深交所主板
    ResponseResult findIPOSjsList(Integer pageindex, Integer pageSize);

    //深交所创业板
    ResponseResult findIPOCybList(Integer pageindex, Integer pageSize);

    //北交所审核信息披露
    ResponseResult findIPOBjsList(Integer pageindex, Integer pageSize);

    //北交所公开发行信息披露
    ResponseResult findIPOBjsBjsList(Integer pageindex, Integer pageSize);

    //上交所主板
    ResponseResult findIPOShangList(Integer pageindex, Integer pageSize);

    //上交所科创版
    ResponseResult findIPOKcbList(Integer pageindex, Integer pageSize);

    //深交所all
    ResponseResult findIPOCybAllList(Integer pageindex, Integer pageSize);

    //北交所ipo公告
    ResponseResult findIPOBjsBjsAllList(Integer pageindex, Integer pageSize);

    //上交所ipo公告
    ResponseResult findIPOKcbAllList(Integer pageindex, Integer pageSize);
}
