package com.layman.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName AdminServiceHystrix
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/22 21:06
 * @Version 3.0
 **/
@Component
public class AdminServiceHystrix implements AdminService {

    @Override
    public String sayHi(String message) {
        return "Hi, your message is:\"" + message + "\" but request error";
    }
}
