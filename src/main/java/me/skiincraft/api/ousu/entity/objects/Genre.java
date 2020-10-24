package me.skiincraft.api.ousu.entity.objects;

import java.util.Arrays;

/**<h1>Genre</h1>
 * <p>The types of music of the Beatmaps</p>
 * <p>There are 9 possible genres, including:
 * <br>
 * ANY(0),
 * UNSPECIFIED(1),
 * VIDEO_GAME(2),
 * ANIME(3),
 * ROCK(4),
 * POP(5),
 * OTHER(6),
 * NOVELTY(7),
 * HIP_HOP(9),
 * ELECTRONIC(10);
 * </br>
 */

public enum Genre {
	
	ANY(0, "Any"),
	UNSPECIFIED(1, "Unspecified"),
	VIDEO_GAME(2, "Video Game"),
	ANIME(3, "Anime"),
	ROCK(4, "Rock"),
	POP(5, "Pop"),
	OTHER(6, "Other"),
	NOVELTY(7, "Novelty"),
	HIP_HOP(9, "Hip Hop"),
	ELECTRONIC(10, "Electronic");
	
	private final int id;
	private final String displayName;
	
	Genre(int id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}
	
	public int getID() {
		return this.id;
	}
	
	public static Genre getById(int id) {
		return Arrays.stream(values()).filter(g -> g.getID() == id).findFirst().orElse(null);
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
}
