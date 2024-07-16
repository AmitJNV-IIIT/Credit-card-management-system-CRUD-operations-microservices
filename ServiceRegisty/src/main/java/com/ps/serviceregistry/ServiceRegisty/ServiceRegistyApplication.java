package com.ps.serviceregistry.ServiceRegisty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistyApplication.class, args);
	}

}
