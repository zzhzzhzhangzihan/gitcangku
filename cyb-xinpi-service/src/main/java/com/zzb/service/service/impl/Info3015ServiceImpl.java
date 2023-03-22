package com.zzb.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.common.dto.PageResponseResult;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Info3015;
import com.zzb.service.mapper.Info3015Mapper;
import com.zzb.service.service.IInfo3015Service;
import com.zzb.service.vo.GsglVo;
import com.zzb.service.vo.InfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>
 * 沪深AB股公告链接表 服务实现类
 * </p>
 *
 * @author rui
 * @since 2022-06-20
 */
@Service
public class Info3015ServiceImpl extends ServiceImpl<Info3015Mapper, Info3015> implements IInfo3015Service {
    @Autowired
    private Info3015Mapper info3015Mapper;
    @Autowired
    public RedisTemplate redisTemplate;

    //通过证券简称查询公告信息
    @Override
    public ResponseResult findAllList(Integer pageNum, Integer pageSize) {

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
        if (pageNum > 3) {

        //构建分页条件
        Page<Info3015> page = new Page<>(pageNum, pageSize);
        //构建查询条件
        QueryWrapper<Info3015> wrapper = new QueryWrapper<>();
        Info3015 info3015 = new Info3015();
        wrapper.select("seccode,secname,f001d,f002v,f003v,f004v").lambda()
                .eq(Info3015::getF009v, "012015")
                .eq(Info3015::getIsvalid, 1)
                .lt(Info3015::getF001d, date)
                .orderByDesc(Info3015::getF001d);
        //准备返回格式的集合
        ArrayList<InfoVo> infoVos = new ArrayList<>();
        //开启分页查询
        page = info3015Mapper.selectPage(page, wrapper);
        List<Info3015> infoList = page.getRecords();

        for (Info3015 info : infoList) {
            //创建Vo
            InfoVo vo = new InfoVo();
            BeanUtils.copyProperties(info, vo);
            infoVos.add(vo);
        }

        PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
        return ResponseResult.okResult(result);
    }else {
            Object New = redisTemplate.opsForValue().get("New"+pageNum);
            return  ResponseResult.okResult(New);
        }

}

    //公司概览
    @Override
    public ResponseResult findgsgl(Integer pageNum, Integer pageSize) {
        if (pageNum > 3) {
            //构建分页条件
            Page<Info3015> page = new Page<>(pageNum, pageSize);
            //构建查询条件
            QueryWrapper<Info3015> wrapper = new QueryWrapper<>();
            wrapper.select("DISTINCT seccode,secname")
                    .lambda().eq(Info3015::getF009v, "012015")
                    .orderByAsc(Info3015::getSeccode);//去重  创业板
            //准备返回格式的集合
            ArrayList<GsglVo> infoVos = new ArrayList<>();
            //开启分页查询
            page = info3015Mapper.selectPage(page, wrapper);
            List<Info3015> infoList = page.getRecords();
            for (Info3015 info3015 : infoList) {
                //创建vo
                GsglVo vo = new GsglVo();
                vo.setSeccode(info3015.getSeccode());
                vo.setSecname(info3015.getSecname());
                infoVos.add(vo);

            }

            PageResponseResult result = new PageResponseResult(pageNum, pageSize, page.getTotal(), infoVos);
            return ResponseResult.okResult(result);
        } else {
            Object gsgl = redisTemplate.opsForValue().get("Gsgl" + pageNum);
            return ResponseResult.okResult(gsgl);
        }
    }

/*    //判断是否为数字
    public static boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");

        return pattern.matcher(str).matches();

    }*/

   /* @Override
    public String searchZs(String q) {
        boolean numeric = isNumeric(q);
        QueryWrapper<Info3015> wrapper = new QueryWrapper<>();
        if (numeric == true) {
            wrapper.select("DISTINCT seccode,secname,f009v").lambda().like(Info3015::getSeccode, q);
        } else {
            wrapper.select("DISTINCT seccode,secname,f009v").lambda().like(Info3015::getSecname, q);
        }
        wrapper.lambda().eq(Info3015::getF009v, "012015");
        wrapper.lambda().last("LIMIT 0,10");
        List<Info3015> info3015List = info3015Mapper.selectList(wrapper);
        String search = "";
        ArrayList<Object> list = new ArrayList<>();
        for (Info3015 info3015 : info3015List) {
            //首字母转汉字
            String spells = new ChineseCharacterHelper().getSpells(info3015.getSecname());
            search = info3015.getSeccode() + "|" + info3015.getSecname() + "|" + spells + "|" + info3015.getF009v() + "\r\n";
            list.add(search);
        }
        //将list转为字符串
        String resultString = "";
        if (list == null && list.size() == 0) {
            System.out.println("list中的内容为空！");
        } else {
            String[] strArrStrings = new String[list.size()];
            for (int i = 0; i <= list.size() - 1; i++) {
                strArrStrings[i] = (String) list.get(i);
            }
            for (int j = 0; j <= strArrStrings.length - 1; j++) {
                if (j < strArrStrings.length - 1) {
                    resultString += strArrStrings[j];
                } else {
                    resultString += strArrStrings[j];
                }
            }
        }
        return resultString;
    }

    @Override
    public GsglVo searchgs(String gs) {
        QueryWrapper<Info3015> wrapper = new QueryWrapper<>();
        wrapper.select("DISTINCT seccode,secname")
                .lambda()
                .eq(Info3015::getSeccode, gs);
        Info3015 selectOne = info3015Mapper.selectOne(wrapper);
        String seccode = selectOne.getSeccode();
        String secname = selectOne.getSecname();
        GsglVo vo = new GsglVo();
        vo.setSeccode(seccode);
        vo.setSecname(secname);
        return vo;
    }*/
}