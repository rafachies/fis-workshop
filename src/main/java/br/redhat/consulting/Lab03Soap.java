package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.stereotype.Component;

import br.redhat.consulting.soap.IncidentService;
import br.redhat.consulting.soap.StatusProcessor;

@Component
public class Lab03Soap extends RouteBuilder {

	private static final String SERVER_ADDRESS = "http://localhost:8383/workshop/incident";

	@Override
	public void configure() throws Exception {
		CxfComponent cxfComponent = new CxfComponent(getContext());
		CxfEndpoint cxfEndpoint = new CxfEndpoint(SERVER_ADDRESS, cxfComponent);
		cxfEndpoint.setServiceClass(IncidentService.class);
		cxfEndpoint.setBeanId("cxfEndpoint");
		
		from(cxfEndpoint)
			.recipientList()
			.simple("direct:${header.operationName}");
		
		from("direct:reportIncident")
			.log("Request: ${body}")
			.to("direct:alertIncident");
		
		from("direct:statusIncident")
			.log("Request: ${body}")
			.process(new StatusProcessor())
			.log("Response: ${body}");
	}
}