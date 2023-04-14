package com.tp.trinken.utils;

import java.util.HashMap;
import java.util.Map;

import com.tp.trinken.entity.Brand;
import com.tp.trinken.entity.Category;
import com.tp.trinken.entity.Product;
import com.tp.trinken.entity.User;

public class Result {

	public Map<String, Object> result(boolean error, String message) {
		Map<String, Object> map = new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		return map;
	}

	public Map<String, Object> resultUser(boolean error, String message, User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		map.put("user", user);
		return map;
	}

	public Map<String, Object> resultCategory(boolean error, String message, Category category) {
		Map<String, Object> map = new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		map.put("category", category);
		return map;
	}

	public Map<String, Object> resultProduct(boolean error, String message, Product product) {
		Map<String, Object> map = new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		map.put("product", product);
		return map;
	}

	public Map<String, Object> resultBrand(boolean error, String message, Brand brand) {
		Map<String, Object> map = new HashMap<>();
		map.put("error", error);
		map.put("message", message);
		map.put("brand", brand);
		return map;
	}

}
