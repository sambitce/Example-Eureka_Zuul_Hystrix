package com.cloud.helloclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("/rest/hello/client")
public class HelloResource {
	
	@Autowired
	private RestTemplate restTemplate ;
	
	
	@HystrixCommand(fallbackMethod="fallback")
	@GetMapping
	public String hello() {
		
		String url = "http://HELLO-SERVER/rest/hello/server" ;
		return restTemplate.getForObject(url, String.class);
	}
	
	public String fallback() {
		return "Hello world from Hystrix" ;
	}

}
