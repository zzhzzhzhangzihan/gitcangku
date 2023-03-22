package com.zzb.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.common.dto.PageResponseResult;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3092;
import com.zzb.service.mapper.Info3092Mapper;
import com.zzb.service.service.IInfo3092Service;
import com.zzb.service.vo.InfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 深沪北交易所临时停牌公告 服务实现类
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
@Service
public class Info3092ServiceImpl extends ServiceImpl<Info3092Mapper, Info3092> implements IInfo3092Service {
    @Autowired
    private Info3092Mapper info3092Mapper;

    @Autowired
    public RedisTemplate redisTemplate;


    @Override
    public ResponseResult findAllList(Integer pageNum, Integer pageSize) {
        if (pageNum>3){
            //构建分页条件
            Page<Info3092> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3092> wrapper = new QueryWrapper<>();
            wrapper.lambda().orderByDesc(Info3092::getF001d);//根据时间倒序
            wrapper.lambda().eq(Info3092::getF006v,2026);
            //准备返回格式的集合
            List<InfoVo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3092Mapper.selectPage(page, wrapper);
            List<Info3092> infoList = page.getRecords();
            for (Info3092 info : infoList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info,vo);
                infoVos.add(vo);
            }
            PageResponseResult result =new PageResponseResult(pageNum,pageSize,page.getTotal(),infoVos);
            return  ResponseResult.okResult(result);
        }else {
            Object jinji = redisTemplate.opsForValue().get("jinji"+pageNum);
         /*   if (jinji==null){
                //构建分页条件
                Page<Info3092> page = new Page<>(pageNum, pageSize);
                //构建查询条件
                QueryWrapper<Info3092> wrapper = new QueryWrapper<>();
                wrapper.lambda().orderByDesc(Info3092::getF001d);//根据时间倒序
                wrapper.lambda().eq(Info3092::getF006v,2026);
                //准备返回格式的集合
                List<InfoVo> infoVos = new ArrayList<>();
                //开启分页查询
                page = info3092Mapper.selectPage(page, wrapper);
                List<Info3092> infoList = page.getRecords();
                for (Info3092 info : infoList) {
                    //创建Vo
                    InfoVo vo = new InfoVo();
                    BeanUtils.copyProperties(info,vo);
                    infoVos.add(vo);
                }
                PageResponseResult result =new PageResponseResult(pageNum,pageSize,page.getTotal(),infoVos);
                redisTemplate.opsForValue().set("jinji"+pageNum,result);
            }*/
            return ResponseResult.okResult(jinji);

        }


    }

    }

