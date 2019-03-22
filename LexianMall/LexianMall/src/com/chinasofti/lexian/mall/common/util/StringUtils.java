package com.chinasofti.lexian.mall.common.util;

import java.util.Calendar;

public class StringUtils {
	private static String[] afix = new String[] { "png", "jpg", "bmp", "gif", "PNG", "JPG", "BMP", "GIF" };

	private StringUtils() {

	}

	public static String trim(String str) {
		return str == null ? "" : str.trim();
	}

	public static String formatDate(Calendar date, String format) {
		if (format == null || format.length() == 0)
			return date.toString();
		StringBuilder sbBuffer = new StringBuilder();
		int[] param = new int[] { date.get(Calendar.YEAR), date.get(Calendar.MONTH) + 1, date.get(Calendar.DATE),
				date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.HOUR), date.get(Calendar.MINUTE),
				date.get(Calendar.SECOND), date.get(Calendar.MILLISECOND) };
		for (int i = format.length() - 1; i >= 0; i--) {
			char ch = format.charAt(i);
			switch (ch) {
			case 'y':
				sbBuffer.append(param[0] % 10);
				param[0] = param[0] / 10;
				break;
			case 'M':
				sbBuffer.append(param[1] % 10);
				param[1] = param[1] / 10;
				break;
			case 'd':
				sbBuffer.append(param[2] % 10);
				param[2] = param[2] / 10;
				break;
			case 'H':
				sbBuffer.append(param[3] % 10);
				param[3] = param[3] / 10;
				break;
			case 'h':
				sbBuffer.append(param[4] % 10);
				param[4] = param[4] / 10;
				break;
			case 'm':
				sbBuffer.append(param[5] % 10);
				param[5] = param[5] / 10;
				break;
			case 's':
				sbBuffer.append(param[6] % 10);
				param[6] = param[6] / 10;
				break;
			case 'S':
				sbBuffer.append(param[7] % 10);
				param[7] = param[7] / 10;
				break;
			// 其他非日期格式字符
			default:
				sbBuffer.append(ch);
			}

		}
		return sbBuffer.reverse().toString();
	}

	public static boolean isNull(String str) {
		return str == null;
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	public static boolean isNullOrEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNotNullAndEmpty(String str) {
		return !isNullOrEmpty(str);
	}

	public static String lpad(char paddingChar, int length, Object obj) {
		String old = obj.toString();
		int oldLength = old.length();
		if (oldLength >= length)
			return old;
		for (int index = 0; index < length - oldLength; index++) {
			old = paddingChar + old;
		}
		return old;
	}

	public static boolean isImage(String name) {

		if (name == null || name.length() == 0)
			return false;
		for (String tmp : afix) {
			if (name.endsWith(tmp))
				return true;
		}
		return false;

	}

	public static boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static String subFrom(String sql) {

		StringBuilder sBuilder = new StringBuilder("select count(0) from ");
		if (isNullOrEmpty(sql))
			return "";
		boolean hasFrom = false;
		for (int i = 0; i < sql.length(); i++) {
			char charc = sql.charAt(i);
			switch (charc) {
			case '(':
				if (hasFrom) {
					sBuilder.append(charc);
				}
				else {
					i = sql.indexOf(')', i);
				}
				break;
			case ')':
				if (hasFrom) {
					sBuilder.append(charc);
				}
				break;
			case 'F':
			case 'f':
				if(hasFrom){
					sBuilder.append(charc);
				}
				else{
					char tmp1 = sql.charAt(++i);
					if ('R' == tmp1 || 'r' == tmp1) {
						char tmp2 = sql.charAt(++i);
						if (tmp2 == 'o' || tmp2 == 'O') {
							char tmp3 = sql.charAt(++i);
							if ('M' == tmp3 || 'm' == tmp3) {
								hasFrom = true;
							}
						}

					}
				}
				break;
			default:
				if (hasFrom) {
					sBuilder.append(charc);
				}
				break;
			}
		}
		return sBuilder.toString();
	}
}
