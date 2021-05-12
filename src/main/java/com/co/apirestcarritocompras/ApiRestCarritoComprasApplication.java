package com.co.apirestcarritocompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(value = "com.co.api")
@EntityScan("com.co.api.entity")
@EnableJpaRepositories("com.co.api.dao")
public class ApiRestCarritoComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestCarritoComprasApplication.class, args);
	}

}
