package com.zxc.web.controller;

import com.zxc.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/17.
 */

@Api(value = "返回状态值解释", description = "返回状态值解释")
@RestController
@RequestMapping("/qhz/status")
@Slf4j
public class CommonsController {

    @ApiOperation(value = "状态一览表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "APP推送类型", value = "REG_PUSH(0,\"注册通知\"),FLOW_PUSH(1,\"拜访推送\"),CHICKEN_APPLY(2,\"报名申请\"),PASS_FORGET(3,\"忘记密码\"),OTHER_PUSH(4,\"其他推送\"),MESSAGE_PAYED(5,\"短信费用支付完成\");", required = true, paramType = "query"),
    })
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultDto list() {
        return null;
    }


}
