package me.skiincraft.api.ousu.exceptions;

public class TokenException extends IllegalArgumentException {

	private final Exception originalerror;
	
	public TokenException(String message, Exception originalerror) {
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
