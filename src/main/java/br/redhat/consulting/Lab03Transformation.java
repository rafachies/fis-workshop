package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.redhat.consulting.soap.IncidentService;
import br.redhat.consulting.util.Currency;
import br.redhat.consulting.util.GetCurrencyByCountryResponse;

@Component
public class Lab03Transformation extends RouteBuilder {

	
	
	private static final String SERVER_ADDRESS = "http://localhost:8383/workshop/incident";

	@Override
	public void configure() throws Exception {
		CxfComponent cxfComponent = new CxfComponent(getContext());
		CxfEndpoint soapProducer = new CxfEndpoint("http://www.webservicex.net/country.asmx", cxfComponent);
		soapProducer.setWsdlURL("http://www.webservicex.net/country.asmx?wsdl");
		soapProducer.setServiceNameString("{http://www.webserviceX.NET}country");
		soapProducer.setPortNameString("{http://www.webserviceX.NET/}countrySoap12");
		soapProducer.setDefaultOperationName("GetCurrencyByCountry");
		soapProducer.setDataFormat(DataFormat.PAYLOAD);
		
		CxfEndpoint soapConsumer = new CxfEndpoint(SERVER_ADDRESS, cxfComponent);
		soapConsumer.setServiceClass(IncidentService.class);
		soapConsumer.setBeanId("cxfEndpoint");
		
		rest("/transformation")
			.post("/currency").consumes(MediaType.APPLICATION_JSON_VALUE).type(Currency.class).to("direct:getCurrency");
		
		
		from("direct:getCurrency")
			.to("dozer:id?mappingFile=dozer-mapping.xml&sourceModel=br.redhat.consulting.util.Currency&targetModel=br.redhat.consulting.util.GetCurrencyByCountry")
			.log("Body converted: ${body}")
			.marshal().jacksonxml(true)
			.to(soapProducer)
			.unmarshal().jacksonxml(GetCurrencyByCountryResponse.class)
			.log("SOAP Response: ${body}");
		
	}


}
