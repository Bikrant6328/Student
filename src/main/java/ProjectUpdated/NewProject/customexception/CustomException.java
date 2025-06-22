package ProjectUpdated.NewProject.customexception;

public class CustomException extends Exception {

	private String code;
	private String message;
	
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
	public CustomException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public CustomException() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CustomException [code=" + code + ", message=" + message + "]";
	}
	
	
	
	
}
