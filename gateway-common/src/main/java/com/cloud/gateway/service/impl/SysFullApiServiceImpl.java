package com.cloud.gateway.service.impl;

import com.cloud.gateway.entity.SysApi;
import com.cloud.gateway.entity.SysApiServer;
import com.cloud.gateway.entity.SysFullApi;
import com.cloud.gateway.entity.SysServerResponse;
import com.cloud.gateway.mapper.*;
import com.cloud.gateway.service.SysFullApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SysFullApiServiceImpl implements SysFullApiService {

    @Autowired
    private SysApiDao sysApiDao;

    @Autowired
    private SysApiParamDao sysApiParamDao;

    @Autowired
    private SysApiServerDao sysApiServerDao;

    @Autowired
    private SysServerParamDao sysServerParamDao;

    @Autowired
    private SysServerResponseDao sysServerResponseDao;

    @Override
    public SysFullApi loadFullApi(String uri) {


        SysFullApi sysFullApi = new SysFullApi();

        SysApi sysApi = sysApiDao.queryByUri(uri);
        if(sysApi != null){

            Integer apiId = sysApi.getId();

            sysFullApi.setApi(sysApi);

            sysFullApi.setApiParams(sysApiParamDao.queryByApiId(apiId));

            SysApiServer sysApiServer = sysApiServerDao.queryByApiId(apiId);
            sysFullApi.setApiServer(sysApiServer);

            sysFullApi.setServerParams(sysServerParamDao.queryByServerId(sysApiServer.getId()));

            sysFullApi.setApiResponse(sysServerResponseDao.queryByApiId(apiId));

        }


        return sysFullApi;
    }
}
