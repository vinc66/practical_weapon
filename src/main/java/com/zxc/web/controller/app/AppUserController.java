package com.zxc.web.controller.app;

import com.zxc.dto.ResultDto;
import com.zxc.service.CommonService;
import com.zxc.service.app.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/4/17.
 */

@Api(value = "app用户信息", description = "app用户信息")
@RestController
@RequestMapping("/qhz/user")
@Slf4j
public class AppUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "获取手机号验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", required = true, paramType = "query")
    })
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ResultDto code(@RequestParam(value = "phone") String phone) {

        return ResultDto.newInstance();
    }
}
