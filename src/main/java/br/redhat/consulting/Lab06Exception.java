package br.redhat.consulting;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import br.redhat.consulting.util.MyException;

@Component
public class Lab06Exception extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		onException(MyException.class)
			.continued(true)
//			.handled(true)
			.log("Handling exception with onException!!!")
			.process(new Processor() {
				@Override
				public void process(Exchange exchange) throws Exception {
					exchange.getIn().setBody("Friendly message to the client");
				}
			});
			
		
		rest("/exception")
			.get("/trycatch").to("direct:trycatch")
			.get("/onexception").to("direct:onexception");;
			
		from("direct:trycatch")
			.doTry()
				.log("Starting route ...")
				.throwException(new RuntimeException())
			.doCatch(Exception.class)
				.log("Catching exception ...")
			.doFinally()
				.log("Finally!")
			.endDoTry();
		
		from("direct:onexception")
			.log("Starting route ...")
			.throwException(new MyException())
			.log("Route has continued with body: ${body}");		
	}
}