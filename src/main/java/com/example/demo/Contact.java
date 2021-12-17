package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Contact {
    
    public List<Map<String, Object>> contactList = null;

    public List<Map<String, Object>> getInstance() {
        if(contactList == null){
            contactList = new ArrayList<>();
        }

//        Map map = new HashMap();
//		map.put("name", "Hong Gildong");
//		map.put("phone", "010-4533-2344");
//        contactList.add(map);
//
//		map = new HashMap();
//		map.put("name", "Hong Gildong");
//		map.put("phone", "010-4533-1111");
//        contactList.add(map);
//
//		map = new HashMap();
//		map.put("name", "jidori");
//		map.put("phone", "010-1234-2344");
//        contactList.add(map);


        return contactList;
    }

    // CSV Load 메서드
    public void load(String filePath) throws IOException {
        
    	InputStreamReader inStream = new InputStreamReader(this.getClass().getResourceAsStream(filePath));
        
    	// try-with-resource -> close 함수 쓰지 않아도 자동 닫힘
    	try(BufferedReader br = new BufferedReader(inStream)) {
        	String readLine = null;
        	 while((readLine = br.readLine()) != null) {
                 Map param = new HashMap<>();
                 param.put(readLine.split(",")[0], readLine.split(",")[1]);
                 contactList.add(param);
             }
        }
       
        
        
    
    }
    
    // Json 파일 로드
    public void jsonLoad(String filePath) throws IOException, ParseException{
    	InputStreamReader inStream = new InputStreamReader(this.getClass().getResourceAsStream(filePath));
    	
    	try(BufferedReader br = new BufferedReader(inStream)) {

        	JSONParser parser = new JSONParser();
        	JSONArray arr = (JSONArray) parser.parse(br); //JSONParser로 readLine하여 String으로 append하지 않고 바로 변환
        	
        	if(ObjectUtils.isEmpty(arr)) {
        		// Json Array 널값 체크
        		 throw new ParseException(0, "isEmpty Data");
        	}
        	
        	
        	for(Object obj : arr) {
        		JSONObject jobj = (JSONObject)obj; 
        		Map<String, Object> maps = new ObjectMapper().readValue(jobj.toJSONString(), Map.class);
        		contactList.add(maps);
        	}
    		
    	}

    }
    
    

    public long getUniqueNumbers(){
//        int keyCount = contactList.stream()
//        						.map(p->p.keySet())
//        						.distinct()
//        						.mapToInt(Set::size) // IntStream으로 리턴 성능향상으로 쓰임
//        						.reduce(0, (a,b)->a+1);
        
        long keyCount = contactList.stream()
				.map(p->p.keySet())
				.distinct()
				.mapToInt(Set::size) // IntStream으로 리턴 성능향상으로 쓰임
				.count();

        System.out.println("keyCount : " + keyCount);
        return keyCount;
    }

    // 같은 이름을 가지고 있는 사람을 그룹화
    public List<Map> getGroupList() {
    	
    	//스트림 생성하여 키인 name으로 groupby를 한다음 entrySet으로 스트림을 열어
    	//key값을 비교해서 정렬하고 Map을 이용하여 정렬된 key/value들을 넣어 최종 list로 리턴해준다.
    	List<Map> collect = contactList.stream()
    				.collect(Collectors.groupingBy((map) -> map.get("name")))
    				.entrySet().stream()
    				.sorted((a,b) -> ((String) b.getKey()).compareTo((String) a.getKey()))
    				.map(entry -> {
    					Map map = new HashMap();
    					map.put("name", entry.getKey());
    					map.put("phone", entry.getValue().stream().map(v->v.get("phone")).collect(Collectors.toList()));
    					return map;
    				})
    				.collect(Collectors.toList());
    	
    	System.out.println(collect);
    	System.out.println(collect.stream().collect(Collectors.toSet()));
    

        return collect;
    }
}