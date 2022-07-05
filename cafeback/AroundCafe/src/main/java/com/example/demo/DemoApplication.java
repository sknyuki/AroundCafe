package com.example.demo;

import com.example.demo.security.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.TimeZone;

@EnableJpaAuditing // createdDate, modifiedDate, updatedBy, createBy 사용 가능
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class) // configurationProperties 사용 가능 -- yaml 파일에 프로퍼티를 임베이드 하여 사용 가능하게 해주는 어노테이션
@EnableCaching
@EnableScheduling
public class DemoApplication {

	@PostConstruct
	public void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


}
