package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
	public List<Book> bookList = new ArrayList<Book>();

	//@Autowired
	//public BookServiceImpl bookService;
	
	public BookController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/book")
	public List<Book> findAll(){
		return bookList;
	}
	
	@GetMapping("/book/{id}")
	public Book findById(@PathVariable int id){
		for(Book data : bookList) {
			if(data.getId() == id) {
				return data;
			}
		}
		return null;
	}

	@PostMapping("/book")
	public Book save(@RequestBody Book param) throws Exception{
		for(Book book : bookList) {
			if(book.getId() == param.getId()) {
				throw new Exception("Duplicate ID");
			}
		}
		
		bookList.add(param);
		return param;
	}
	
	@PutMapping("/book/{id}")
	public List<Book> update(@RequestBody Book book, @PathVariable int id){
		int idx = 0;
		for(Book data : bookList) {
			if(data.getId() == id) {
				data.setName(book.getName());
				bookList.set(idx, data);
				return bookList;
			}
			idx++;
		}
		return bookList;
	}
	
	@DeleteMapping("/book/{id}")
	public List<Book> delete(@PathVariable int id){
		int idx = 0;
		for(Book data : bookList) {
			if(data.getId() == id) {
				bookList.remove(idx);
				return bookList;
			}
			idx++;
		}
		return bookList;
	}
}
