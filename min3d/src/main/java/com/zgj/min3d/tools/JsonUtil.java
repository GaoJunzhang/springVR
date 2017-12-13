package com.zgj.min3d.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	public static Map<String, Object> convertToMap(String msg) {
		try {
			return mapper.readValue(msg, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
		}
		return null;
	}

	public static List<Map<String, Object>> convertToListMap(String msg) {
		try {
			return mapper.readValue(msg, new TypeReference<List<Map<String, Object>>>() {
			});
		} catch (IOException e) {
		}
		return null;
	}

	public static List<Integer> convertToListInteger(String msg) {
		try {
			return mapper.readValue(msg, new TypeReference<List<Integer>>() {
			});
		} catch (IOException e) {
		}
		return null;
	}

	public static String convertToString(Object o) {
		try {
			return mapper.writeValueAsString(o);
		} catch (JsonProcessingException e) {
		}
		return null;
	}
}
