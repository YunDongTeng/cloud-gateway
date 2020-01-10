package com.cloud.gateway.service.impl;

import com.cloud.gateway.dto.SysApiDto;
import com.cloud.gateway.dto.SysApiParamDto;
import com.cloud.gateway.dto.SysFullApiDto;
import com.cloud.gateway.dto.SysServerParamDto;
import com.cloud.gateway.entity.*;
import com.cloud.gateway.mapper.*;
import com.cloud.gateway.service.SysApiService;
import com.cloud.gateway.vo.SysApiVo;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author TYD
 * api网关服务类
 */
@Service
public class SysApiServiceImpl implements SysApiService {

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

    @Override
    public SysApiVo queryApi(SysApiDto sysApiDto) {

        // 分页查询api信息
        List<SysApi> apiList = sysApiDao.queryApiByPage(sysApiDto.getName(),
                sysApiDto.getUri(),
                sysApiDto.getStatus(),
                sysApiDto.getStartIndex(),
                sysApiDto.getPageSize());

        SysApiVo sysApiVo = new SysApiVo();

        sysApiVo.setApiList(apiList);
        sysApiVo.setCount(sysApiDao.getTotal(sysApiDto.getName(), sysApiDto.getUri(), sysApiDto.getStatus()));

        return sysApiVo;
    }

    @Transactional
    @Override
    public Boolean saveFullApi(SysFullApiDto fullApiDto) {

        // 1.校验参数

        // 2.保存api信息
        try {
            SysApi api = new SysApi();
            BeanUtils.copyProperties(fullApiDto.getApi(), api);
            api.preSave();

            sysApiDao.save(api);

            // 3.保存api param信息
            List<SysApiParam> apiParams = Lists.newArrayList();

            for(SysApiParamDto e: fullApiDto.getApiParamList()){
                SysApiParam sysApiParam = new SysApiParam();
                BeanUtils.copyProperties(e, sysApiParam);

                sysApiParam.setApiId(api.getId());

                sysApiParam.preSave();
                apiParams.add(sysApiParam);
            }

            sysApiParamDao.batchSave(apiParams);


            // 4.保存api server信息
            SysApiServer apiServer = new SysApiServer();

            BeanUtils.copyProperties(fullApiDto.getApiServer(), apiServer);
            apiServer.setApiId(api.getId());

            apiServer.preSave();
            sysApiServerDao.save(apiServer);

            // 5.保存server param信息
            List<SysServerParam> serverParams = Lists.newArrayList();

            for(SysServerParamDto e: fullApiDto.getServerParamList()){
                SysServerParam serverParam = new SysServerParam();

                BeanUtils.copyProperties(e, serverParam);
                serverParam.preSave();
                serverParam.setApiServerId(apiServer.getId());
                serverParams.add(serverParam);
            }

            sysServerParamDao.batchSave(serverParams);

            // 6.保存server response信息
            SysServerResponse sysServerResponse = new SysServerResponse();
            BeanUtils.copyProperties(fullApiDto.getServerResponse(), sysServerResponse);
            sysServerResponse.setApiId(api.getId());
            sysServerResponse.preSave();
            sysServerResponseDao.save(sysServerResponse);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
