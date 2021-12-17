package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() throws IOException, ParseException {
		
		Contact c = new Contact();
		c.getInstance();
		c.jsonLoad("/testJson.json");
		List<Map> groupList = c.getGroupList();
		assertEquals(2, ((List)groupList.get(0).get("phone")).size());
	}
		
	
	

}
