package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.SysServerParam;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysServerParamDao {

    void batchSave(List<SysServerParam> serverParams);

    List<SysServerParam> queryByServerId(Integer serverId);

}
