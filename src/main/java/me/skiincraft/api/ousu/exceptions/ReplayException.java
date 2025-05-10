package me.skiincraft.api.ousu.exceptions;

public class ReplayException extends RuntimeException {

	private final Exception originalerror;
	
	public ReplayException(String message, Exception originalerror) {
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
