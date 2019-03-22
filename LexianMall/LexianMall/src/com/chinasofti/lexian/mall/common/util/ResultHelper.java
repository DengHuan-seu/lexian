package com.chinasofti.lexian.mall.common.util;

// 服务端送到客户端的数据包装器
public class ResultHelper {
	private Object data;
	private Integer code;
	private String message;
	private Object total;

	public Object getTotal() {
		return total;
	}

	public void setTotal(Object total) {
		this.total = total;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public ResultHelper() {

	}

	public ResultHelper(int code, String message) {
		this.message = message;
		this.code = code;
	}

	public ResultHelper(int code, String message, Object object) {
		this.message = message;
		this.code = code;
		this.data = object;
	}

	public ResultHelper(int code, String message, Object object, int total) {
		this.message = message;
		this.code = code;
		this.data = object;
		this.total = total;
	}

}
