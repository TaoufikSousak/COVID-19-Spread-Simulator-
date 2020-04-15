package tsousa01.hw5;

public class NotPossibleAmountException extends Exception{


	String msg;
	
	public NotPossibleAmountException(String msg) {
		this.msg=msg;
	}
	
	public String getMessage() {
		return msg;
	}
}
