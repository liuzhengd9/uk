package com.pcitc.uk.util;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.StringUtils;

public class JsonUtils {

	private static final Logger logger = Logger.getLogger(JsonUtils.class);

	private static final ObjectMapper mapper = new ObjectMapper();

	public static String[] getSort(String sort, String[] defaultSort) {

		if (StringUtils.hasText(sort)) {
			try {
				JsonNode n = mapper.readTree(sort);
				String property = n.findValue("property").getTextValue();
				logger.info("property:" + property);
				String direction = n.findValue("direction").getTextValue();
				logger.info("direction:" + direction);
				if (StringUtils.hasText(property) && StringUtils.hasText(direction)) {
					return new String[] { property, direction };
				} else {
					return defaultSort;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return defaultSort;
			}
		} else {
			return defaultSort;
		}
	}
}
