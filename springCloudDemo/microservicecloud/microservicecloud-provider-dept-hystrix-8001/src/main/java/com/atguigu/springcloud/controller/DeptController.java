package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController {
	@Autowired
	private DeptService service;

	@RequestMapping(value = "/dept/ss")
	public String ss() {
		System.out.println("你好");
		return "十四时代";
	}

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	//添加如果出现异常处理异常的注解
	@HystrixCommand(fallbackMethod="ExceptionGet")
	public Dept get(@PathVariable("id") Long id) {
		Dept dept = service.get(id);
		if(dept == null){
			throw new RuntimeException("查无此人!");
		}
		return dept;
	}
	//定义上述@HystrixCommand注解对应的抛出异常方法
	public Dept ExceptionGet(@PathVariable("id") Long id ){
		Dept dept = new Dept();
		dept.setDeptno(id);
		dept.setDname("没有查到此用户对应的id");
		dept.setDb_source("该数据库没有此用户");
		return dept;
	}
	
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return service.list();
	}

}
