package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class Lab02Rest extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.component("servlet")
			.port(8080)
			.contextPath("/workshop")
			.bindingMode(RestBindingMode.json)
			.dataFormatProperty("printPretty", "true");
		
		rest()
			.get("/say").route()
				.setBody(simple("Hello World"))
		.end();
	}

}
