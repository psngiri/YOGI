package yogi.base.io.api;

public class APIException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6463966960001270938L;

	protected APIException(String message, Throwable cause) {
		super(message, cause);
	}

	protected APIException(Throwable cause) {
		super(cause);
	}

}
