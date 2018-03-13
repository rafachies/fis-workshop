package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import br.redhat.consulting.util.BodyProcessor;

@Component
public class Lab01Timer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer:blue?delay=5000&repeatCount=1").routeId("timerRoute")
			.process(new BodyProcessor())
			.log("${body}");
		
	}
}