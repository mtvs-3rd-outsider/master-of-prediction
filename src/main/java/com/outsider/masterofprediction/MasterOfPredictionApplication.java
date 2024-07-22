package com.outsider.masterofprediction;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.outsider.masterofprediction")
@MapperScan("com.outsider.masterofprediction")
public class MasterOfPredictionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterOfPredictionApplication.class, args);
	}

}
