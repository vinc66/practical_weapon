package com.zxc.web.controller.admin;

import com.zxc.dto.ResultDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/17.
 */


@Api(value = "后台用户接口", description = "后台用户接口")
@RestController
@RequestMapping("/qhz/back/user")
@Slf4j
public class AdminUserController {


    @ApiOperation(value = "获取手机号验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", required = true, paramType = "query")
    })
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ResultDto code(@RequestParam(value = "phone") String phone) {
        return ResultDto.newInstance();
    }

}
