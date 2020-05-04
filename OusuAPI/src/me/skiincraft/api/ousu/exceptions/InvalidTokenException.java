package me.skiincraft.api.ousu.exceptions;

@SuppressWarnings("serial")
public class InvalidTokenException extends IllegalArgumentException {

	public InvalidTokenException(String message) {
		super(message);
	}

}
