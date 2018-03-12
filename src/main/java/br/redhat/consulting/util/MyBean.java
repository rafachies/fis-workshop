package br.redhat.consulting.util;

import org.springframework.stereotype.Component;

@Component
public class MyBean {

	public String sayHello() {
		return new String("Hello Bean World");
	}
	
}
