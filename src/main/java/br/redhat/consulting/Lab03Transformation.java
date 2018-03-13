package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.springframework.stereotype.Component;

@Component
public class Lab03Transformation extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		final String envelope = "<ns1:GetCurrencyByCountry xmlns:ns1='http://www.webserviceX.NET'>\n"
				+ "  <ns1:CountryName>brazil</ns1:CountryName>\n" + "</ns1:GetCurrencyByCountry>";
		
		
		CxfComponent cxfComponent = new CxfComponent(getContext());
		CxfEndpoint soapEndpoint = new CxfEndpoint("http://www.webservicex.net/country.asmx", cxfComponent);
		soapEndpoint.setWsdlURL("http://www.webservicex.net/country.asmx?wsdl");
		soapEndpoint.setServiceNameString("{http://www.webserviceX.NET}country");
		soapEndpoint.setPortNameString("{http://www.webserviceX.NET/}countrySoap12");
		soapEndpoint.setDefaultOperationName("GetCurrencyByCountry");
		soapEndpoint.setDataFormat(DataFormat.PAYLOAD);
		
		from("timer:chies?repeatCount=1&delay=5000")
			.setBody(constant(envelope))
			.to(soapEndpoint)
			.log("SOAP Body Response: ${body}");
		
		
		
		
		
		
	}


}
