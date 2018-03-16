package br.redhat.consulting;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.aggregate.UseLatestAggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class Lab09Aggregator extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		rest("/aggregator/{message}")
			.get("/").route().to("direct:aggregator");


		from("direct:aggregator")
			.setBody(simple("${header.message}"))
			//more default strategiea: UseOriginalAggregationStrategy GroupedExchangeAggregationStrategy
			.aggregate(header("company"), new UseLatestAggregationStrategy())
			.completionTimeout(10000)
		    //.completionSize(2) // wait for 2 messages instead of a timeout
			.log("Message received from ${header.company}: ${body}");
	}               

	public class CustomAggregationStrategy implements AggregationStrategy {

		@Override
		public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
			String oldBody = oldExchange == null ? "" : (String) oldExchange.getIn().getBody();
			String newBody = newExchange.getIn().getBody() == null ? "" : (String) newExchange.getIn().getBody();
			System.out.println("oldBody: " + oldBody );
			System.out.println("newBody: " + newBody );
			newExchange.getOut().setBody(oldBody + newBody);
			return newExchange;
		}

	}

}
