package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.springframework.stereotype.Component;

import br.redhat.consulting.soap.IncidentService;

@Component
public class Lab03Soap extends RouteBuilder {


	private static final String SERVER_ADDRESS = "http://localhost:8383/workshop/incident";

	@Override
	public void configure() throws Exception {
		CxfComponent cxfComponent = new CxfComponent();
		CxfEndpoint cxfEndpoint = new CxfEndpoint(SERVER_ADDRESS, cxfComponent);
		cxfEndpoint.setServiceClass(IncidentService.class);
		cxfEndpoint.setBeanId("cxfEndpoint");
		
		from(cxfEndpoint)
			.recipientList()
			.simple("direct:${header.operationName}");
		
		from("direct:report")
			.log("Report called: ${body}");
		
		from("direct:status")
			.log("Status called: ${body}");
	}
}