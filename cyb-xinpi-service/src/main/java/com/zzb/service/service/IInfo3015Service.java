package com.zzb.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3015;

/**
 * <p>
 * 沪深AB股公告链接表 服务类
 * </p>
 *
 * @author rui
 * @since 2022-06-20
 */
public interface IInfo3015Service extends IService<Info3015> {
    //通过证券简称查询公告信息
    ResponseResult findAllList(Integer pageNum, Integer pageSize);

    //公司概览
    ResponseResult findgsgl(Integer pageindex, Integer pageSize);
/*//搜索下拉框
    String searchZs(String q);
   //公司搜索
   GsglVo searchgs(String gs);
*/
}