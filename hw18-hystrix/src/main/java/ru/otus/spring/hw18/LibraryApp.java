package ru.otus.spring.hw18;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
@EnableCaching
@EnableHystrix
@EnableHystrixDashboard
public class LibraryApp {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApp.class, args);
	}

}
