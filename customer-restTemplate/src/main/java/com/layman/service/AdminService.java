package com.layman.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/22 10:42
 * @Version 3.0
 **/
@Service
public class AdminService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String message) {
        return restTemplate.getForObject("http://PROVIDER/hi?message=" + message, String.class);
    }

    public String hiError(String message) {
        return "hi, your message is :\"" + message + "\"but request error";
    }
}
