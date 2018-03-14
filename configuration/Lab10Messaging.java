package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab10Messaging extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		rest("/amq")
			.get("/queue/{message}").to("direct:queue");
			
		from("direct:queue")
			.setBody(simple("${header.message}"))
			.to("activemq:queue:hey?exchangePattern=InOnly")
			.log("Route finished");	
		
		from("activemq:queue:myqueue?transacted=true")
			.log("Message received from queue: ${body}")
			.throwException(new RuntimeException());
	}
}