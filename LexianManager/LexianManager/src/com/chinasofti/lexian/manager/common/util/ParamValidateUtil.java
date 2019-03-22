package com.chinasofti.lexian.manager.common.util;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.multipart.MultipartFile;

public class ParamValidateUtil {

	private ParamValidateUtil() {

	}

	public static void validatePassword(String param, String message) throws ParamNotValidException {
		if (null == param || param.trim().length() < 6 || param.trim().length() > 16 || CommonUtil.hasZhWord(param)) {
			throw new ParamNotValidException(message);
		}

	}

	public static void validateNull(Object param, String message) throws ParamNotValidException {
		if (null == param) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validateEmpty(String param, String message) throws ParamNotValidException {
		if (param == null || param.trim().length() == 0) {
			throw new ParamNotValidException(message);
		}
	}
	
	public static void validateEmpty(MultipartFile param, String message) throws ParamNotValidException {
		if (param == null || param.isEmpty()) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validatePositive(Integer param, String message) throws ParamNotValidException {
		if (param == null || param <= 0) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validatePositive(Integer param, Integer num, String message) throws ParamNotValidException {
		if (param == null || param <= 0 || param > num) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validatePositive(Integer param, Integer num,Integer num1, String message) throws ParamNotValidException {
		if (param == null || param <= num || param > num1) {
			throw new ParamNotValidException(message);
		}
	}
	
	public static void validatePositive(Long param, Long num, String message) throws ParamNotValidException {
		if (param == null || param < num) {
			throw new ParamNotValidException(message);
		}
	}
	public static void validatePositive(BigDecimal param, BigDecimal num, String message)
			throws ParamNotValidException {
		if (num == null) {
			num = BigDecimal.ZERO;
		}
		if (param == null || param.compareTo(num) != 1) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validatePositive(Long param, String message) throws ParamNotValidException {
		if (param == null || param <= 0) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validatePositive(BigDecimal param, String message) throws ParamNotValidException {
		if (!(param instanceof BigDecimal)) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validateEmpty(BigDecimal param, String message) throws ParamNotValidException {
		if (param == null || param.compareTo(BigDecimal.ZERO) < 0) {
			throw new ParamNotValidException(message);
		}

	}

	public static void validateEmpty(Number param, String message) throws ParamNotValidException {
		if (param == null || param.doubleValue() <= 0) {
			throw new ParamNotValidException(message);
		}

	}
	
	public static boolean validateIDCard(String IDCard) {
		int[] intArr = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		int sum = 0;
		for (int i = 0; i < intArr.length; i++) {
			sum += Character.digit(IDCard.charAt(i), 10) * intArr[i];
		}
		int mod = sum % 11;
		int[] intArr2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] intArr3 = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };
		String matchDigit = "";
		for (int i = 0; i < intArr2.length; i++) {
			int j = intArr2[i];
			if (j == mod) {
				matchDigit = String.valueOf(intArr3[i]);
				if (intArr3[i] > 57) {
					matchDigit = String.valueOf((char) intArr3[i]);
				}
			}
		}
		if (matchDigit.equals(IDCard.substring(IDCard.length() - 1))) {
			return true;
		} else {
			return false;
		}
	}

	public static void validateEmail(String email, String message) throws ParamNotValidException {
		String check = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern regex = Pattern.compile(check);
		if (StringUtils.isNullOrEmpty(email))
			return;
		Matcher matcher = regex.matcher(email);
		if (!matcher.matches()) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validateExpression(boolean expression, String message) throws ParamNotValidException {
		if (!expression) {
			throw new ParamNotValidException(message);
		}
	}

	public static void validateMaxLength(String param, int length, String message) throws ParamNotValidException {
		if (param != null) {
			if (CommonUtil.getLengthOfStr(param) >= length) {
				throw new ParamNotValidException(message);
			}
		}
	}

	public static void validateMinLength(String param, int length, String message) throws ParamNotValidException {
		if (param != null) {
			if (CommonUtil.getLengthOfStr(param) <= length) {
				throw new ParamNotValidException(message);
			}
		}
	}

	public static void validatePhone(String param, String message) throws ParamNotValidException {
		validateEmpty(param, message);
		if (!StringUtils.isNumber(param))
			throw new ParamNotValidException(message);
	}

	public static void validationStringIsNumber(String split, String soure, String message)
			throws ParamNotValidException {
		if (split == null || soure == null) {
			throw new ParamNotValidException(message);
		}
		char ch = split.toCharArray()[0];
		char[] chars = soure.toCharArray();
		for (char c : chars) {
			if (!((c >= '0' && c <= '9') || c == ch))
				throw new ParamNotValidException(message);
		}
	}

	public static void validationStringIsNumber(String split, String soure, String message, int length)
			throws ParamNotValidException {
		if (split == null || soure == null) {
			throw new ParamNotValidException(message);
		}
		char ch = split.toCharArray()[0];
		String[] str = soure.split(split);
		for (String string : str) {
			if (string.length() > length)
				throw new ParamNotValidException("长度超过限定长度");
		}

		char[] chars = soure.toCharArray();
		for (char c : chars) {
			if (!((c >= '0' && c <= '9') || c == ch))
				throw new ParamNotValidException(message);
		}
	}

	public static void validateStringLength(String param, Integer minLength, Integer maxLength, String message)
			throws ParamNotValidException {
		validateEmpty(param, message);
		if (minLength != null) {
			validateMinLength(param, minLength, message);
		}
		if (maxLength != null) {
			validateMaxLength(param, maxLength, message);
		}
	}

	public static void validateIntegerLength(Integer param, Integer minLength, Integer maxLength, String message)
			throws ParamNotValidException {
		validateEmpty(param, message);
		String paramTostring = param.toString();
		if (minLength != null) {
			validateMinLength(paramTostring, minLength, message);
		}
		if (maxLength != null) {
			validateMaxLength(paramTostring, maxLength, message);
		}
	}

	public static void validateLongLength(Long param, Integer minLength, Integer maxLength, String message)
			throws ParamNotValidException {
		validateEmpty(param, message);
		String paramTostring = param.toString();
		if (minLength != null) {
			validateMinLength(paramTostring, minLength, message);
		}
		if (maxLength != null) {
			validateMaxLength(paramTostring, maxLength, message);
		}
	}

	public static void validateDoubleLength(Double param, Integer minLength, Integer maxLength, String message)
			throws ParamNotValidException {
		validateEmpty(param, message);
		String paramTostring = param.toString();
		if (minLength != null) {
			validateMinLength(paramTostring, minLength, message);
		}
		if (maxLength != null) {
			validateMaxLength(paramTostring, maxLength, message);
		}
	}

	public static final class ParamNotValidException extends Exception {

		private static final long serialVersionUID = 1L;

		private String message;

		private int code = Constant.failed_code;

		public void setCode(int code) {
			this.code = code;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		@Override
		public String getMessage() {
			return this.message;
		}

		public int getCode() {
			return code;
		}

		public ParamNotValidException() {

		}

		public ParamNotValidException(String message) {
			this.message = message;
		}

		public ParamNotValidException(int code, String message) {
			this.code = code;
			this.message = message;
		}
	}
}
