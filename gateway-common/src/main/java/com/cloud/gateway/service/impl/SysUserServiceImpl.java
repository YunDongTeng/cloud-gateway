package com.cloud.gateway.service.impl;

import com.cloud.gateway.common.exception.AppException;
import com.cloud.gateway.dto.SysUserDto;
import com.cloud.gateway.entity.SysUser;
import com.cloud.gateway.mapper.SysUserDao;
import com.cloud.gateway.service.SysUserService;
import com.cloud.gateway.vo.SysUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class SysUserServiceImpl implements SysUserService {


    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public SysUserVo login(String username, String password) {

        SysUser sysUser = sysUserDao.queryByUsername(username);
        if (sysUser == null) {
            throw new AppException("用户名不存在");
        }

        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(sysUser.getPassword())) {
            throw new AppException("密码错误");
        }


        SysUserVo sysUserVo = new SysUserVo();
        BeanUtils.copyProperties(sysUser, sysUserVo);

        return sysUserVo;
    }

    @Override
    public void save(SysUserDto sysUserDto) {

        SysUser sysUser = new SysUser();

        BeanUtils.copyProperties(sysUser, sysUser);
        sysUser.setPassword(DigestUtils.md5DigestAsHex(sysUser.getPassword().getBytes()));
        sysUserDao.save(sysUser);
    }

    public static void main(String[] args){
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }
}
