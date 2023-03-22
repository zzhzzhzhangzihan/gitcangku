package com.zzb.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.common.dto.PageResponseResult;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3060;
import com.zzb.service.mapper.Info3060Mapper;
import com.zzb.service.service.IInfo3060Service;
import com.zzb.service.vo.Info3060Vo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 招股说明书公告 服务实现类
 * </p>
 *
 * @author gang
 * @since 2023-02-20
 */
@Service
public class Info3060ServiceImpl extends ServiceImpl<Info3060Mapper, Info3060> implements IInfo3060Service {

    @Autowired
    private Info3060Mapper info3060Mapper;
    @Autowired
    public RedisTemplate redisTemplate;

    @Override
    public ResponseResult findIPOSjsList(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {

            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012002")//深交所主板
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("sjsZb" + pageNum);
            return ResponseResult.okResult(New);
        }

    }

    @Override
    public ResponseResult findIPOCybList(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {

            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012015")//深交所创业板
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("sjsCyb" + pageNum);
            return ResponseResult.okResult(New);
        }


    }

    @Override
    public ResponseResult findIPOBjsList(Integer pageNum, Integer pageSize) {

        if (pageNum > 3) {

            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012046")
                    .eq(Info3060::getF006v, "2104")//北交所审核信息披露
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("bjsSh" + pageNum);
            return ResponseResult.okResult(New);
        }

    }

    @Override
    public ResponseResult findIPOBjsBjsList(Integer pageNum, Integer pageSize) {


        if (pageNum > 3) {

            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012046")
                    .eq(Info3060::getF006v, "2103")//北交所公开发行信息披露
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("bjsGk" + pageNum);
            return ResponseResult.okResult(New);
        }

    }

    @Override
    public ResponseResult findIPOShangList(Integer pageNum, Integer pageSize) {

        if (pageNum > 3) {

            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012001")//上交所主板
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("shangJs" + pageNum);
            return ResponseResult.okResult(New);
        }

    }

    @Override
    public ResponseResult findIPOKcbList(Integer pageNum, Integer pageSize) {

        if (pageNum > 3) {
            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012029")//科创版
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("kcb" + pageNum);
            return ResponseResult.okResult(New);
        }

    }

    //深交所all
    @Override
    public ResponseResult findIPOCybAllList(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {
            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .in(Info3060::getF009v, "012002","012015")//深交所
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("sjsAll" + pageNum);
            return ResponseResult.okResult(New);
        }
    }

    //北交所all
    @Override
    public ResponseResult findIPOBjsBjsAllList(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {
            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012046")
                    .in(Info3060::getF006v, "2103","2104")//北交所
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("bjsAll" + pageNum);
            return ResponseResult.okResult(New);
        }
    }

    //上交所all
    @Override
    public ResponseResult findIPOKcbAllList(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {
            //构建分页条件
            Page<Info3060> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3060> wrapper = new QueryWrapper<>();

            wrapper.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .in(Info3060::getF009v, "012001","012029")//深交所
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3060Mapper.selectPage(page, wrapper);
            List<Info3060> infoList = page.getRecords();

            for (Info3060 info : infoList) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVos.add(vo);
            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object New = redisTemplate.opsForValue().get("shangAll" + pageNum);
            return ResponseResult.okResult(New);
        }
    }
}
