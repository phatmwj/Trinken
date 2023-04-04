package com.tp.trinken.utils;

import java.util.HashMap;
import java.util.Map;

import com.tp.trinken.entity.User;

public class Result{
	
	public Map<String, Object> result(boolean error, String message){
		Map<String , Object>map=new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		return map;
	}
	
	public Map<String, Object> resultUser(boolean error, String message,User user){
		Map<String , Object>map=new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		map.put("user", user);
		return map;
	}
	

}
