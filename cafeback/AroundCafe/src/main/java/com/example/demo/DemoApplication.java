package com.example.demo;

import com.example.demo.security.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // createdDate, modifiedDate, updatedBy, createBy 사용 가능
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class) // configurationProperties 사용 가능 -- yaml 파일에 프로퍼티를 임베이드 하여 사용 가능하게 해주는 어노테이션
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
