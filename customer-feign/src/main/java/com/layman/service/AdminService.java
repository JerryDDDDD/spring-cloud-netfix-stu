package com.layman.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @InterfaceName AdminService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/22 12:01
 * @Version 3.0
 **/
@FeignClient(value = "provider", fallback = AdminServiceHystrix.class)
public interface AdminService {

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam(value = "message") String message);
}
