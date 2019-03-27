package com.atguigu.myRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 自定义规则的负载均衡 
 * @author qqq
 *
 */
@Configuration
public class MyRuleConf {

	@Bean 
	//这个注入的方法名需要和已经注册的名称对应
	public IRule myRule(){
		//return new RandomRule(); //设置成随机的轮训 ,然后切记去app启动springboot中设置获取这个类的注解
		return new RandomRule_5ci(); //调用自定义5次轮询
	}
	
}
