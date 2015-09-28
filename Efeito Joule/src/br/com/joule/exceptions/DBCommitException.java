package br.com.joule.exceptions;

public class DBCommitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7763105965046781013L;

	public DBCommitException() {
		super();
		
	}

	public DBCommitException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public DBCommitException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public DBCommitException(String message) {
		super(message);
		
	}

	public DBCommitException(Throwable cause) {
		super(cause);
		
	}

}
