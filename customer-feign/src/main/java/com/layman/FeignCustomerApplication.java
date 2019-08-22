package com.layman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName FeignCustomerApplication
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/22 11:43
 * @Version 3.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FeignCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignCustomerApplication.class, args);
    }
}
