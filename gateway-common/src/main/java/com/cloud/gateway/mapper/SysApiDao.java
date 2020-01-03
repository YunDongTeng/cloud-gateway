package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysApi;
import org.springframework.stereotype.Repository;

/**
 * @author TYD
 * sysApi dao接口
 */
@Repository
public interface SysApiDao {

    // 根据uri查询api信息
    SysApi queryByUri(String uri);

}
