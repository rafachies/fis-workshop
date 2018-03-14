package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab10Messaging extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		rest("/amq")
			.get("/queue/{message}").to("direct:sendQueue")
			.get("/topic/{message}").to("direct:sendTopic");
		
		from("direct:sendQueue")
			.setBody(simple("${header.message}"))
			.to("activemq:queue:redhat?deliveryPersistent=false&exchangePattern=InOnly")
			.log("Body returned: ${body}");
		
		from("direct:sendTopic")
			.setBody(simple("${header.message}"))
			.to("activemq:topic:jboss?exchangePattern=InOnly");
		
		//Two clientes expecting a topic message
		from("activemq:topic:jboss")
			.routeId("lopes")
			.log("Message received: ${body}");
		
		from("activemq:topic:jboss?clientId=chies&durableSubscriptionName=chies")
			.routeId("chies")
			.log("Message received: ${body}");
		
		
		from("activemq:queue:redhat")
			.log("Message received: ${body}");
	}
}