package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.redhat.consulting.util.MyBean;
import br.redhat.consulting.util.Stock;

@Component
public class Lab02Rest extends RouteBuilder {

	private static final String STOCK_JSON = "{\"symbol\":\"rht\", \"name\":\"redhat\", \"price\":\"158\"}";

	@Override
	public void configure() throws Exception {
		restConfiguration()
			.component("servlet")
			.port(8080)
			.contextPath("/workshop")
			.apiProperty("api.title", "Workshop REST Services")
			.apiProperty("api.version", "1.0")
			.apiContextPath("/api-doc")
			.bindingMode(RestBindingMode.json)
			.enableCORS(true)
			.dataFormatProperty("printPretty", "true");
		
		rest("/rest")
			.description("Serviços Laboratório REST")
			
			.get("/bean").route()
				.bean(MyBean.class, "sayHello()")
			.endRest()
			
			.get("/say")
				.id("say")
				.description("Diz Hello World")
				.route()
					.setBody(simple("Hello World"))
				.end()
			.endRest()
			
			.get("/say/{message}")
				.id("sayMessage")
				.responseMessage().code(200).message("Retorno com sucesso").endResponseMessage()
				.description("Diz Hello para alguém")
				.param().name("mensagem para hello").endParam()
				.route()
					.setBody(simple("Hello ${header.message}"))
				.end()
			.endRest()
			
			.get("/stock").produces(MediaType.APPLICATION_JSON_VALUE).route()
				.setBody(constant(STOCK_JSON))
				.unmarshal().json(JsonLibrary.Jackson)
			.endRest()
			
			.post("/stock").type(Stock.class).consumes(MediaType.APPLICATION_JSON_VALUE).produces(MediaType.APPLICATION_JSON_VALUE)
				.to("direct:postStock");
		
			from("direct:postStock")
				.log("POJO: ${body}")
				.marshal().json(JsonLibrary.Jackson)
				.log("JSON: ${body}");
	}

}