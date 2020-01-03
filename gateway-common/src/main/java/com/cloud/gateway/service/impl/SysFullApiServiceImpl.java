package com.cloud.gateway.service.impl;

import com.cloud.gateway.entity.SysFullApi;
import com.cloud.gateway.mapper.SysApiDao;
import com.cloud.gateway.service.SysFullApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysFullApiServiceImpl implements SysFullApiService {

    @Autowired
    private SysApiDao sysApiDao;

    @Override
    public SysFullApi loadFullApi(String uri) {
        return null;
    }
}
