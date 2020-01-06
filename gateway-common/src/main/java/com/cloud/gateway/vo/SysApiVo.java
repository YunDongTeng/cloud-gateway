package com.cloud.gateway.vo;

import com.cloud.gateway.entity.SysApi;
import java.util.List;

public class SysApiVo {

    private List<SysApi> apiList;

    private Integer count;

    public List<SysApi> getApiList() {
        return apiList;
    }

    public void setApiList(List<SysApi> apiList) {
        this.apiList = apiList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
