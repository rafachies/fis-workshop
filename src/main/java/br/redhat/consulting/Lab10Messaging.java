package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab10Messaging extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		rest("/amq")
			.get("/queue/{message}").to("direct:sendQueue");
		
		from("direct:sendQueue")
			.setBody(simple("${header.message}"))
			.to("activemq:queue:redhat?deliveryPersistent=false&exchangePattern=InOnly")
			.log("Body returned: ${body}");
		
		
		from("activemq:queue:redhat")
			.log("Message received: ${body}");
	}
}