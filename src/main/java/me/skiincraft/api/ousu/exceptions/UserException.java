package me.skiincraft.api.ousu.exceptions;

public class UserException extends IllegalArgumentException {

	private final Exception originalerror;
	
	public UserException(String message, Exception originalerror) {
		super(message);
		this.originalerror = originalerror;
	}
	
	public Exception getOriginalError() {
		if (originalerror == null) {
			return this;
		}
		return originalerror;
	}

}
