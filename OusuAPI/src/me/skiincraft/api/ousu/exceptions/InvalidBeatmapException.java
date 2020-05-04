package me.skiincraft.api.ousu.exceptions;

@SuppressWarnings("serial")
public class InvalidBeatmapException extends IllegalArgumentException {

	public InvalidBeatmapException(String message) {
		super(message);
	}

}
