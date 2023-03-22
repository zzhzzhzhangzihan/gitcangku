package com.zzb.service.controller;

import com.zzb.common.dto.ResponseResult;
import com.zzb.service.service.IInfo3060Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 招股说明书公告 前端控制器
 * </p>
 *
 * @author gang
 * @since 2023-02-20
 */
@RestController
@RequestMapping("/ipo/v1/")
@Api(tags = "招股说明书公告接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class Info3060Controller {

    @Autowired
    private IInfo3060Service info3060Service;


    @GetMapping("/sjszb/{pageindex}.json")
    @ApiOperation(value = "深交所主板", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOSjsList(@PathVariable Integer pageindex,
                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOSjsList(pageindex, pageSize);
    }

    @GetMapping("/sjscyb/{pageindex}.json")
    @ApiOperation(value = "深交所创业板", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOCybList(@PathVariable Integer pageindex,
                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOCybList(pageindex, pageSize);


    }
    @GetMapping("/sjsAll/{pageindex}.json")
    @ApiOperation(value = "深交所ipo公告", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOCybAllList(@PathVariable Integer pageindex,
                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOCybAllList(pageindex, pageSize);
    }

    @GetMapping("/bjssh/{pageindex}.json")
    @ApiOperation(value = "北交所审核信息披露", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOBjsList(@PathVariable Integer pageindex,
                                         @RequestParam(value = "pageSize", defaultValue = "14") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOBjsList(pageindex, pageSize);


    }

    @GetMapping("/bjsgkfx/{pageindex}.json")
    @ApiOperation(value = "北交所公开发行信息披露", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOBjsBjsList(@PathVariable Integer pageindex,
                                            @RequestParam(value = "pageSize", defaultValue = "14") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOBjsBjsList(pageindex, pageSize);


    }
    @GetMapping("/bjsAll/{pageindex}.json")
    @ApiOperation(value = "北交所ipo公告", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOBjsBjsAllList(@PathVariable Integer pageindex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOBjsBjsAllList(pageindex, pageSize);


    }

    @GetMapping("/shangzb/{pageindex}.json")
    @ApiOperation(value = "上交所主板", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOShangList(@PathVariable Integer pageindex,
                                            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOShangList(pageindex, pageSize);
    }
    @GetMapping("/kcb/{pageindex}.json")
    @ApiOperation(value = "上交所科创版", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOKcbList(@PathVariable Integer pageindex,
                                           @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOKcbList(pageindex, pageSize);
    }
    @GetMapping("/shangAll/{pageindex}.json")
    @ApiOperation(value = "上交所ipo公告", notes = "大刚")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseResult findIPOKcbAllList(@PathVariable Integer pageindex,
                                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3060Service.findIPOKcbAllList(pageindex, pageSize);
    }
}