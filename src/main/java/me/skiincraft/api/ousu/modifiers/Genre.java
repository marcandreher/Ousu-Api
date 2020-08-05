package me.skiincraft.api.ousu.modifiers;

public enum Genre {
	
	ANY(0, "Any"),
	UNSPECIFIED(1, "Não especificado"),
	VIDEO_GAME(2, "Video Game"),
	ANIME(3, "Anime"),
	ROCK(4, "Rock"),
	POP(5, "Pop"),
	OTHER(6, "Other"),
	NOVELTY(7, "Novelty"),
	HIP_HOP(9, "Hip Hop"),
	ELECTRONIC(10, "Electronic");
	
	private int id;
	private String displayName;
	
	Genre(int id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
}
