package me.skiincraft.api.ousu.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.score.Score;
import me.skiincraft.api.ousu.entity.user.User;

public class ScoreImpl implements Score {

	private final OusuAPI api;
	private final long beatmapid;
	private final JsonObject object;
	
	public ScoreImpl(JsonObject object, long beatmapid, OusuAPI api) {
		this.api = api;
		this.beatmapid = beatmapid;
		this.object = object;
	}

	private OffsetDateTime getDate() {
		if (object.get("date").isJsonNull()){
			return null;
		}
		LocalDateTime time = LocalDateTime.parse(object.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return OffsetDateTime.of(time, ZoneOffset.UTC);
	}
	
	public long getScoreId() {
		return object.get("score_id").getAsLong();
	}
	
	public long getBeatmapId() {
		return beatmapid;
	}

	public Request<Beatmap> getBeatmap() {
		return api.getBeatmap(getBeatmapId());
	}

	public long getScore() {
		return object.get("score").getAsLong();
	}

	public Request<User> getUser(Gamemode gamemode) {
		return api.getUser(getUsername(), gamemode);
	}

	public String getUsername() {
		return object.get("username").getAsString();
	}
	public int getMaxCombo() {
		return object.get("maxcombo").getAsInt();
	}

	public int get300() {
		return object.get("count300").getAsInt();
	}

	public int get100() {
		return object.get("count100").getAsInt();
	}

	public int get50() {
		return object.get("count50").getAsInt();
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

	public boolean isReplayAvailable() {
		return object.get("replay_available").getAsInt() == 1;
	}

	public Mods[] getEnabledMods() {
		return Mods.get(object.get("enabled_mods").getAsLong());
	}

	public OffsetDateTime getScoreDate() {
		return getDate();
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

	public float getScorePP() {
		return object.get("pp").getAsFloat();
	}

}
