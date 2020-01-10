package com.cloud.gateway.service;

import com.cloud.gateway.dto.SysApiDto;
import com.cloud.gateway.dto.SysFullApiDto;
import com.cloud.gateway.entity.SysApi;
import com.cloud.gateway.vo.SysApiVo;

public interface SysApiService {

    // 根据uri查询api
    SysApi queryByUri(String uri);

    // 查询api列表(分页)
    SysApiVo queryApi(SysApiDto sysApiDto);

    // 保存api信息
    Boolean saveFullApi(SysFullApiDto fullApiDto);

}
