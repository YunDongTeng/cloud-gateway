package com.cloud.gateway.dto;

import com.cloud.gateway.common.page.BasePage;

public class SysApiDto extends BasePage {


    private String uri;

    private String name;


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartIndex() {
        return this.getPageNo() == null ? 0 : (this.getPageNo() - 1) * this.getPageSize();
    }

}
