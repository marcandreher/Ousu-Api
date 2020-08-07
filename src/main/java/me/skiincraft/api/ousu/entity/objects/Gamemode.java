package me.skiincraft.api.ousu.entity.objects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**<h1>Gamemode</h1>
 * <p>These are Osu! game modes.</p>
 * <p>The Osu game has 4 Game modes:
 * <br>
 * Standard(0),
 * Taiko(1),
 * Catch(2),
 * Mania(3);
 * </br>
 */
public enum Gamemode {
	
	Standard(0, "Osu!"), Taiko(1, "Osu!Taiko"), Catch(2, "Osu!Ctb"), Mania(3, "Osu!Mania");
	
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
		map.put("osu!ctb", Gamemode.Catch);
		map.put("osuctb", Gamemode.Catch);
		map.put("osumania", Gamemode.Mania);
		map.put("osutaiko", Gamemode.Taiko);
		
		map.put("osu!", Gamemode.Standard);
		map.put("osu!catch", Gamemode.Catch);
		map.put("osu!mania", Gamemode.Mania);
		map.put("osu!taiko", Gamemode.Taiko);
		
		map.put("padrão", Gamemode.Standard);
		map.put("default", Gamemode.Standard);
		map.put("standard", Gamemode.Standard);
		map.put("catch", Gamemode.Catch);
		map.put("mania", Gamemode.Mania);
		map.put("taiko", Gamemode.Taiko);

		if (map.containsKey(gm)) {
			return map.get(gamemode);
		}
		return null;
	}
	
	public static Gamemode getById(int id) {
		return Arrays.stream(values()).filter(g -> g.id == id).findFirst().orElse(null);
	}
}
