package br.redhat.consulting.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GetCurrencyByCountryResult", namespace="http://www.webserviceX.NET")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCurrencyByCountryResponse {

	@XmlElement(name="GetCurrencyByCountryResult", namespace="http://www.webserviceX.NET")
	private String GetCurrencyByCountryResult;
	
	public GetCurrencyByCountryResponse() {}

	public String getGetCurrencyByCountryResult() {
		return GetCurrencyByCountryResult;
	}

	public void setGetCurrencyByCountryResult(String getCurrencyByCountryResult) {
		GetCurrencyByCountryResult = getCurrencyByCountryResult;
	}
}

