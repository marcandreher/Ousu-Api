package me.skiincraft.api.ousu.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.multiplayer.Game;
import me.skiincraft.api.ousu.entity.multiplayer.MultiplayerScore;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.user.User;

public class MultiplayerScoreImpl implements MultiplayerScore {

	private final JsonObject object;
	private final Game game;
	private final OusuAPI api;
	
	public MultiplayerScoreImpl(JsonObject object, Game game, OusuAPI api) {
		this.object = object;
		this.game = game;
		this.api = api;
	}

	public int getSlot() {
		return object.get("slot").getAsInt();
	}

	public int getTeam() {
		return object.get("team").getAsInt();
	}
	
	public Request<User> getUser(Gamemode gamemode) {
		return api.getUser(getUserId(), gamemode);
	}

	public long getUserId() {
		return object.get("user_id").getAsLong();
	}

	public long getScore() {
		return object.get("score").getAsLong();
	}

	public int getMaxCombo() {
		return object.get("maxcombo").getAsInt();
	}

	public int get50() {
		return object.get("count50").getAsInt();
	}

	public int get100() {
		return object.get("count100").getAsInt();
	}

	public int get300() {
		return object.get("count300").getAsInt();
	}

	public int getMiss() {
		return object.get("countmiss").getAsInt();
	}

	public int getGekis() {
		return object.get("countgeki").getAsInt();
	}

	public int getKatus() {
		return object.get("countkatu").getAsInt();
	}

	public boolean isPerfect() {
		return object.get("perfect").getAsInt() != 0;
	}

	public boolean isPassed() {
		return object.get("pass").getAsInt() != 0;
	}

	public Mods[] getEnabledMods() {
		JsonElement ele = object.get("enabled_mods");
		return Mods.get((ele.isJsonNull() ? 0 : ele.getAsLong()));
	}

	public Game getGame() {
		return game;
	}

}
