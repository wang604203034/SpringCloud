package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.springcloud.entities.Dept;

/**
 * 一个基于feign开发的接口，和mybatis很相似，使用接口带上注解开发
	对对外提供的服务进行调用，然后通过消费者的controller加载feign的service接口方法对外提供访问
 *
 */
@FeignClient(name="MICROSERVICECLOUD-DEPT",   //从微服务中调用,寻找的时候在接口的方法上填上对应的路径即可访问
											//第二个参数注入服务降级，如果访问失败则进入该类
				fallbackFactory=DeptClientServiceFallbackFactory.class) 
public interface DeptClientService {
	//@requestMapping填写的是微服务中的方法路径.我们不管方法是怎么实现的，只需要调用就行
	@RequestMapping(value = "/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept);
	
	@GetMapping(value ="/dept/get/{id}")
	public Dept get(@PathVariable("id") long id); //需要添加注解
	
	@GetMapping(value ="/dept/list")
	public List<Dept> list();
	/*
	  @RequestMapping(value = "/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept);
	
	@RequestMapping(value ="/dept/get/{id}",method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id); //需要添加注解
	
	@RequestMapping(value ="/dept/list",method = RequestMethod.GET)
	public List<Dept> list();*/
}
