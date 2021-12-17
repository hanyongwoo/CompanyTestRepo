package com.example.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		
		String[] nameArr = {"IronMan", "Captain", "Hulk", "Thor"};
		List<String> nameList = Arrays.asList(nameArr);
		
		Stream<String> streamNameArr = Arrays.stream(nameArr);
		streamNameArr.sorted().forEach(System.out::println);
		
		long count = streamNameArr.count();
		System.out.println(count);
		//Collections.sort(nameList);
		
		//System.out.println(streamNameArr);
		
		SpringApplication.run(DemoApplication.class, args);
	}

}
