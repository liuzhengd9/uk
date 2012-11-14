package com.pcitc.json;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

public class JsonTest {

	// @Test
	public void test() throws Exception {

		String str = "[{\"property\":\"id\",\"direction\":\"ASC\"}]";
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, String>> list = mapper.readValue(str, new TypeReference<List<Map<String, String>>>() {
		});
		for (Map<String, String> m : list) {
			System.out.println(m.get("property"));
		}
	}

	// @Test
	public void testSortList() throws Exception {

		String str = "[{\"property\":\"id\",\"direction\":\"ASC\"}]";
		ObjectMapper mapper = new ObjectMapper();
		List<Sort> list = mapper.readValue(str, new TypeReference<List<Sort>>() {
		});
		for (Sort s : list) {
			System.out.println(s.property);
			System.out.println(s.direction);
		}
	}

	private static class Sort implements java.io.Serializable {

		private static final long serialVersionUID = 2504503131742993521L;

		public String property;

		public String direction;

	}

	@Test
	public void jsonNode() throws Exception {

		String str = "[{\"property\":\"id\",\"direction\":\"ASC\"}]";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode n = mapper.readTree(str);
		System.out.println(n.findValue("property").getTextValue());

	}
}
