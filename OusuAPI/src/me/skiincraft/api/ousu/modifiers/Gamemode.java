package me.skiincraft.api.ousu.modifiers;

import java.util.HashMap;
import java.util.Map;

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
	
	public static Gamemode getGamemode(String gamemode) {
		String gm = gamemode.toLowerCase();
		Map<String, Gamemode> map = new HashMap<>();

		map.put("osu", Gamemode.Standard);
		map.put("osu!ctb", Gamemode.Catch_the_Beat);
		map.put("osuctb", Gamemode.Catch_the_Beat);
		map.put("osumania", Gamemode.Mania);
		map.put("osutaiko", Gamemode.Taiko);
		
		map.put("osu!", Gamemode.Standard);
		map.put("osu!catch", Gamemode.Catch_the_Beat);
		map.put("osu!mania", Gamemode.Mania);
		map.put("osu!taiko", Gamemode.Taiko);
		
		map.put("padrão", Gamemode.Standard);
		map.put("default", Gamemode.Standard);
		map.put("standard", Gamemode.Standard);
		map.put("catch", Gamemode.Catch_the_Beat);
		map.put("mania", Gamemode.Mania);
		map.put("taiko", Gamemode.Taiko);

		if (map.containsKey(gm)) {
			return map.get(gamemode);
		}
		return null;
	}
	
	

}
