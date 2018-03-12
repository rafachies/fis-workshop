package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class Lab02Rest extends RouteBuilder {

	private static final String STOCK_JSON = "{\"symbol\":\"rht\", \"name\":\"redhat\", \"price\":\"158\"}";

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.component("servlet")
			.port(8080)
			.contextPath("/workshop")
			.bindingMode(RestBindingMode.json)
			.dataFormatProperty("printPretty", "true");
		
		rest("/rest")
			.get("/say").route()
				.setBody(simple("Hello World"))
			.endRest()
			.get("/say/{message}").route()
				.setBody(simple("Hello ${header.message}"))
			.endRest()
			.get("/stock").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.setBody(constant(STOCK_JSON))
				.unmarshal().json(JsonLibrary.Jackson)
			.endRest();
		
		
			
	}

}