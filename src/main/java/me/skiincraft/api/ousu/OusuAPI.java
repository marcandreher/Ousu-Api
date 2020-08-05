package me.skiincraft.api.ousu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;

import com.github.kevinsawicki.http.HttpRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.beatmap.BeatmapSet;
import me.skiincraft.api.ousu.entity.multiplayer.Match;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.replay.Replay;
import me.skiincraft.api.ousu.entity.score.BeatmapScore;
import me.skiincraft.api.ousu.entity.score.RecentScore;
import me.skiincraft.api.ousu.entity.score.Score;
import me.skiincraft.api.ousu.entity.user.User;
import me.skiincraft.api.ousu.exceptions.InvalidTokenException;
import me.skiincraft.api.ousu.impl.BeatmapImpl;
import me.skiincraft.api.ousu.impl.BeatmapScoreImpl;
import me.skiincraft.api.ousu.impl.BeatmapSetImpl;
import me.skiincraft.api.ousu.impl.MatchImpl;
import me.skiincraft.api.ousu.impl.RecentScoreImpl;
import me.skiincraft.api.ousu.impl.ReplayImpl;
import me.skiincraft.api.ousu.impl.ScoreImpl;
import me.skiincraft.api.ousu.impl.UserImpl;

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
		//Ignore this.
	}
	
	public Request<Beatmap> getBeatmap(long id) {
		final OusuAPI api = this;
		return new Request<Beatmap>() {

			private final String get = "https://osu.ppy.sh/api/get_beatmaps";
			private Beatmap beatmap;
			private String json;
			
			public boolean isRequested() {
				return beatmap != null;
			}
			
			public Beatmap get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "b", Long.toString(id));
					bc.accept("application/json").contentType();

					json = bc.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					beatmap = new BeatmapImpl(array.get(0).getAsJsonObject(), api);
					return beatmap;
				}
				return beatmap;
			}

			public void getWithJson(BiConsumer<Beatmap, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public Beatmap getSample() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public Request<BeatmapSet> getBeatmapSet(long id) {
		final OusuAPI api = this;
		return new Request<BeatmapSet>() {
			private final String get = "https://osu.ppy.sh/api/get_beatmaps";
			private BeatmapSet beatmapSet;
			private String json;
			
			public boolean isRequested() {
				return beatmapSet != null;
			}
			
			public BeatmapSet get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "s", Long.toString(id));
					bc.accept("application/json").contentType();

					json = bc.body();
					JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
					beatmapSet = new BeatmapSetImpl(jsonArray, api);
					return beatmapSet;
				}
				return beatmapSet;
			}

			public void getWithJson(BiConsumer<BeatmapSet, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public BeatmapSet getSample() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public Request<BeatmapScore> getBeatmapScore(int beatmapid, int limit) {
		final OusuAPI api = this;
		return new Request<BeatmapScore>() {
			
			private final String get = "https://osu.ppy.sh/api/get_scores";
			private String json;
			private BeatmapScore beatmapScore;

			public boolean isRequested() {
				return beatmapScore != null;
			}
			
			public BeatmapScore get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "b", beatmapid + "", "limit",
							limit + "");
					json = bc.body();

					if (json.equalsIgnoreCase("[]")) {
						return null;
					}

					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<Score> scores = new ArrayList<>();
					for (JsonElement ele : array) {
						JsonObject object = ele.getAsJsonObject();
						scores.add(new ScoreImpl(object, beatmapid, api));
					}
					beatmapScore = new BeatmapScoreImpl(scores);
				}
				return beatmapScore;
			}

			public void getWithJson(BiConsumer<BeatmapScore, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public BeatmapScore getSample() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public Request<Score> getBeatmapScore(int beatmapid, String user) {
		final OusuAPI api = this;
		return new Request<Score>() {
			
			private final String get = "https://osu.ppy.sh/api/get_scores";
			private String json;
			private Score score;

			public boolean isRequested() {
				return score != null;
			}
			
			public Score get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "b", beatmapid+"", "u", user, "limit", "1");
					json = bc.body();
					
					if (json.equalsIgnoreCase("[]")) {
						return null;
					}
					
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();	
					score = new ScoreImpl(array.get(0).getAsJsonObject(), beatmapid, api);
				}
				
				return score;
			}

			public void getWithJson(BiConsumer<Score, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public Score getSample() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	
	public Request<List<Beatmap>> getBeatmapFromCreator(String username, int limit) {
		final OusuAPI api = this;
		return new Request<List<Beatmap>>() {
			
			private final String get = "https://osu.ppy.sh/api/get_beatmaps";
			private String json;
			private List<Beatmap> beatmaps;
			
			public void getWithJson(BiConsumer<List<Beatmap>, String> biConsumer) {
				biConsumer.accept(get(), token);
			}
			
			public List<Beatmap> getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public boolean isRequested() {
				return this.beatmaps != null;
			}
			
			public List<Beatmap> get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "u", username, "limit",
							(limit == 0) ? 500 : limit + "");
					bc.accept("application/json").contentType();
					json = bc.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();

					List<Beatmap> beatmaps = new ArrayList<>();
					for (JsonElement ele : array) {
						JsonObject object = ele.getAsJsonObject();
						Beatmap beatmap = new BeatmapImpl(object, api);
						beatmaps.add(beatmap);
					}
					this.beatmaps = beatmaps;
				}
				return beatmaps;
			}
		};
	}
	
	public Request<List<Beatmap>> getBeatmapSince(Date date) {
		final OusuAPI api = this;
		return new Request<List<Beatmap>>() {
			
			private final String get = "https://osu.ppy.sh/api/get_beatmaps";
			private String json;
			
			private List<Beatmap> beatmaps;
			public void getWithJson(BiConsumer<List<Beatmap>, String> biConsumer) {
				biConsumer.accept(get(), json);
				
			}
			
			public List<Beatmap> getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public List<Beatmap> get() {
				if (!isRequested()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "since", sdf.format(date));
					bc.accept("application/json").contentType();

					json = bc.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<Beatmap> beatmaps = new ArrayList<Beatmap>();
					for (JsonElement ele : array) {
						JsonObject object = ele.getAsJsonObject();
						beatmaps.add(new BeatmapImpl(object, api));
					}
					this.beatmaps = beatmaps;
				}
				return beatmaps;
			}
		};
	}
	
	public Request<List<RecentScore>> getRecentUser(String user, Gamemode mode, int limit) {
		final OusuAPI api = this;
		return new Request<List<RecentScore>>() {
			
			private final String get = "https://osu.ppy.sh/api/get_user_recent";
			private String json;
			private List<RecentScore> scores;
			
			public boolean isRequested() {
				return scores != null;
			}
			
			public void getWithJson(BiConsumer<List<RecentScore>, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public List<RecentScore> getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public List<RecentScore> get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "u", user, "m", mode.getId()+"","limit", limit+"");
					bc.accept("application/json").contentType();
					
					json = bc.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<RecentScore> scores = new ArrayList<>();
					for (JsonElement jsonElement : array) {
						JsonObject object = jsonElement.getAsJsonObject();
						scores.add(new RecentScoreImpl(object, api));
					}
					this.scores = scores;
				}
				return scores;
			}
		};
	}
	
	public Request<List<Score>> getTopUser(String user, Gamemode mode, int limit) {
		
		final OusuAPI api = this;
		return new Request<List<Score>>() {
			
			private final String get = "https://osu.ppy.sh/api/get_user_best";
			private String json;
			private List<Score> scores;
			
			public boolean isRequested() {
				return scores != null;
			}
			
			public void getWithJson(BiConsumer<List<Score>, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public List<Score> getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public List<Score> get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "u", user, "limit", limit+"", "m", mode.getId()+"");
					bc.accept("application/json").contentType();
					
					json = bc.body();
					JsonArray array = new JsonParser().parse(json).getAsJsonArray();
					List<Score> scores = new ArrayList<>();
					for (JsonElement jsonElement : array) {
						JsonObject object = jsonElement.getAsJsonObject();
						scores.add(new ScoreImpl(object, object.get("beatmap_id").getAsLong(), api));
					}
					this.scores = scores;
				}
				return scores;
			}
		};
	}
	
	public Request<User> getUser(long userId, Gamemode mode) {
		return getUser(String.valueOf(userId), mode);
	}
	
	public Request<User> getUser(String username, Gamemode mode) {
		final OusuAPI api = this;
		return new Request<User>() {
			
			private final String get = "https://osu.ppy.sh/api/get_user";
			private String json;
			private User user;
			
			public boolean isRequested() {
				return user != null;
			}
			public void getWithJson(BiConsumer<User, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public User getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public User get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "u", username, "m", mode.getId()+"");
					bc.accept("application/json").contentType();
					json = bc.body();
					JsonArray jsonArray = new JsonParser().parse(json).getAsJsonArray();
					
					if (jsonArray.size() == 0) {
						return null;
					}
					user = new UserImpl(jsonArray.get(0).getAsJsonObject(), mode, api);
				}
				return user;
			}
		};
	}
	
	public Request<Replay> getReplay(String user, int beatmapid) {
		final OusuAPI api = this;
		return new Request<Replay>() {
			
			private final String get = "https://osu.ppy.sh/api/get_replay";
			private Replay replay;
			private String json;
			
			
			public boolean isRequested() {
				return replay != null;
			}
			
			public void getWithJson(BiConsumer<Replay, String> biConsumer) {
				biConsumer.accept(replay, json);
			}
			
			public Replay getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Replay get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "b", beatmapid+"", "u", user);
					bc.accept("application/json").contentType();
					json = bc.body();
					JsonObject object = new JsonParser().parse(json).getAsJsonObject();
					
					replay = new ReplayImpl(object, beatmapid, api);
				}
				return replay;
			}
		};
	}
	
	public Request<Match> getMatch(long matchId){
		final OusuAPI api = this;
		return new Request<Match>() {
			private final String get = "https://osu.ppy.sh/api/get_match";
			private Match match;
			private String json;
			
			public boolean isRequested() {
				return match != null;
			}
			
			public void getWithJson(BiConsumer<Match, String> biConsumer) {
				biConsumer.accept(match, json);
			}
			
			public Match getSample() {
				// TODO Auto-generated method stub
				return null;
			}
			
			public Match get() {
				if (!isRequested()) {
					HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "mp", matchId);
					bc.accept("application/json").contentType();
					json = bc.body();
					JsonObject object = new JsonParser().parse(json).getAsJsonObject();
					
					match = new MatchImpl(object, api);
				}
				return match;
			}
		};
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
