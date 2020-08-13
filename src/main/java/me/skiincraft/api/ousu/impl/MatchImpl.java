package me.skiincraft.api.ousu.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.entity.multiplayer.Game;
import me.skiincraft.api.ousu.entity.multiplayer.Match;

public class MatchImpl implements Match{
	
	private JsonObject completeobject;
	private JsonObject object;
	private OusuAPI api;
	
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
	
	private Date getDate(String parse) {
		try {
			return (object.get(parse).isJsonNull()) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.get(parse).getAsString());
		} catch (ParseException e) {
			return null;
		}
	}

	public Date getStartTime() {
		return getDate("start_time");
	}

	public Date getEndTime() {
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
