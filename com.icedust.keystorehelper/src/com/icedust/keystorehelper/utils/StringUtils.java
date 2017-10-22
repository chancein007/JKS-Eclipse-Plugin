package com.icedust.keystorehelper.utils;
import java.util.regex.Pattern;

public class StringUtils {

	/**
	 * Is null or empty string
	 * 
	 * @param string
	 * @return
	 */
	public static boolean IsNullOrEmpty(final String str) {
		return str == null || str.length() == 0;
	}

	public static boolean IsNotNullOrEmpty(final String str) {
		if (str != null && str.length() != 0) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean IsWorkdayWSDLVersion(final String str) {
		Pattern pattern = Pattern
				.compile("(v[1-9]{1}[0-9]*+.[0-9]{1})|(v[1-9]{1}[0-9]*)");
		return pattern.matcher(str).matches();
	}

	public static boolean IsBooleanType(final String str) {
		if (StringUtils.IsNotNullOrEmpty(str)
				&& (str.equalsIgnoreCase("true") || str
						.equalsIgnoreCase("false"))) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean IsNonNegativeInteger(String str) {
		Pattern pattern = Pattern.compile("^[1-9]\\d*|0$");
		return pattern.matcher(str).matches();
	}

}
