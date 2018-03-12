package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;

import br.redhat.consulting.util.BodyProcessor;

//@Component
public class Lab01Timer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer:blue?period=5000").routeId("timerRoute")
			.process(new BodyProcessor())
			.log("${body}");
		
	}
}