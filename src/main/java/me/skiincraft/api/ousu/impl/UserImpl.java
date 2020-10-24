package me.skiincraft.api.ousu.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.PlayedTime;
import me.skiincraft.api.ousu.entity.objects.ProfileEvents;
import me.skiincraft.api.ousu.entity.score.RecentScore;
import me.skiincraft.api.ousu.entity.score.Score;
import me.skiincraft.api.ousu.entity.user.User;

public class UserImpl implements User {

	private final JsonObject object;
	private final OusuAPI api;
	private final Gamemode mode;
	
	public UserImpl(JsonObject object, Gamemode gamemode,OusuAPI api) {
		this.object = object;
		this.api = api;
		this.mode = gamemode;
	}

	
	@Override
	public String getUsername() {
		return object.get("username").getAsString();
	}
	
	
	@Override
	public long getUserId() {
		return object.get("user_id").getAsLong();
	}
	
	@Override
	public long getTotalScore() {
		return object.get("total_score").getAsLong();
	}
	
	@Override
	public int getSh() {
		return object.get("count_rank_sh").getAsInt();
	}
	
	@Override
	public int getSSh() {
		return object.get("count_rank_ssh").getAsInt();
	}
	
	@Override
	public int getSS() {
		return object.get("count_rank_ss").getAsInt();
	}
	
	@Override
	public int getS() {
		return object.get("count_rank_s").getAsInt();
	}
	
	@Override
	public int getRanking() {
		return object.get("pp_rank").getAsInt();
	}
	
	@Override
	public long getRankedScore() {
		return object.get("ranked_score").getAsLong();
	}
	
	@Override
	public int getPlayCount() {
		return object.get("playcount").getAsInt();
	}
	
	@Override
	public float getPP() {
		return object.get("pp_raw").getAsFloat();
	}
	
	@Override
	public int getCountryRanking() {
		return object.get("pp_country_rank").getAsInt();
	}
	
	@Override
	public float getLevel() {
		return object.get("level").getAsFloat();
	}

	private OffsetDateTime getDate() {
		if (object.get("join_date").isJsonNull()){
			return null;
		}
		LocalDateTime time = LocalDateTime.parse(object.get("join_date").getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return OffsetDateTime.of(time, ZoneOffset.UTC);
	}

	@Override
	public OffsetDateTime getJoinDate() {
		return getDate();
	}
	
	@Override
	public List<ProfileEvents> getProfileEvents() {
		List<ProfileEvents> evnlist = new ArrayList<>();
		if (object.get("events").toString().contains("[]")) {
			return evnlist;
		}

		JsonArray array = object.get("events").getAsJsonArray();
		
		if (array.size() == 0) {
			return evnlist;
		}

		for (JsonElement ele : array) {
			JsonObject evn = ele.getAsJsonObject();
			if (!evn.has("beatmap_id"))
				continue;

			evnlist.add(new ProfileEvents(evn.get("display_html").getAsString(), evn.get("beatmap_id").getAsInt(),
					evn.get("beatmapset_id").getAsInt(), evn.get("date").getAsString(),
					evn.get("epicfactor").getAsInt()));
		}
		return evnlist;
	}
	
	@Override
	public int getA() {
		return object.get("count_rank_a").getAsInt();
	}
	
	@Override
	public int get50() {
		return object.get("count50").getAsInt();
	}
	
	@Override
	public int get300() {
		return object.get("count300").getAsInt();
	}
	
	@Override
	public int get100() {
		return object.get("count100").getAsInt();
	}

	@Override
	public String getUserAvatar() {
		return "http://s.ppy.sh/a/" + getUserId() + ".png";
	}
	public Request<List<RecentScore>> getRecentScore(Gamemode gamemode, int limit) {
		return api.getRecentUser(getUserId()+"", gamemode, limit);
	}
	
	public Request<List<Score>> getTopScore(Gamemode gamemode, int limit) {
		return api.getTopUser(getUserId()+"", gamemode, limit);
	}

	@Override
	public float getAccuracy() {
		return object.get("accuracy").getAsFloat();
	}

	@Override
	public PlayedTime getPlayedHours() {
		BigDecimal decimal = new BigDecimal(object.get("total_seconds_played").getAsBigInteger());
	    long longVal = decimal.longValue();
	    
	    int days = (int)longVal / 86400;
	    int remainder = (int) longVal - days * 86400;
	    int hours = remainder / 3600;
	    remainder = remainder - hours *3600;
	    int mins = remainder / 60;
	    int secs = remainder - mins / 60;

	    return new PlayedTime(days ,hours, mins, secs);
	}

	@Override
	public String getCountryCode() {
		return object.get("country").getAsString();
	}

	@Override
	public String getURL() {
		return "https://osu.ppy.sh/users/" + getUserId();
	}

	@Override
	public String getUserFlag() {
		return "https://osu.ppy.sh/images/flags/" + getCountryCode() + ".png";
	}

	@Override
	public Gamemode getGamemode() {
		return mode;
	}

	@Override
	public String toString() {
		return "[username=" + getUsername()+ ", userId=" + getUserId() + "]";
	}
	
}
