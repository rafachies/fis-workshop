package br.redhat.consulting.util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GetCurrencyByCountry", namespace="http://www.webserviceX.NET")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetCurrencyByCountry {

	@XmlElement(name="CountryName", namespace="http://www.webserviceX.NET")
	private String CountryName;

	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
}

