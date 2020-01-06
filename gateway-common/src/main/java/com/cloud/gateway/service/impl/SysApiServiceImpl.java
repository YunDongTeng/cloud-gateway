package com.cloud.gateway.service.impl;

import com.cloud.gateway.dto.SysApiDto;
import com.cloud.gateway.entity.SysApi;
import com.cloud.gateway.mapper.SysApiDao;
import com.cloud.gateway.service.SysApiService;
import com.cloud.gateway.vo.SysApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public SysApiVo queryApi(SysApiDto sysApiDto) {

        // 分页查询api信息
        List<SysApi> apiList = sysApiDao.queryApiByPage(sysApiDto.getName(), sysApiDto.getUri(),
                sysApiDto.getStartIndex(), sysApiDto.getPageSize());

        SysApiVo sysApiVo = new SysApiVo();

        sysApiVo.setApiList(apiList);
        sysApiVo.setCount(sysApiDao.getTotal(sysApiDto.getName(), sysApiDto.getUri()));

        return sysApiVo;
    }
}
