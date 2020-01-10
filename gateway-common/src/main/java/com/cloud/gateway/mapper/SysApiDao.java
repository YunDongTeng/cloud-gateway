package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysApi;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author TYD
 * sysApi dao接口
 */
@Repository
public interface SysApiDao {

    // 根据uri查询api信息
    SysApi queryByUri(String uri);

    // 分页查询网关api
    List<SysApi> queryApiByPage(@Param("name") String name,
                                @Param("uri") String uri,
                                @Param("status") String status,
                                @Param("start") Integer start,
                                @Param("pageSize") Integer pageSize);
    // 获取总数
    Integer getTotal(@Param("name") String name, @Param("uri") String uri, @Param("status")String status);

    int save(SysApi sysApi);
}
