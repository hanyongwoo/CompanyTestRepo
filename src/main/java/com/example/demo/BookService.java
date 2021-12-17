package com.example.demo;

import java.util.List;
import java.util.Map;

public interface BookService {

	public List<Map> findAll();
	public Map findById(int i);
	public void insert(Map map);
	public void update(Map map);
	public void delete(Map map);
} 
