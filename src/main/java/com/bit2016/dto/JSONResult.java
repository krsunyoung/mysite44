package com.bit2016.dto;


public class JSONResult {

		private String result;	//"success" or "fail"
		private String message;	//result가 "fail"일때 원인
		private Object data;	//result가 "success"이면 전달해야할 데이터 객체
		
		public static JSONResult error(String message){
			return new JSONResult(false,null, message);
		}
		public static JSONResult success(Object data){
			return new JSONResult(true, data,null);
		}
		private JSONResult(){
			
		}
	
		private JSONResult(boolean isSuccess, Object data, String message){
			result = isSuccess ?"success" :"fail";
			this.data=data;
			this.message=message;
		}

		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Object getData() {
			return data;
		}
		public void setData(Object data) {
			this.data = data;
		}
}
