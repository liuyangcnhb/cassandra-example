package com.sinodata.example.cassandra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class CassandraDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraDemoApplication.class, args);
	}

}
