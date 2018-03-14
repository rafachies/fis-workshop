package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class Lab10Messaging extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		rest("/amq")
			.get("/queue/{message}").to("direct:sendQueue")
			.post("/queue").consumes(MediaType.APPLICATION_JSON_VALUE).to("direct:sendJson")
			.get("/topic/{message}").to("direct:sendTopic");
		
		// testing sending and consuming queue
		from("direct:sendQueue")
			.setBody(simple("${header.message}"))
			.to("activemq:queue:redhat?exchangePattern=InOnly")
			.log("Body returned: ${body}");
		
		from("activemq:queue:redhat?transacted=true")
			.log("Message received: ${body}")
			.throwException(new RuntimeException());
		
		
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
		
		
		
		
		
		//Testing with Json
//		from("direct:sendJson")
//			.to("activemq:queue:stock?exchangePattern=InOnly");
//		
//		from("activemq:queue:stock")
//			.unmarshal().json(JsonLibrary.Jackson, Stock.class)
//			.log("Message received: ${body}");
		
		
	}
}