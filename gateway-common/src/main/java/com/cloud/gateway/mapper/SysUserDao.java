package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserDao {

    void save(SysUser user);

    SysUser queryByUsername(String username);

}
