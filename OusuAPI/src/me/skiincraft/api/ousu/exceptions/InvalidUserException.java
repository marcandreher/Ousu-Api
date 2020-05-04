package me.skiincraft.api.ousu.exceptions;

@SuppressWarnings("serial")
public class InvalidUserException extends IllegalArgumentException {

	public InvalidUserException(String message) {
		super(message);
	}

}
