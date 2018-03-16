package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab05File extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("file:files/live?fileName=stock.xml")
			.choice()
			.when(xpath("//price > 100"))
				.to("file:files/live/expensive/")
			.otherwise()
				.to("file:files/live/cheap/")
		.endChoice();
	}

}
