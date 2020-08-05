package me.skiincraft.api.ousu.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.score.RecentScore;
import me.skiincraft.api.ousu.entity.user.User;

public class RecentScoreImpl implements RecentScore {

	public JsonObject object;
	public OusuAPI api;
	
	public RecentScoreImpl(JsonObject object, OusuAPI api) {
		this.object = object;
		this.api = api;
	}
	
	public long getBeatmapId() {
		return object.get("beatmap_id").getAsLong();
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

	public int getKatus() {
		return object.get("countkatu").getAsInt();
	}

	public int getGekis() {
		return object.get("countgeki").getAsInt();
	}

	public boolean isPerfect() {
		return object.get("perfect").getAsInt() == 1;
	}

	public Mods[] getEnabledMods() {
		return Mods.get(object.get("enabled_mods").getAsLong());
	}
	
	public long getUserId() {
		return object.get("user_id").getAsLong();
	}
	public Request<User> getUser(Gamemode gamemode) {
		return api.getUser(getUserId()+"", gamemode);
	}

	public Date getDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.get("date").getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRank() {
		String rank = object.get("rank").getAsString();
		if (rank.equalsIgnoreCase("xh")) {
			return "SS+";
		}
		if (rank.equalsIgnoreCase("x")) {
			return "SS";
		}
		if (rank.equalsIgnoreCase("sh")) {
			return "S+";
		}
		
		return rank;
	}

}
