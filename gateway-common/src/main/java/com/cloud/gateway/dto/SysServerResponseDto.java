package com.cloud.gateway.dto;

public class SysServerResponseDto {

    private String contentType;

    private String successDemo;

    private String failDemo;

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
