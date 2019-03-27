package com.atguigu.myRule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 每个提供者的接口访问5次
 * @author qqq
 *
 */
public class RandomRule_5ci extends AbstractLoadBalancerRule {

	private int total = 0;    //总共被调用的次数，目前要求每台被调用5次
	  private int currentIndex = 0;//当前提供服务的机器号
	  
	    public Server choose(ILoadBalancer lb, Object key) {
	        if (lb == null) {
	            return null;
	        }
	        Server server = null;
	 
	        while (server == null) {
	            if (Thread.interrupted()) {
	                return null;
	            }
	            List<Server> upList = lb.getReachableServers();
	            List<Server> allList = lb.getAllServers();
	 
	            int serverCount = allList.size();
	            if (serverCount == 0) {
	                /*
	                 * No servers. End regardless of pass, because subsequent passes
	                 * only get more restrictive.
	                 */
	                return null;
	            }
	            
//	            int index = rand.nextInt(serverCount);
//	            server = upList.get(index);
	            if(total < 5) //判断当前第几次，如果小于5则进入
	            {
	            server = upList.get(currentIndex); //获取当前服务者的下标，从0开始有三台 0,1,2
	            total++; //调用完成后加一次
	            }else {
	            total = 0; //如果超过5次那么，循环的次数为0
	            currentIndex++; //此时服务者变成 0 + 1 ......
	            if(currentIndex >= upList.size()) //如当前提供者超过了最大的提供者那么就回到第一次
	            {
	              currentIndex = 0;
	            }
	            
	            }
	            if (server == null) {
	                /*
	                 * The only time this should happen is if the server list were
	                 * somehow trimmed. This is a transient condition. Retry after
	                 * yielding.
	                 */
	                Thread.yield();
	                continue;
	            }
	 
	            if (server.isAlive()) {
	                return (server);
	            }
	 
	            // Shouldn't actually happen.. but must be transient or a bug.
	            server = null;
	            Thread.yield();
	        }
	 
	        return server;
	 
	    }
	@Override
	public Server choose(Object key) {
		// TODO Auto-generated method stub
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig arg0) {
		// TODO Auto-generated method stub
		
	}

}
