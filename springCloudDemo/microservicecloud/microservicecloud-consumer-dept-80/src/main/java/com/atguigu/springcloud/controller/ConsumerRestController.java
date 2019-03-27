package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.atguigu.springcloud.entities.Dept;

/**
 * 基于RestTemplate,对外暴露服务,供用户访问
 * @author qqq
 *
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerRestController {
	
	/**
	 *  * 使用这个必须指定完整的访问路径
	 	* 使用restTemplate访问restful接口非常的简单粗暴无脑
   		* (url, requestMap, ResponseBean.class) 这三个参数分别代表
   		   REST请求地址, 请求参数, Http响应转换被转换成的对象类型
	 */
	
	//指定完整的访问路径
//	private final String REST_URL_PREFIX = "http://localhost:8001";
	private final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT"; //直接访问微服务名称.不用关心端口ip问题了
	//注入RestTemplate
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/add")
	public Boolean add(Dept dept){
										//访问提供者的requestmapper()中的地址
		return restTemplate.postForObject(REST_URL_PREFIX +"/dept/add", dept, Boolean.class);
	}
	@RequestMapping(value="/get/{id}",method=RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id){
		return restTemplate.getForObject(REST_URL_PREFIX +"/dept/get/"+id, Dept.class);
	}
	@RequestMapping(value="/list")
	public List<Dept> list(){
		return restTemplate.getForObject(REST_URL_PREFIX +"/dept/list", List.class);
	}
}
