package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysApiParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysApiParamDao {

    // 批量保存
    void batchSave(List<SysApiParam> apiParams);

    List<SysApiParam> queryByApiId(Integer apiId);

}
