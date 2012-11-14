package com.pcitc.uk.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Utils {

	public static final Logger logger = Logger.getLogger(Utils.class);

	public static Date format(String source, String pattern) throws ParseException {

		return new SimpleDateFormat(pattern).parse(source);
	}
}
