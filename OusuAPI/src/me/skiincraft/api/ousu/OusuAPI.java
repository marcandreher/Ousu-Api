package me.skiincraft.api.ousu;

import java.util.List;

import com.github.kevinsawicki.http.HttpRequest;

import me.skiincraft.api.ousu.beatmaps.Beatmap;
import me.skiincraft.api.ousu.beatmaps.BeatmapBuilder;
import me.skiincraft.api.ousu.beatmaps.BeatmapSetBuilder;
import me.skiincraft.api.ousu.exceptions.InvalidTokenException;
import me.skiincraft.api.ousu.modifiers.Gamemode;
import me.skiincraft.api.ousu.scores.Score;
import me.skiincraft.api.ousu.scores.ScoreBuilder;
import me.skiincraft.api.ousu.users.RecentScoreBuilder;
import me.skiincraft.api.ousu.users.TopScoreBuilder;
import me.skiincraft.api.ousu.users.User;
import me.skiincraft.api.ousu.users.UserBuilder;

public class OusuAPI {
	
	private String token;
	
	public OusuAPI(String token) {
		this.setToken(token);
		
		if (token == null || token == "") {
				throw new InvalidTokenException("API Key esta nula, insira uma API Key.", null);
		}
		
		
		HttpRequest bc = HttpRequest.get("https://osu.ppy.sh/api/get_user?k=" + token);
		bc.accept("application/json").contentType();
		String body = bc.body();
		
		if (body.contains("Please provide a valid API key.")) {
			try {
				throw new InvalidTokenException("Insira uma API key valida.", null);
			} catch (InvalidTokenException e) {
				e.printStackTrace();
				return;
			}
		}
		
	}

	public static void main(String[] args) {
		//Ignore this
	}
	
	public Beatmap getBeatmap(int id) {
		BeatmapBuilder cons = new BeatmapBuilder(id);
		cons.setAPI(this);
			return cons.build();
	}
	
	public List<Beatmap> getBeatmapSet(int id) {
		BeatmapSetBuilder cons = new BeatmapSetBuilder(id);
		cons.setAPI(this);
		return cons.build();
	}
	
	public List<Score> getBeatmapScore(int beatmapid, int limit) {
		ScoreBuilder cons = new ScoreBuilder(beatmapid, limit);
		cons.setAPI(this);
		return cons.buildList();
	}
	
	public Score getBeatmapScore(int beatmapid, String user) {
		ScoreBuilder cons = new ScoreBuilder(beatmapid, user);
		cons.setAPI(this);
			return cons.build();
	}
	
	public Score getRecentUser(String user) {
		RecentScoreBuilder cons = new RecentScoreBuilder(user, 1);
		cons.setAPI(this);
			return cons.build();
	}
	
	public Score getRecentUser(String user, Gamemode mode) {
		RecentScoreBuilder cons = new RecentScoreBuilder(user, mode, 1);
		cons.setAPI(this);
			return cons.build();
	}
	
	public List<Score> getRecentUser(String user, int limit) {
		RecentScoreBuilder cons = new RecentScoreBuilder(user, limit);
		cons.setAPI(this);
			return cons.buildList();
	}
	
	public List<Score> getRecentUser(String user, Gamemode mode, int limit) {
		RecentScoreBuilder cons = new RecentScoreBuilder(user, mode, limit);
		cons.setAPI(this);
		return cons.buildList();
	}
	
	public Score getTopUser(String user) {
		TopScoreBuilder cons = new TopScoreBuilder(user, 1);
		cons.setAPI(this);
		return cons.build();
	}
	
	public Score getTopUser(String user, Gamemode mode) {
		TopScoreBuilder cons = new TopScoreBuilder(user, mode, 1);
		cons.setAPI(this);
			return cons.build();
	}
	
	public List<Score> getTopUser(String user, int limit) {
		TopScoreBuilder cons = new TopScoreBuilder(user, limit);
		cons.setAPI(this);
			return cons.buildList();
	}
	
	public List<Score> getTopUser(String user, Gamemode mode, int limit) {
		TopScoreBuilder cons = new TopScoreBuilder(user, mode, limit);
		cons.setAPI(this);
			return cons.buildList();
	}
	
	public User getUser(String user) {
		UserBuilder cons = new UserBuilder(user);
		cons.setAPI(this);
			return cons.build();
	}
	
	public User getUser(int user) {
		UserBuilder cons = new UserBuilder(user+"");
		cons.setAPI(this);
		return cons.build();
	}
	
	public User getUser(String user, Gamemode mode) {
		UserBuilder cons = new UserBuilder(user, mode);
		cons.setAPI(this);
		return cons.build();
	}
	
	public User getUser(int user, Gamemode mode) {
		UserBuilder cons = new UserBuilder(user+"", mode);
		cons.setAPI(this);
		return cons.build();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
