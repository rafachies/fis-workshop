package br.redhat.consulting.soap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class StatusProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		OutputStatusIncident outputStatusIncident = new OutputStatusIncident();
		outputStatusIncident.setStatus("Approved");
		exchange.getIn().setBody(outputStatusIncident);
	}

}
