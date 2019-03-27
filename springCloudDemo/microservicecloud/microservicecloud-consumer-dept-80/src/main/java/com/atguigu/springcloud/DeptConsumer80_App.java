package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.atguigu.myRule.MyRuleConf;

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
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration = MyRuleConf.class) //第一个参数是对外暴露服务名称，第二个参数是自定义的负载均衡类
public class DeptConsumer80_App {

	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}

}
