package br.com.otero.personalcredit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PersonalcreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalcreditApplication.class, args);
	}

}
