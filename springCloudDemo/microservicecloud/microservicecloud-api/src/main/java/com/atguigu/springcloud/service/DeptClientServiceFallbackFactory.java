package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.atguigu.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;
@Component //@component （把普通pojo实例化到spring容器中，相当于配置文件中的 <bean id="" class=""/>）
public class DeptClientServiceFallbackFactory 
								implements FallbackFactory<DeptClientService>{

	@Override
	public DeptClientService create(Throwable arg0) {
		return new DeptClientService() {
			@Override
			public Dept get(long id) {
				Dept dept = new Dept();
				dept.setDeptno(id);
				dept.setDname("此服务已经临时关闭....请稍后访问");
				dept.setDb_source("数据库服务关闭");
				return dept;
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}

			@Override
			public List<Dept> list() {
				return null;
			}
			
		};
	}

}
