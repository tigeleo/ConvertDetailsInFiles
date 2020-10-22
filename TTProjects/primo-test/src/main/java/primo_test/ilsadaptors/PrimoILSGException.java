package primo_test.ilsadaptors;


public class PrimoILSGException extends RuntimeException {

	/**
	 *
	 */
	private String errorCode = null;
	private String errorMessage = null;



	public PrimoILSGException(String errorCode, String errorMessage,Throwable throwable) {
		super(throwable);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage != null  ?errorMessage :throwable!=null?throwable.getMessage():null;
	}

	public PrimoILSGException(String errorCode,Throwable throwable) {
		this(errorCode,null,throwable);
	}


	public PrimoILSGException(String errorCode) {
		this(errorCode,null,null);
	}



	public PrimoILSGException(PrimoILSGException primoILSGException) {
		super(primoILSGException);
		if(primoILSGException != null){
			this.errorCode =primoILSGException.errorCode ;
			this.errorMessage = primoILSGException.errorMessage;
		}
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String getMessage() {
		return errorMessage != null ? errorMessage :super.getMessage();
	}



}
