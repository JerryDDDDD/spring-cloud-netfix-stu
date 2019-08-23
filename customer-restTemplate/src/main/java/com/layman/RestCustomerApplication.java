package com.layman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * @ClassName RestCustomerApplication
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/8/22 10:38
 * @Version 3.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrix
public class RestCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestCustomerApplication.class, args);
    }
}
