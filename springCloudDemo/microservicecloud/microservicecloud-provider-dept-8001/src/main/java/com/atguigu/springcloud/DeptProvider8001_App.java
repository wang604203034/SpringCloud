package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableEurekaClient //是Eureka的客户端,注册到7001的server
@EnableDiscoveryClient //顶替了@EnableEurekaClient 所有的都可以被注册
public class DeptProvider8001_App {
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_App.class, args);
	}
}