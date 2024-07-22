package com.outsider.masterofprediction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.outsider.masterofprediction")
@MapperScan("com.outsider.masterofprediction")
@EnableScheduling
public class MasterOfPredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterOfPredictionApplication.class, args);
	}

}
