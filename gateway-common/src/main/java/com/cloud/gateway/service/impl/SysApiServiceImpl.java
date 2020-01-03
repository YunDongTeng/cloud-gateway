package com.cloud.gateway.service.impl;

import com.cloud.gateway.entity.SysApi;
import com.cloud.gateway.mapper.SysApiDao;
import com.cloud.gateway.service.SysApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TYD
 * api网关服务类
 */
@Service
public class SysApiServiceImpl implements SysApiService {

    @Autowired
    private SysApiDao sysApiDao;

    /**
     * 根据uri查询对应api
     *
     * @param uri
     * @return
     */
    @Override
    public SysApi queryByUri(String uri) {
        return sysApiDao.queryByUri(uri);
    }
}
