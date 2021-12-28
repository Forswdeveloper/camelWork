package com.camel.test;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelTestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelTestProjectApplication.class, args);
		CamelContext context = new DefaultCamelContext();
		//파일을 라우팅
	        try {
	            context.addRoutes(new RouteBuilder() {
	                @Override
	                public void configure() throws Exception {
	                    from("direct:JavaFileRouteStart").
	                 
	                    //To filter routing message to the external file 
	                        split(xpath("//order[@product='electronics']/items"))
	                        .to("file:src/main/resources/orderxmlroute/");
	                }
	            });
	            context.start();
	            ProducerTemplate template = context.createProducerTemplate();
	            //InputStream orderxml = new FileInputStream("src/main/resources/order.xml");
	            //template.sendBody("direct:JavaFileRouteStart", orderxml);
	            template.sendBody("direct:JavaFileRouteStart","");
	        }catch(Exception exception) {
	        	exception.printStackTrace();
	        }finally {
	            context.stop();
	        }
	        //메시지를 라우팅
//	        try {
//	            context.addRoutes(new RouteBuilder() {
//	                @Override
//	                public void configure() throws Exception {
//	                    from("direct:JavaMessageRouteStart").
//	                    // To route message on the IDE console  
//	                        to("stream:out");
//	                }
//	                });
//	            context.start();
//	            ProducerTemplate template = context.createProducerTemplate();
//	            //InputStream orderxml = new FileInputStream("src/main/resources/order.xml");
//	            //template.sendBody("direct:JavaMessageRouteStart", orderxml);
//	            template.sendBody("direct:JavaMessageRouteStart","");
//	        }catch(Exception exception) {
//	        	exception.printStackTrace();
//	        } finally {
//	            context.stop();
//	        }
	}

}
