package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Lab04Database extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/database")
			.get("/sql/count").to("direct:sql-count");
		
		from("direct:sql-count")
			.to("sql:select count(*) from stock?dataSource=#dataSource&outputType=SelectOne")
			.setBody(simple("Number of stocks: ${body}"));
	}

}
