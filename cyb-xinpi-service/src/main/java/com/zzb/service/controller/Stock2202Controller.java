package com.zzb.service.controller;

import com.zzb.common.dto.ResponseResult;
import com.zzb.service.service.IStock2202Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 证券交易特别提示 前端控制器
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
@RestController
@RequestMapping("/cyb/v1/")
@Api(tags = "证券交易特别提示接口")
@CrossOrigin
public class Stock2202Controller {

    @Autowired
    private IStock2202Service stock2202Service;


    //昨日交易提示z
    @GetMapping("/all_Yesterday")
    @ApiOperation(value = "每日交易提示 - 昨日", notes = "大刚")
    public ResponseResult findYesterdayAllList() {
        return stock2202Service.findYesterdayAllList();

    }

    //停复盘提示
    @GetMapping("/all_F004V")
    @ApiOperation(value = "停复盘提示", notes = "大刚")
    public ResponseResult findF004vList() {
        return stock2202Service.findF004vList();
    }
    //今日交易提示
    @GetMapping("/all_Today")
    @ApiOperation(value = "每日交易提示 - 今日", notes = "大刚")
    public ResponseResult findTodyAllList() {
        return stock2202Service.findTodyAllList();

    }
}
