package me.skiincraft.api.ousu.exceptions;

@SuppressWarnings("serial")
public class InvalidTokenException extends IllegalArgumentException {

	private Exception originalerror;
	
	public InvalidTokenException(String message, Exception originalerror) {
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
