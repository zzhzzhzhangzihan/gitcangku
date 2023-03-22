package com.zzb.service.controller;


import com.zzb.common.dto.ResponseResult;
import com.zzb.service.service.IInfo3015Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 沪深AB股公告链接表 前端控制器
 * </p>
 *
 * @author rui
 * @since 2022-06-20
 */
@RestController
@RequestMapping("/cyb/v1/")
@Api(tags = "沪深AB股公告链接表接口")
@CrossOrigin
public class Info3015Controller {

    @Autowired
    private IInfo3015Service info3015Service;

    //最新公告
    @GetMapping("/new/{pageindex}.json")
    @ApiOperation(value = "最新公告信息", notes = "大刚")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseResult findAllList(@PathVariable Integer pageindex,
                                      @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        System.err.println(pageindex);
        return info3015Service.findAllList(pageindex, pageSize);
    }

    //公司概览
    @GetMapping("/gsgl/{pageindex}.json")
    @ApiOperation(value = "公司概览信息", notes = "大刚")
    @CrossOrigin(origins = "*",maxAge = 3600)
    public ResponseResult findgsgl(@PathVariable Integer pageindex,
                                   @RequestParam(value = "pageSize", defaultValue = "90") Integer pageSize) {
        System.err.println(pageindex);
        return info3015Service.findgsgl(pageindex, pageSize);
    }


 /*   //搜索下拉框
    @PostMapping(value = "/all_search_zhanshi&q={q}",produces="text/html;charset=UTF-8")
    @ApiOperation(value = "搜索下拉框", notes = "大刚")
    public String searchZs(@PathVariable String q ){

        return info3015Service.searchZs(q);
        }*/



    /*
    @PostMapping(value = "/all_search_gs")
    @ApiOperation(value = "公司检索", notes = "大刚")
    public GsglVo searchgs(@RequestParam String gs){

        return info3015Service.searchgs(gs);

}
*/
}





















