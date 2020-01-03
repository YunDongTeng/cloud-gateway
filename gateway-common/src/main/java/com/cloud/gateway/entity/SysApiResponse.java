package com.cloud.gateway.entity;

public class SysApiResponse extends BaseEntity {

    private Integer id;

    private Integer apiId;

    private String contentType;

    private String successDemo;

    private String failDemo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSuccessDemo() {
        return successDemo;
    }

    public void setSuccessDemo(String successDemo) {
        this.successDemo = successDemo;
    }

    public String getFailDemo() {
        return failDemo;
    }

    public void setFailDemo(String failDemo) {
        this.failDemo = failDemo;
    }
}
