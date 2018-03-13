package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfComponent;
import org.apache.camel.component.cxf.CxfEndpoint;
import org.apache.camel.component.cxf.DataFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import br.redhat.consulting.util.Currency;
import br.redhat.consulting.util.GetCurrencyByCountryResponse;

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
		
		rest("/transformation")
			.post("/currency").consumes(MediaType.APPLICATION_JSON_VALUE).type(Currency.class).to("direct:getCurrency");
		
		
		from("direct:getCurrency")
			.to("dozer:id?mappingFile=dozer-mapping.xml&sourceModel=br.redhat.consulting.util.Currency&targetModel=br.redhat.consulting.util.GetCurrencyByCountry")
			.log("Body converted: ${body}")
			.marshal().jacksonxml(true)
			.to(soapEndpoint)
			.unmarshal().jacksonxml(GetCurrencyByCountryResponse.class)
			.log("SOAP Response: ${body}");
			
			
		
		
		
		
	}


}
