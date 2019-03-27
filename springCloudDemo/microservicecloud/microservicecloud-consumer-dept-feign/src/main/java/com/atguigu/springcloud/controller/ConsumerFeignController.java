package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;


/**
 * 通过feign使消费者访问
 * @author qqq
 *		public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerFeignController {
	//注入写好并且带有feign的service
	@Autowired
	private DeptClientService service;
	//编写对外提供的方法
	@GetMapping(value = "/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id){
		return service.get(id);
	}
	@GetMapping(value = "/dept/list")
	public List<Dept> list(){
		return service.list();
	}
	@RequestMapping(value = "/dept/add",method=RequestMethod.POST) 
	public Boolean add(@RequestBody Dept dept){
		return service.add(dept);
	}
	
	/**
	 * @RequestMapping(value = "/dept/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id){
		return service.get(id);
	}
	@RequestMapping(value = "/dept/list",method=RequestMethod.GET)
	public List<Dept> list(){
		return service.list();
	}
	@RequestMapping(value = "/dept/add",method=RequestMethod.POST) //无法在url后面添加了,会强制将post转换成get
	public Boolean add(@RequestBody Dept dept){
		return service.add(dept);
	}
	 */
	
}
