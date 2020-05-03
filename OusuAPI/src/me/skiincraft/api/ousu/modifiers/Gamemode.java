package me.skiincraft.api.ousu.modifiers;

public enum Gamemode {
	
	Standard(0, "Osu!"), Taiko(1, "Osu!Taiko"), Catch_the_Beat(2, "Osu!Ctb"), Mania(3, "Osu!Mania");
	
	private int id;
	private String displayName;
	
	Gamemode(int id, String displayName){
		this.id = id;
		this.displayName = displayName;
	}
	

	public int getId() {
		return id;
	}

	public String getDisplayName() {
		return displayName;
	}
	
	

}
