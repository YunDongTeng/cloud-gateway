package com.cloud.gateway.service;

import com.cloud.gateway.dto.SysUserDto;
import com.cloud.gateway.entity.SysUser;
import com.cloud.gateway.vo.SysUserVo;

public interface SysUserService {

    SysUserVo login(String username, String password);

    void save(SysUserDto sysUserDto);

}
