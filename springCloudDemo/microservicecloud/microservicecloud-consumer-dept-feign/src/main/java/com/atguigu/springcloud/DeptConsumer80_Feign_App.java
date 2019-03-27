package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * 对外暴露的服务，用户访问的
 * 
 * @RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MyRule.class) 
 * 第一个参数是对外暴露服务名称，第二个参数是自定义的负载均衡类
 * 自定义的负载均衡类不能放在@ComponentScan 所在的同包或子包下 ，因为@springbootapp 注解自带
 * @author qqq
 *
 */
@SpringBootApplication
@EnableEurekaClient  //添加eureka客户端,把我们的负载均衡注册到注册中心
//扫描feign
@EnableFeignClients(basePackages={"com.atguigu.springcloud"}) //扫描的是api项目下的feign
@ComponentScan("com.atguigu.springcloud")
public class DeptConsumer80_Feign_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_Feign_App.class, args);
	}

}
