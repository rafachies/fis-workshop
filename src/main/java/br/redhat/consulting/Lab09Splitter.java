package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab09Splitter extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/splitter/{messages}")
			.get("/").route().to("direct:splitter");

		//Split message coming from a XML file
		from("file://files/live?fileName=products.xml&delete=true")
			.split(xpath("//products/product"))
			.to("direct:messageHandler");

		//Split message coming from a REST request
		from("direct:splitter")
			.setBody(simple("${header.messages}"))
			.split().tokenize("x")
			.to("direct:messageHandler")
			.end()
			.log("Body after split: ${body}");

		from("direct:messageHandler")
			.log("message extractedt:\n ${body}");
	}


}
