package com.microservice.base.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.base.client.UserFeignClient2;
import com.microservice.base.entity.ComplexBean1;
import com.microservice.base.entity.ComplexBean2;
import com.microservice.base.entity.Role;
import com.microservice.base.entity.User;

//事实证明最新版本的fegin也支持GetMapping这种组合注解了
@RestController
@RequestMapping("/feign2")
public class FeignController2 {

	@Autowired
	private UserFeignClient2 client;

	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return client.getUser(id);
	}

	@PostMapping("/user/post-add")
	public User addUser(@RequestBody User user) {
		return client.addUser(user);
	}

	/**
	 * 这种方法，通过浏览器可以调用，但是通过RestTemplate和FeignClient都调用不到
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/user/get-add")
	public User addUser2(User user) {
		User o = client.addUser2(user);
		return o;
	}

	@GetMapping("/role/{id}")
	public Role getRole(@PathVariable("id") Long id) {
		Role o = client.getRole(id);
		return o;
	}

	@PostMapping("/role/post-add")
	public Role addRole(@RequestBody Role role) {
		Role o = client.addRole(role);
		return o;
	}

	@GetMapping("/complex1/{id}")
	public ComplexBean1 testComplexBean1(@PathVariable("id") Long id) {
		ComplexBean1 o = client.testComplexBean1(id);
		return o;
	}

	@PostMapping("/complex1/post-add")
	public ComplexBean1 postAddComplexBean1(@RequestBody ComplexBean1 bean) {
		ComplexBean1 o = client.postAddComplexBean1(bean);
		return o;
	}

	@GetMapping("/complex1/get-add")
	public ComplexBean1 getAddTestComplexBean1(ComplexBean1 bean) {
		ComplexBean1 o = client.getAddTestComplexBean1(bean);
		return o;
	}

	@GetMapping("/complex2/{id}")
	public ComplexBean2 getComplexBean2(@PathVariable("id") Long id) {
		ComplexBean2 o = client.getComplexBean2(id);
		return o;
	}

	@PostMapping("/complex2/post-add")
	public ComplexBean2 postAddComplexBean2(@RequestBody ComplexBean2 bean) {
		ComplexBean2 o = client.postAddComplexBean2(bean);
		return o;
	}

	@GetMapping("/complex2/get-add")
	public ComplexBean2 getAddTestComplexBean2(ComplexBean2 bean) {
		ComplexBean2 o = client.getAddTestComplexBean2(bean);
		return o;
	}
	
	@PostMapping("/complex2/post-add2get")
	public ComplexBean2 postAddTestComplexBean2(ComplexBean2 bean) {
		ComplexBean2 o = client.getAddTestComplexBean2(bean);
		return o;
	}

	/**
	 * 用于测试数据参数的传入和传出
	 * 
	 * @param ulist
	 * @return
	 */
	@PostMapping("/user-list")
	public List<User> testList(@RequestBody List<User> ulist) {
		List<User> o = client.testList(ulist);
		System.out.println(o.size());
		return o;
	}

	@PostMapping("/user-map")
	public Map<Long, User> testMap(@RequestBody Map<Long, User> umap) {
		Map<Long, User> o = client.testMap(umap);
		return o;
	}

	@PostMapping("/user-set")
	public Set<User> testSet(@RequestBody Set<User> uset) {
		Set<User> o = client.testSet(uset);
		System.out.println(o.size());
		return o;
	}
	
	@GetMapping("/balance")
	public String balance(){
		return "OK";
	}

	@GetMapping("/test-hystrix/{arg}")
	public String testHystrix(@PathVariable String arg){
		return client.testHystrix(arg);
	}
}