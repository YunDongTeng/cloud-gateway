package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysApiServer;
import org.springframework.stereotype.Repository;

@Repository
public interface SysApiServerDao {

    // 保存api服务端信息
    int save(SysApiServer sysApiServer);

    SysApiServer queryByApiId(Integer apiId);

}
