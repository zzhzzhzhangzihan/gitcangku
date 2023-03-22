package com.zzb.service.controller;

import com.zzb.common.dto.ResponseResult;
import com.zzb.service.service.IInfo3092Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 深沪北交易所临时停牌公告 前端控制器
 * </p>
 *
 * @author gang
 * @since 2022-07-12
 */
@RestController
@RequestMapping("/cyb/v1/")
@Api(tags = "深沪北交易所临时停牌公告接口")
@CrossOrigin
public class Info3092Controller {

    @Autowired
    private IInfo3092Service info3092Service;

    //紧急公告
    @GetMapping("/jjgg/{pageindex}.json")
    @ApiOperation(value = "紧急公告信息", notes = "大刚")
    public ResponseResult find019901List(@PathVariable Integer pageindex,
                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3092Service.findAllList(pageindex, pageSize);

    }
}