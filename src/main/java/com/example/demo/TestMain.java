package com.example.demo;

import java.io.IOException;
import java.util.Arrays;

import org.json.simple.parser.ParseException;

class TestMain {

	
	static void getFunction(MyFunction m) {
		m.run();
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		System.out.println("test");
		
		Contact c = new Contact();
		c.getInstance();
		//c.load("/test.csv"); 
		//c.getUniqueNumbers();
		c.jsonLoad("/testJson.json");
		c.getGroupList();
		
		//Arrays.stream(str).flatMap().distinct().filter(s->"a".equals(s)).forEach(System.out::println);
		
	}
	
}

@FunctionalInterface
interface MyFunction {
	void run();
};

