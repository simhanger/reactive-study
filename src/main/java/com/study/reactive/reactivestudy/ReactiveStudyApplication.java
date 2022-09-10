package com.study.reactive.reactivestudy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ReactiveStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveStudyApplication.class, args);
		log.debug("hello reactive");
	}

}
