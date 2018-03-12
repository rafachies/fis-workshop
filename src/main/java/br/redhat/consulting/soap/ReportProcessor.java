package br.redhat.consulting.soap;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ReportProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		OutputReportIncident outputReportIncident = new OutputReportIncident();
		outputReportIncident.setCode("00");
		exchange.getIn().setBody(outputReportIncident);
	}

}
