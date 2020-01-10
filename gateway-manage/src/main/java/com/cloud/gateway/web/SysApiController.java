package com.cloud.gateway.web;

import com.cloud.gateway.common.response.Result;
import com.cloud.gateway.dto.SysApiDto;
import com.cloud.gateway.dto.SysFullApiDto;
import com.cloud.gateway.service.SysApiService;
import com.cloud.gateway.vo.SysApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SysApiController {

    @Autowired
    private SysApiService sysApiService;

    /**
     * 分页查询api列表
     *
     * @param apiDto
     * @return
     */
    @PostMapping("/list")
    public Result<SysApiVo> list(@RequestBody SysApiDto apiDto) {
        return new Result<SysApiVo>().success(sysApiService.queryApi(apiDto));
    }

    /**
     * 保存api相关信息
     * @param fullApiDto
     * @return
     */
    @PostMapping("/save")
    public Result<Boolean> save(@RequestBody SysFullApiDto fullApiDto) {
        return new Result<Boolean>().success(sysApiService.saveFullApi(fullApiDto));
    }
}
