package com.zzb.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzb.common.dto.ResponseResult;
import com.zzb.service.entity.Stock2202;
import com.zzb.service.mapper.Stock2202Mapper;
import com.zzb.service.service.IStock2202Service;
import com.zzb.service.vo.Stock2202Vo;
import com.zzb.service.vo.TfptsVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 * <p>
 * 证券交易特别提示 服务实现类
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
@Service
public class Stock2202ServiceImpl extends ServiceImpl<Stock2202Mapper, Stock2202> implements IStock2202Service {

    @Autowired
    private Stock2202Mapper stock2202Mapper;


    //今日
    @Override
    public ResponseResult findTodyAllList() {
        QueryWrapper<Stock2202> wrapper1 = new QueryWrapper<>();
      //  wrapper1.lambda().in(Stock2202::getF003v, "076015", "076016", "076019", "076037", "076044", "076053", "076054", "076066", "076074");
        wrapper1.select("seccode,secname,F004V,F005D,F006V");
         wrapper1.lambda().eq(Stock2202::getF005d, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        wrapper1.lambda().eq(Stock2202::getMarketcode, "012015");//创业板公告
        wrapper1.lambda().orderByDesc(Stock2202::getF004v);
        wrapper1.lambda().eq(Stock2202::getIsvalid, 1);
        List<Stock2202> stock2202s = stock2202Mapper.selectList(wrapper1);
        //准备返回格式的集合
        ArrayList<Stock2202Vo> list = new ArrayList<>();

        for (Stock2202 stock2202 : stock2202s) {
            Stock2202Vo vo = new Stock2202Vo();
            BeanUtils.copyProperties(stock2202, vo);
            list.add(vo);
        }
        return ResponseResult.okResult(list);
    }

    //昨日
    @Override
    public ResponseResult findYesterdayAllList() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往前减少一天
        date = calendar.getTime();

        QueryWrapper<Stock2202> wrapper1 = new QueryWrapper<>();
       // wrapper1.lambda().in(Stock2202::getF003v, "076015", "076016", "076019", "076037", "076044", "076053", "076054", "076066", "076074");
        wrapper1.select("seccode,secname,F004V,F005D,F006V");
        wrapper1.lambda().eq(Stock2202::getF005d, new SimpleDateFormat("yyyy-MM-dd").format(date));
        wrapper1.lambda().eq(Stock2202::getMarketcode, "012015");//创业板公告
        //wrapper1.lambda().orderByDesc(Stock2202::getF004v);
        wrapper1.lambda().eq(Stock2202::getIsvalid, 1);
        List<Stock2202> stock2202s = stock2202Mapper.selectList(wrapper1);
        //准备返回格式的集合
        ArrayList<Stock2202Vo> list = new ArrayList<>();

        for (Stock2202 stock2202 : stock2202s) {
            Stock2202Vo vo = new Stock2202Vo();
            BeanUtils.copyProperties(stock2202, vo);
            list.add(vo);
        }
        return ResponseResult.okResult(list);
    }

 //停复盘通知
    @Override
    public ResponseResult findF004vList() {
        //构建查询条件
        QueryWrapper<Stock2202> wrapper1 = new QueryWrapper<>();
        wrapper1.lambda().eq(Stock2202::getF003v, "076058");//停盘日期
        wrapper1.lambda().eq(Stock2202::getMarketcode, "012015");//创业板
        wrapper1.lambda().orderByDesc(Stock2202::getF005d);//排序
        wrapper1.lambda().eq(Stock2202::getIsvalid, 1).last("LIMIT 0,30");

        QueryWrapper<Stock2202> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Stock2202::getF003v, "076059");//复盘日期
        wrapper.lambda().eq(Stock2202::getMarketcode, "012015");//创业板
        wrapper.lambda().orderByDesc(Stock2202::getF005d);//排序
        wrapper.lambda().eq(Stock2202::getIsvalid, 1).last("LIMIT 0,30");
        //开始查询
        List<Stock2202> stock2202t = stock2202Mapper.selectList(wrapper1);
        List<Stock2202> stock2202f = stock2202Mapper.selectList(wrapper);
        //准备返回的类
        ArrayList<Object> list = new ArrayList<>();

        for (Stock2202 fp : stock2202f) {
            TfptsVo vo = new TfptsVo();
            for (Stock2202 tp : stock2202t) {
                if (fp.getSeccode().equals(tp.getSeccode())) {

                    vo.setSeccode(tp.getSeccode());
                    vo.setSecname(tp.getSecname());
                    vo.setTdata(tp.getF005d());
                    vo.setFdata(fp.getF005d());
                    vo.setCause("重大原因");

                }

            }
            list.add(vo);

        }
        return ResponseResult.okResult(list);

    }

}

