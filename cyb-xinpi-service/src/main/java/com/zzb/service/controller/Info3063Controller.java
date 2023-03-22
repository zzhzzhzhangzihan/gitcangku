package com.zzb.service.controller;

import com.zzb.common.dto.ResponseResult;
import com.zzb.service.service.IInfo3063Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 监管机构公告 前端控制器
 * </p>
 *
 * @author rui
 * @since 2022-07-20
 */
@RestController
@RequestMapping("/cyb/v1/")
@Api(tags = "监管机构公告接口")
@CrossOrigin
public class Info3063Controller {

    @Autowired
    private IInfo3063Service info3063Service;

    //证监会公告
    @GetMapping("/zjhgg/{pageindex}.json")
    @ApiOperation(value = "证监会公告", notes = "大刚")
    public ResponseResult find9900046195List(@PathVariable  Integer pageindex,
                                             @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3063Service.find9900046195List(pageindex, pageSize);

    }

    //深交所公告
    @GetMapping("/sjsgg/{pageindex}.json")
    @ApiOperation(value = "深交所公告", notes = "大刚")
    public ResponseResult findjysz0000001List(@PathVariable Integer pageindex,
                                              @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3063Service.findjysz0000001AllList(pageindex, pageSize);
    }
}