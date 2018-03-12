package br.redhat.consulting.util;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class BodyProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setBody("Hello Lab01");
	}

}
