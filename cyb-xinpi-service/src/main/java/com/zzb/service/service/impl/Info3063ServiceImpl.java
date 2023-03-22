package com.zzb.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.common.dto.PageResponseResult;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3063;
import com.zzb.service.mapper.Info3063Mapper;
import com.zzb.service.service.IInfo3063Service;
import com.zzb.service.vo.InfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 监管机构公告 服务实现类
 * </p>
 *
 * @author rui
 * @since 2022-07-20
 */
@Service
public class Info3063ServiceImpl extends ServiceImpl<Info3063Mapper, Info3063> implements IInfo3063Service {

    @Autowired
    private Info3063Mapper info3063Mapper;
    @Autowired
    public RedisTemplate redisTemplate;


    @Override
    public ResponseResult find9900046195List(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {
            //构建分页条件
            Page<Info3063> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3063> wrapper = new QueryWrapper<>();
            wrapper.lambda().orderByDesc(Info3063::getF001d);//根据时间倒序
            wrapper.lambda().eq(Info3063::getSeccode, "9900046195");//证监会
            wrapper.lambda().eq(Info3063::getF006v, "2010");//中国证监会最新更新
            //准备返回格式的集合
            ArrayList<InfoVo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3063Mapper.selectPage(page, wrapper);
            List<Info3063> infoList = page.getRecords();

            for (Info3063 info : infoList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }
            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object zjh = redisTemplate.opsForValue().get("Zjh" + pageNum);
            return ResponseResult.okResult(zjh);

        }
    }

    @Override
    public ResponseResult findjysz0000001AllList(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {
            //构建分页条件
            Page<Info3063> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3063> wrapper = new QueryWrapper<>();
            wrapper.lambda().orderByDesc(Info3063::getF001d);//根据时间倒序
            wrapper.lambda().eq(Info3063::getSeccode, "jysz0000001");//深圳证券交易所
            wrapper.lambda().in(Info3063::getF006v, "2032", "2026", "2023");//中国证监会最新更新
            //准备返回格式的集合
            ArrayList<InfoVo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3063Mapper.selectPage(page, wrapper);
            List<Info3063> infoList = page.getRecords();

            for (Info3063 info : infoList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }
            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        }else {
            Object sjs = redisTemplate.opsForValue().get("Sjs" + pageNum);
            return ResponseResult.okResult(sjs);
        }
    }
}
