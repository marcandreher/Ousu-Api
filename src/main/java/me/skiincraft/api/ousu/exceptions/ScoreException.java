package me.skiincraft.api.ousu.exceptions;

@SuppressWarnings("serial")
public class ScoreException extends IllegalArgumentException {

	private final Exception originalerror;
	
	public ScoreException(String message, Exception originalerror) {
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
