package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysServerResponse;
import org.springframework.stereotype.Repository;

@Repository
public interface SysServerResponseDao {

    void save(SysServerResponse apiResponse);

    SysServerResponse queryByApiId(Integer apiId);
}
