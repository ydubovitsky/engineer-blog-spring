package ru.ydubovitsky.engineerBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
public class EngineerBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(EngineerBlogApplication.class, args);
	}

}
