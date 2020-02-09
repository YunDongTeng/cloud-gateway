package com.cloud.gateway.mapper;

import com.cloud.gateway.entity.Org;
import org.springframework.stereotype.Repository;

@Repository
public interface SysOrgDao {

    void save(Org org);
}
