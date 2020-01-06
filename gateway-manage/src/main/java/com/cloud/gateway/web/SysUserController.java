package com.cloud.gateway.web;

import com.cloud.gateway.common.response.Result;
import com.cloud.gateway.dto.SysUserDto;
import com.cloud.gateway.service.SysUserService;
import com.cloud.gateway.vo.SysUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录接口
     *
     * @return
     */
    @PostMapping("/login")
    public Result<SysUserVo> login(@RequestBody SysUserDto userDto) {
        SysUserVo sysUserVo = sysUserService.login(userDto.getUsername(), userDto.getPassword());

        return new Result<SysUserVo>().success(sysUserVo);
    }

}
