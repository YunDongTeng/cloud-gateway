package com.cloud.gateway.service;

import com.cloud.gateway.entity.SysApi;

public interface SysApiService {

    // 根据uri查询api
    SysApi queryByUri(String uri);



}
