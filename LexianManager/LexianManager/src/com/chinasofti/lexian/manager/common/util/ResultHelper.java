package com.chinasofti.lexian.manager.common.util;

public class ResultHelper {
	private Object rows;
	private  int code;
	private  String message;
	private  int total;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	

	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public ResultHelper(){
		
	}
	
	public ResultHelper(int code,String message){
		this.message = message;
		this.code = code;
	}
	
	public ResultHelper(int code,String message,Object object){
		this.message = message;
		this.code = code;
		this.rows = object;
	}
	public ResultHelper(int code,String message,Object object, int total){
		this.message = message;
		this.code = code;
		this.rows = object;
		this.total=total;
	}
	

}
