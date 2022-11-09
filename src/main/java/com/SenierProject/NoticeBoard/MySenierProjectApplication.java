package com.SenierProject.NoticeBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MySenierProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySenierProjectApplication.class, args);
	}

}
