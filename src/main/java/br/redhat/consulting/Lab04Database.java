package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.redhat.consulting.util.Stock;

@Component
public class Lab04Database extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		rest("/database")
			.get("/sql/count").to("direct:sql-count")
			.get("/sql/resultset").to("direct:sql-resultset")
			.get("/sql/stock/{symbol}").to("direct:sql-singleresult")
			.post("/jpa/stock").consumes(MediaType.APPLICATION_JSON_VALUE).type(Stock.class).to("direct:jpa-merge")
			.post("/transaction").consumes(MediaType.APPLICATION_JSON_VALUE).type(Stock.class).to("direct:transaction");
		
		from("direct:sql-count")
			.to("sql:select count(*) from stock?dataSource=#dataSource&outputType=SelectOne")
			.setBody(simple("Number of stocks: ${body}"));
		
		from("direct:sql-resultset")
			.to("sql:select * from stock?dataSource=#dataSource")
			.setBody(simple("All stocks: ${body}"));
		
		from("direct:sql-singleresult")
			.to("sql:select * from stock where symbol = :#symbol?dataSource=#dataSource&outputType=SelectOne&outputClass=br.redhat.consulting.util.Stock")
			.setBody(simple("Stock: ${body}"));
	
		from("direct:jpa-merge")
			.to("jpa:br.redhat.consulting.util.Stock")
			.setBody(simple("Stock persisted: ${body}"));
		
		from("direct:transaction")
			.transacted()
			.to("jpa:br.redhat.consulting.util.Stock")
			.throwException(new RuntimeException())
			.setBody(simple("Stock persisted: ${body}"));
	
		from("jpa://br.redhat.consulting.util.Stock?consumer.namedQuery=Stock.findAll&consumer.delay=5000&consumeDelete=false")
			.log("Consuming: ${body.symbol}");
		
	}

}
