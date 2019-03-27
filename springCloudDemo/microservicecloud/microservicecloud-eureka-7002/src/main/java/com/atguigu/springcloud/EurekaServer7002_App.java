package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//springboot启动
@SpringBootApplication
//启动Eureka控制中心，可以是其他的服务在注册中心注入
@EnableEurekaServer
public class EurekaServer7002_App {

	public static void main(String[] args) {
		//启动springboot
		SpringApplication.run(EurekaServer7002_App.class, args);
	}
}
