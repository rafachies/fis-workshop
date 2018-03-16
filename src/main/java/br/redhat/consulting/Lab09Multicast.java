package br.redhat.consulting;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class Lab09Multicast extends RouteBuilder {
	
	 @Override
     public void configure() throws Exception {

		 rest("/multicast")
             .get("/").route().to("direct:multicast");

		 from("direct:multicast")
		 	.multicast()
		 		.aggregationStrategy(new MulticastStrategy())
		 		.to("bean:buyerOne", "bean:buyerTwo")
		 	.end()
     .log("Winner bid: ${body}");
     }
	 
	 @Component
     private class MulticastStrategy implements AggregationStrategy {

             public MulticastStrategy() {}

             @Override
             public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
            	 	  Integer oldBid = getBid(oldExchange);
	              Integer newBid = getBid(newExchange);
	              System.out.println("previous bid: " + oldBid );
	              System.out.println("new bid: " + newBid );
	              Integer winner = newBid > oldBid ? newBid : oldBid;
	              newExchange.getOut().setBody(winner);
	              return newExchange;
             }

             private Integer getBid(Exchange exchange) {
                     if (exchange == null || exchange.getIn().getBody() == null || "".equals(exchange.getIn().getBody())) {
                             return 0;
                     }
                     return (Integer) exchange.getIn().getBody();
             }
     }


}
