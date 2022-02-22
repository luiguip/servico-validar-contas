package tech.luigui.workers_management.exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 6216083032309876629L;
	
	public BusinessException(String message) {
		super(message);
	}
}
