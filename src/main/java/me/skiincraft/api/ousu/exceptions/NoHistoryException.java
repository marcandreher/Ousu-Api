package me.skiincraft.api.ousu.exceptions;

@SuppressWarnings("serial")
public class NoHistoryException extends RuntimeException {

	private Exception originalerror;
	
	public NoHistoryException(String message, Exception originalerror) {
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
