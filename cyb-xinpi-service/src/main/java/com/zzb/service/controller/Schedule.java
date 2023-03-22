package com.zzb.service.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zzb.common.dto.PageResponseResult;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3015;
import com.zzb.service.entity.Info3060;
import com.zzb.service.entity.Info3063;
import com.zzb.service.entity.Info3092;
import com.zzb.service.mapper.*;
import com.zzb.service.vo.GsglVo;
import com.zzb.service.vo.Info3060Vo;
import com.zzb.service.vo.InfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//1.主要用于标记配置类
@Configuration
// 2.开启定时任务
@EnableScheduling
@Slf4j
public class Schedule implements ApplicationContextAware {
    @Autowired
    public Info3092Mapper info3092Mapper;
    @Autowired
    public Info3015Mapper info3015Mapper;
    @Autowired
    public Info3063Mapper info3063Mapper;
    @Autowired
    public Stock2202Mapper stock2202Mapper;
    @Autowired
    private Info3060Mapper info3060Mapper;
    @Autowired
    public RedisTemplate redisTemplate;

  /*  //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() throws InterruptedException {

        log.info("基于注解(@Scheduled)的简单定时器demo: " + new Date());
        *//*TimeUnit.SECONDS.sleep(6);*//*
    }*/

    @Scheduled(cron = "0 0/1 * * * ?")
    private void configureTasks1() throws IOException {
        System.out.println("-----------------" + new Date() + "-----------------");
        Integer pagenum = 1;
        //紧急公告
        for (pagenum = 1; pagenum <= 3; pagenum++) {

            Page<Info3092> pageJinji = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3092> wrapper = new QueryWrapper<>();
            wrapper.lambda().orderByDesc(Info3092::getF001d);//根据时间倒序
            wrapper.lambda().eq(Info3092::getF006v, 2026);
            //准备返回格式的集合
            List<InfoVo> infoJinjiVos = new ArrayList<>();
            //开启分页查询
            pageJinji = info3092Mapper.selectPage(pageJinji, wrapper);
            List<Info3092> infoList = pageJinji.getRecords();
            for (Info3092 info : infoList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info, vo);
                infoJinjiVos.add(vo);
            }

            //最新公告
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, 1);//把日期往后加一天
            date = calendar.getTime();
            String format = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(date);
            try {
                date = new SimpleDateFormat("yyyy-MM-dd 00:00:00").parse(format);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //构建分页条件
            Page<Info3015> pageNew = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3015> wrapper1 = new QueryWrapper<>();
            wrapper1.select("seccode,secname,f001d,f002v,f003v,f004v").lambda()
                    .eq(Info3015::getF009v, "012015")
                    .eq(Info3015::getIsvalid, 1)
                    .lt(Info3015::getF001d, date)
                    .orderByDesc(Info3015::getF001d);
            //准备返回格式的集合
            ArrayList<InfoVo> infoNewVos = new ArrayList<>();
            //开启分页查询
            pageNew = info3015Mapper.selectPage(pageNew, wrapper1);
            List<Info3015> infoNewList = pageNew.getRecords();

            for (Info3015 info : infoNewList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info, vo);
                infoNewVos.add(vo);
            }

            //证监会公告
            Page<Info3063> pageZjh = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3063> wrapper2 = new QueryWrapper<>();
            wrapper2.lambda().orderByDesc(Info3063::getF001d);//根据时间倒序
            wrapper2.lambda().eq(Info3063::getSeccode, "9900046195");//证监会
            wrapper2.lambda().eq(Info3063::getF006v, "2010");//中国证监会最新更新
            //准备返回格式的集合
            ArrayList<InfoVo> infoZjhVos = new ArrayList<>();
            //开启分页查询
            pageZjh = info3063Mapper.selectPage(pageZjh, wrapper2);
            List<Info3063> infoZjhList = pageZjh.getRecords();

            for (Info3063 info : infoZjhList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info, vo);
                infoZjhVos.add(vo);
            }


            //深交所公告
            Page<Info3063> pageSjs = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3063> wrapper3 = new QueryWrapper<>();
            wrapper3.lambda().orderByDesc(Info3063::getF001d);//根据时间倒序
            wrapper3.lambda().eq(Info3063::getSeccode, "jysz0000001");//深圳证券交易所
            wrapper3.lambda().in(Info3063::getF006v, "2032", "2026", "2023");//中国证监会最新更新
            //准备返回格式的集合
            ArrayList<InfoVo> infoSjsVos = new ArrayList<>();
            //开启分页查询
            pageSjs = info3063Mapper.selectPage(pageSjs, wrapper3);
            List<Info3063> infoSjsList = pageSjs.getRecords();

            for (Info3063 info : infoSjsList) {
                //创建Vo
                InfoVo vo = new InfoVo();
                BeanUtils.copyProperties(info, vo);
                infoSjsVos.add(vo);
            }


            //公司概览
            Page<Info3015> pageGs = new Page<>(pagenum, 90);
            //构建查询条件
            QueryWrapper<Info3015> wrapper4 = new QueryWrapper<>();
            wrapper4.select("DISTINCT seccode,secname")
                    .lambda().eq(Info3015::getF009v, "012015")
                    .orderByAsc(Info3015::getSeccode);//去重  创业板
            //准备返回格式的集合
            ArrayList<GsglVo> infoGsVos = new ArrayList<>();
            //开启分页查询
            pageGs = info3015Mapper.selectPage(pageGs, wrapper4);
            List<Info3015> infoGsList = pageGs.getRecords();
            for (Info3015 info3015Gs : infoGsList) {
                //创建vo
                GsglVo vo = new GsglVo();
                vo.setSeccode(info3015Gs.getSeccode());
                vo.setSecname(info3015Gs.getSecname());
                infoGsVos.add(vo);

            }


            //深交所主板
            //构建分页条件
            Page<Info3060> pageSjsZb = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperSjsZb = new QueryWrapper<>();

            wrapperSjsZb.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v,"012002")//深交所主板
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVoSjsZb = new ArrayList<>();
            //开启分页查询
            pageSjsZb = info3060Mapper.selectPage(pageSjsZb, wrapperSjsZb);
            List<Info3060> infoListSjsZb = pageSjsZb.getRecords();

            for (Info3060 info : infoListSjsZb) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVoSjsZb.add(vo);
            }


            //深交所创业板
            Page<Info3060> pageSjsCyb = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperSjsCyb = new QueryWrapper<>();

            wrapperSjsCyb.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v,"012015")//深交所创业板
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVoSjsCyb = new ArrayList<>();
            //开启分页查询
            pageSjsCyb = info3060Mapper.selectPage(pageSjsCyb, wrapperSjsCyb);
            List<Info3060> infoListSjsCyb = pageSjsCyb.getRecords();

            for (Info3060 info : infoListSjsCyb) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVoSjsCyb.add(vo);
            }


            //上交所主板
            //构建分页条件
            Page<Info3060> pageShang = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperShang = new QueryWrapper<>();

            wrapperShang.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v,"012001")//上交所主板
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosShang = new ArrayList<>();
            //开启分页查询
            pageShang = info3060Mapper.selectPage(pageShang, wrapperShang);
            List<Info3060> infoListShang = pageShang.getRecords();

            for (Info3060 info : infoListShang) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosShang.add(vo);
            }



            //科创版

            //构建分页条件
            Page<Info3060> pageKcb = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperKcb = new QueryWrapper<>();

            wrapperKcb.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v,"012029")//科创版
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosKcb = new ArrayList<>();
            //开启分页查询
            pageKcb = info3060Mapper.selectPage(pageKcb, wrapperKcb);
            List<Info3060> infoListKcb = pageKcb.getRecords();

            for (Info3060 info : infoListKcb) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosKcb.add(vo);
            }


            //北交所审核
            //构建分页条件
            Page<Info3060> pageBjsSh = new Page<>(pagenum, 14);
            //构建查询条件
            QueryWrapper<Info3060> wrapperBjsSh = new QueryWrapper<>();

            wrapperBjsSh.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v,"012046")
                    .eq(Info3060::getF006v,"2104")//北交所审核信息披露
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosBjsSh = new ArrayList<>();
            //开启分页查询
            pageBjsSh = info3060Mapper.selectPage(pageBjsSh, wrapperBjsSh);
            List<Info3060> infoListBjsSh = pageBjsSh.getRecords();

            for (Info3060 info : infoListBjsSh) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosBjsSh.add(vo);
            }


            //北交所公开信息
            //构建分页条件
            Page<Info3060> pageBjsGk = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperBjsGk = new QueryWrapper<>();

            wrapperBjsGk.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v,"012046")
                    .eq(Info3060::getF006v,"2103")//北交所公开发行信息披露
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosBjsGk = new ArrayList<>();
            //开启分页查询
            pageBjsGk = info3060Mapper.selectPage(pageBjsGk, wrapperBjsGk);
            List<Info3060> infoListBjsGk = pageBjsGk.getRecords();

            for (Info3060 info : infoListBjsGk) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosBjsGk.add(vo);
            }

            //深交所all
            //构建分页条件
            Page<Info3060> pageSjsAll = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperSjsAll = new QueryWrapper<>();

            wrapperSjsAll.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .in(Info3060::getF009v, "012002","012015")//深交所
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosSjsAll = new ArrayList<>();
            //开启分页查询
            pageSjsAll = info3060Mapper.selectPage(pageSjsAll, wrapperSjsAll);
            List<Info3060> infoListSjsAll = pageSjsAll.getRecords();

            for (Info3060 info : infoListSjsAll) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosSjsAll.add(vo);
            }


            //北交所all
            //构建分页条件
            Page<Info3060> pageBjsAll = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperBjsAll = new QueryWrapper<>();

            wrapperBjsAll.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .eq(Info3060::getF009v, "012046")
                    .in(Info3060::getF006v, "2103","2104")//北交所
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosBjsAll = new ArrayList<>();
            //开启分页查询
            pageBjsAll = info3060Mapper.selectPage(pageBjsAll, wrapperBjsAll);
            List<Info3060> infoListBjsAll = pageBjsAll.getRecords();

            for (Info3060 info : infoListBjsAll) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosBjsAll.add(vo);
            }



            //上交所all
            //构建分页条件
            Page<Info3060> pageShangAll = new Page<>(pagenum, 20);
            //构建查询条件
            QueryWrapper<Info3060> wrapperShangAll = new QueryWrapper<>();

            wrapperShangAll.select("seccode,secname,f001d,f002v,f003v,f004v,f006v,f007v,f009v,f010v,f011v,memo").lambda()
                    .in(Info3060::getF009v, "012001","012029")//深交所
                    .orderByDesc(Info3060::getF001d);
            //准备返回格式的集合
            ArrayList<Info3060Vo> infoVosShangAll = new ArrayList<>();
            //开启分页查询
            pageShangAll = info3060Mapper.selectPage(pageShangAll, wrapperShangAll);
            List<Info3060> infoListShangAll = pageShangAll.getRecords();

            for (Info3060 info : infoListShangAll) {
                //创建Vo
                Info3060Vo vo = new Info3060Vo();
                BeanUtils.copyProperties(info, vo);
                infoVosShangAll.add(vo);
            }
            PageResponseResult resultBjsAll = new PageResponseResult(pagenum, 20, pageBjsAll.getTotal(), infoVosBjsAll);
            PageResponseResult resultShangAll = new PageResponseResult(pagenum, 20, pageShangAll.getTotal(), infoVosShangAll);
            PageResponseResult resultSjsAll = new PageResponseResult(pagenum, 20, pageSjsAll.getTotal(), infoVosSjsAll);
            PageResponseResult resultKcb = new PageResponseResult(pagenum, 20, pageKcb.getTotal(), infoVosKcb);
            PageResponseResult resultShang = new PageResponseResult(pagenum, 20, pageShang.getTotal(), infoVosShang);
            PageResponseResult resultBjsSh = new PageResponseResult(pagenum, 20, pageBjsSh.getTotal(), infoVosBjsSh);
            PageResponseResult resultBjsGk = new PageResponseResult(pagenum, 20, pageBjsGk.getTotal(), infoVosBjsGk);
            PageResponseResult resultSjsCyb = new PageResponseResult(pagenum, 20, pageSjsCyb.getTotal(), infoVoSjsCyb);
            PageResponseResult resultSjsZb = new PageResponseResult(pagenum, 20, pageSjsZb.getTotal(), infoVoSjsZb);
            PageResponseResult resultGs = new PageResponseResult(pagenum, 90, pageGs.getTotal(), infoGsVos);
            PageResponseResult resultSjs = new PageResponseResult(pagenum, 20, pageSjs.getTotal(), infoSjsVos);
            PageResponseResult resultZjh = new PageResponseResult(pagenum, 20, pageZjh.getTotal(), infoZjhVos);
            PageResponseResult resultNew = new PageResponseResult(pagenum, 20, pageNew.getTotal(), infoNewVos);
            PageResponseResult resultJinji = new PageResponseResult(pagenum, 20, pageJinji.getTotal(), infoJinjiVos);

            ValueOperations ops = redisTemplate.opsForValue();
            //紧急公告
            ops.set("jinji" + pagenum, resultJinji);
            ops.set("New" + pagenum, resultNew);
            ops.set("Zjh" + pagenum, resultZjh);
            ops.set("Sjs" + pagenum, resultSjs);
            ops.set("Gsgl" + pagenum, resultGs);
            ops.set("sjsZb"+pagenum,resultSjsZb);
            ops.set("sjsCyb"+pagenum,resultSjsCyb);
            ops.set("bjsSh"+pagenum,resultBjsSh);
            ops.set("bjsGk"+pagenum,resultBjsGk);
            ops.set("shangJs"+pagenum,resultShang);
            ops.set("kcb"+pagenum,resultKcb);
            ops.set("sjsAll"+pagenum,resultSjsAll);
            ops.set("bjsAll"+pagenum,resultBjsAll);
            ops.set("shangAll"+pagenum,resultShangAll);

        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}