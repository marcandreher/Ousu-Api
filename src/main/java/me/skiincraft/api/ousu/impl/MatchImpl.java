package me.skiincraft.api.ousu.impl;

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
import me.skiincraft.api.ousu.entity.multiplayer.Game;
import me.skiincraft.api.ousu.entity.multiplayer.Match;

public class MatchImpl implements Match{
	
	private final JsonObject completeobject;
	private final JsonObject object;
	private final OusuAPI api;
	
	public MatchImpl(JsonObject object, OusuAPI api) {
		this.completeobject = object;
		this.object = object.get("match").getAsJsonObject();
		this.api = api;
	}

	public long getMatchId() {
		return object.get("match_id").getAsLong();
	}

	public String getName() {
		return object.get("name").getAsString();
	}

	private OffsetDateTime getDate(String parse) {
		if (object.get(parse).isJsonNull()){
			return null;
		}
		LocalDateTime time = LocalDateTime.parse(object.get(parse).getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return OffsetDateTime.of(time, ZoneOffset.UTC);
	}

	public OffsetDateTime getStartTime() {
		return getDate("start_time");
	}

	public OffsetDateTime getEndTime() {
		return getDate("end_time");
	}
	
	public OusuAPI getApi() {
		return api;
	}

	public List<Game> getGames() {
		List<Game> games = new ArrayList<>();
		if (completeobject.get("games").isJsonNull()) {
			return games;
		}
		
		JsonArray array = completeobject.get("games").getAsJsonArray();
		if (array.size() == 0) {
			return games;
		}
	
		for (JsonElement ele : array) {
			JsonObject gm = ele.getAsJsonObject();
			games.add(new GameImpl(gm, this, api));
		}
		
		return games;
	}
	
	public String toString() {
		return "[name=" + getName() +", matchId=" + getMatchId()+ ", games=" + getGames()+ "]";
	}

}
