package com.atguigu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;


/**
 *  restemplate
 *  提供了很多中便捷访问远程http的服务的方法
 *  是一种简单便捷的访问restful的服务模板类
 *  是spring4X以后出现的 ， 提供用于访问Rest服务的客户端模版工具集
 * @author qqq
 *
 */
@Configuration  //配置类 舍弃了xml
public class ConfigBean {
	@Bean
	@LoadBalanced //开启负载均衡Ribbon
	public RestTemplate geRestTemplate() {
		return new RestTemplate();
	}
	//设置负载均衡的其他方法
	@Bean //注入到容器,自定义的方法必须和这个方法同名 要么就把这个方法删除掉(测试好几遍了)
	public IRule myRule(){
		// 用随机算法代替默认的轮询算法
		return new RandomRule(); //随机算法，如果不加上该方法返回默认的轮训挨个的访问
		//return new RetryRule(); //重试，当某个提供服务的接口宕掉以后通过几次的重复错误访问，将会下次不在访问宕机的服务
	}
}
