package com.cloud.gateway.web;

import com.cloud.gateway.common.response.Result;
import com.cloud.gateway.service.SysApiService;
import com.cloud.gateway.vo.SysApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SysApiController {

    @Autowired
    private SysApiService sysApiService;

    @PostMapping("/list")
    public Result<SysApiVo> list() {
        return new Result<SysApiVo>().success(null);
    }

}
