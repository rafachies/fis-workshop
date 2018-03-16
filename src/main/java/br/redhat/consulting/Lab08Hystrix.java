package br.redhat.consulting;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.HystrixConfigurationDefinition;
import org.springframework.stereotype.Component;

@Component
public class Lab08Hystrix extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		HystrixConfigurationDefinition configuration = new HystrixConfigurationDefinition();
		configuration.circuitBreakerRequestVolumeThreshold(5);
		configuration.circuitBreakerErrorThresholdPercentage(50);
		configuration.circuitBreakerSleepWindowInMilliseconds(60000);
		
		
		rest("/hystrix")
			.get("/").to("direct:hystrix");
			
		from("direct:hystrix")
			.removeHeaders("CamelHttp*")
			.hystrix()
				.hystrixConfiguration(configuration)
				.log("REST vai ser executado.")
				.to("http4:myphp-php-chies.apps.paas.rhbrlab.com?httpMethod=GET")
				.log("REST call executed")
			.onFallback()
				.log("Circuit is open, man!")
			.end();
	}
}