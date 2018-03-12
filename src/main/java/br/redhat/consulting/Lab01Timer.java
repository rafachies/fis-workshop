package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab01Timer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
	
		from("timer:workshop?period=5000")
			.log("Hello Camel!");
		
	}

}
