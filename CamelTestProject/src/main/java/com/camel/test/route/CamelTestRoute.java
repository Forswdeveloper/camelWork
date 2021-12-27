package com.camel.test.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelTestRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		from("timer://test?repeatCount=1")
		.log("Test Camel")
		.setProperty("testProperty",simple("name"))
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				System.err.println(exchange.getProperty("testProperty"));
				exchange.setProperty("testProperty","is Dongin");
				System.out.print(exchange.getProperty("testProperty"));
			}
		})
		.end();
	}

}
