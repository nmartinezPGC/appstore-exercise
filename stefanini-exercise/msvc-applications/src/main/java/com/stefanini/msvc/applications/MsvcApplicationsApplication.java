package com.stefanini.msvc.applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcApplicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcApplicationsApplication.class, args);
	}

}
