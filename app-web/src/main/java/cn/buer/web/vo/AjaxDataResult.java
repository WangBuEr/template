package cn.buer.web.vo;

public class AjaxDataResult {
	private static final String SUCCESS = "10000";
	private static final String ERROR_SERVER = "10001";
	private String code;
	private String message;
	private Object data;
	public AjaxDataResult(String code,String message,Object data){
		this.code = code;
		this.message = message;
		this.data = data;
	}
	public static AjaxDataResult success(Object data){
		return new AjaxDataResult(AjaxDataResult.SUCCESS, null, data);
	}
	public static AjaxDataResult success(String message,Object data){
		return new AjaxDataResult(AjaxDataResult.SUCCESS, message, data);
	}
	public static AjaxDataResult serverError(String message,Object data){
		return new AjaxDataResult(AjaxDataResult.ERROR_SERVER, message, data);
	}
	public static AjaxDataResult serverError(String message){
		return new AjaxDataResult(AjaxDataResult.ERROR_SERVER, message, null);
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
