package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AllTests {

	public List<Book> bookList;
	private Book book1 = new Book(1, "han1");
	
	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	protected ObjectMapper objectMapper;
	
	//@Autowired
	//private Logger logger = LoggerFactory.getLogger(AllTests.class);
	
	
	@Test
	@Order(1)
	@DisplayName("1. 모든 데이터 불러오기")
	public void findAllListTest() throws Exception {		
		mvc.perform(get("/book"))
			.andExpect(result -> {
				MockHttpServletResponse httpServletResponse = result.getResponse();
				//bookList = (List<Book>)Arrays.asList(httpServletResponse.getContentAsString());
				//logger.info((httpServletResponse.getContentAsString()));
				//log.info("");
				System.out.println("allList >> " +httpServletResponse.getContentAsString());
				
			});
			
	}
	
	@Test
	@Order(2)
	@DisplayName("2. 데이터 저장")
	//@Disabled
	public void saveTest1() throws Exception {
		
		mvc.perform(post("/book")
				.content(objectMapper.writeValueAsString(book1))
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				)
		
		.andExpect(result -> {
			MockHttpServletResponse httpServletResponse = result.getResponse();
			//logger.info(httpServletResponse.getContentAsString());
			System.out.println("save >> " + httpServletResponse.getContentAsString());
			findAllListTest();
		});
		
	}
	
	@Test
	@Order(3)
	@DisplayName("2. 데이터 저장 2")
	//@Disabled
	public void saveTest2() throws Exception {
		
		Book book1 = new Book(2, "han2");
		
		mvc.perform(post("/book")
				.content(objectMapper.writeValueAsString(book1))
				.contentType(org.springframework.http.MediaType.APPLICATION_JSON)
				)
		
		.andExpect(result -> {
			MockHttpServletResponse httpServletResponse = result.getResponse();
			//logger.info(httpServletResponse.getContentAsString());
			System.out.println("save >> " + httpServletResponse.getContentAsString());
			findAllListTest();
		});
	}
	
	@Test
	@Order(4)
	@DisplayName("3. 데이터 수정")
	public void updateData() throws JsonProcessingException, Exception {
		
		int id = 1;
		book1.setName("goSpring");
		
		mvc.perform(put("/book/"+id)
				.content(objectMapper.writeValueAsString(book1))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(result -> {
			MockHttpServletResponse httpServletResponse = result.getResponse();
			System.out.println(httpServletResponse.getContentAsString());
		});
	}
	
	@Test
	@Order(4)
	@DisplayName("4. 데이터 삭제")
	public void deleteData() throws JsonProcessingException, Exception {
		
		int id = 1;
		
		mvc.perform(put("/book/"+id)
				.content(objectMapper.writeValueAsString(book1))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(result -> {
			MockHttpServletResponse httpServletResponse = result.getResponse();
			System.out.println(httpServletResponse.getContentAsString());
		});
	}
	
	
}
