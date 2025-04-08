package com.taskboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.taskboard")
public class TaskboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskboardApplication.class, args);
	}

}
