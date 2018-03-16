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
		
		// testing sending and consuming from queue
		from("direct:sendQueue")
			.setBody(simple("${header.message}"))
			.to("activemq:queue:redhat?exchangePattern=InOnly")
			.log("Body returned: ${body}");
		
		from("activemq:queue:redhat?transacted=true")
			.log("Message received: ${body}")
			.throwException(new RuntimeException());
		
		
		//testing sending and consuming from topic
		from("direct:sendTopic")
			.setBody(simple("${header.message}"))
			.to("activemq:topic:jboss?exchangePattern=InOnly");
		
		from("activemq:topic:jboss?clientId=lopes")
			.routeId("lopes")
			.log("Message received: ${body}");
		
		from("activemq:topic:jboss?clientId=chies&durableSubscriptionName=chies")
			.routeId("chies")
			.log("Message received: ${body}");
	}
}