package me.skiincraft.api.ousu;

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
import me.skiincraft.api.ousu.exceptions.*;
import me.skiincraft.api.ousu.impl.*;
import me.skiincraft.api.ousu.requests.Request;

import okhttp3.OkHttpClient;
import okhttp3.HttpUrl;
import okhttp3.Response;
import okhttp3.ConnectionPool;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OusuAPI {
	
	private String token;
	private OkHttpClient httpClient;
		// Singleton OkHttpClient for optimal performance with connection pooling
	private static final OkHttpClient SHARED_HTTP_CLIENT = new OkHttpClient.Builder()
		.connectTimeout(10, TimeUnit.SECONDS)
		.readTimeout(30, TimeUnit.SECONDS)
		.writeTimeout(15, TimeUnit.SECONDS)
		.connectionPool(new ConnectionPool(10, 5, TimeUnit.MINUTES)) // Keep up to 10 idle connections open for 5 minutes
		.retryOnConnectionFailure(true) // Auto retry on connection failures
		.build();

	public OusuAPI(String token, boolean testToken) {
		this.setToken(token);
		this.httpClient = SHARED_HTTP_CLIENT;

		if (token == null || token.equals("")) {
			throw new TokenException("API Key is null, enter an API Key.", null);
		}

		if (token.length() < 36 || token.matches("-?\\d+(\\.\\d+)?")) {
			throw new TokenException("Enter a valid API key. The key you entered is invalid.", null);
		}

		if (testToken) {
			String body = executeGet("https://osu.ppy.sh/api/get_user", Map.of("k", token));
			checkHasValid(body);
		}
	}
	
	private void checkHasValid(String json) {
		if (json.contains("Please provide a valid API key.")) {
			throw new TokenException("Enter a valid API key. The key you entered is invalid.", null);
		}
	}
	
	public OusuAPI(String token) {
		this.setToken(token);
		this.httpClient = SHARED_HTTP_CLIENT;
		
		if (token == null || token.equals("")) {
			throw new TokenException("API Key is null, enter an API Key.", null);
		}
		
		if (token.length() < 36|| token.matches("-?\\d+(\\.\\d+)?")) {
			throw new TokenException("Enter a valid API key. The key you entered is invalid.", null);
		}
		
		String body = executeGet("https://osu.ppy.sh/api/get_user", Map.of("k", token));
		checkHasValid(body);
	}
		/**
	 * Executes a GET request using OkHttp client with optimized performance
	 * 
	 * @param url The base URL
	 * @param params Query parameters
	 * @return Response body as String
	 */
	private String executeGet(String url, Map<String, String> params) {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		
		// Add all query parameters
		for (Map.Entry<String, String> param : params.entrySet()) {
			urlBuilder.addQueryParameter(param.getKey(), param.getValue());
		}
				// Build a request without caching
		okhttp3.Request request = new okhttp3.Request.Builder()
				.url(urlBuilder.build())
				.get()
				.build();
		
		try (Response response = httpClient.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				throw new IOException("Unexpected response code: " + response);
			}
			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException("Failed to execute request: " + e.getMessage(), e);
		}
	}
	
	/**
	 * Parse JSON string to JsonArray
	 */
	private JsonArray parseJsonArray(String json) {
		return JsonParser.parseString(json).getAsJsonArray();
	}
	
	/**
	 * Parse JSON string to JsonObject
	 */
	private JsonObject parseJsonObject(String json) {
		return JsonParser.parseString(json).getAsJsonObject();
	}
	
	/**<h1>Request<{@linkplain Beatmap}></h1>
	 * <p>
	 * Make a request for a Beatmap.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param id specify a beatmap_id to return metadata from.
	 *  
	 *  @see Request
	 *  @see Beatmap
	 */
	public Request<Beatmap> getBeatmap(long id) {
		final OusuAPI api = this;
		return new Request<Beatmap>() {

			private Beatmap beatmap;
			private String json;
			
			public boolean wasRequested() {
				return beatmap != null;
			}
			
			public Beatmap get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_beatmaps";
					json = executeGet(get, Map.of("k", api.getToken(), "b", Long.toString(id)));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					
					if (array.size() == 0) {
						throw new BeatmapException("This requested beatmap was not found. (jsonnull)", null);
					}
					beatmap = new BeatmapImpl(array.get(0).getAsJsonObject(), api);
				}
				return beatmap;
			}

			public void getWithJson(BiConsumer<Beatmap, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public Beatmap getSample() {
				return Beatmap.getSample();
			}
		};
	}

	public Request<Beatmap> getBeatmapByChecksum(String checksum) {
		final OusuAPI api = this;
		return new Request<Beatmap>() {

			private Beatmap beatmap;
			private String json;
			
			public boolean wasRequested() {
				return beatmap != null;
			}
			
			public Beatmap get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_beatmaps";
					json = executeGet(get, Map.of("k", api.getToken(), "h", checksum));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					
					if (array.size() == 0) {
						throw new BeatmapException("This requested beatmap was not found. (jsonnull)", null);
					}
					beatmap = new BeatmapImpl(array.get(0).getAsJsonObject(), api);
				}
				return beatmap;
			}

			public void getWithJson(BiConsumer<Beatmap, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public Beatmap getSample() {
				return Beatmap.getSample();
			}
		};
	}
	
	/**<h1>Request<{@linkplain BeatmapSet}></h1>
	 * <p>
	 * Make a request for a BeatmapSet.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param id specify a beatmapset_id to return metadata from.
	 *  @see Request
	 *  @see BeatmapSet
	 */
	public Request<BeatmapSet> getBeatmapSet(long id) {
		final OusuAPI api = this;
		return new Request<BeatmapSet>() {
			private BeatmapSet beatmapSet;
			private String json;
			
			public boolean wasRequested() {
				return beatmapSet != null;
			}
			
			public BeatmapSet get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_beatmaps";
					json = executeGet(get, Map.of("k", api.getToken(), "s", Long.toString(id)));
					
					checkHasValid(json);
					JsonArray jsonArray = parseJsonArray(json);
					if (jsonArray.size() == 0) {
						throw new BeatmapException("This requested beatmap was not found.", null);
					}
					return beatmapSet = new BeatmapSetImpl(jsonArray, api);
				}
				return beatmapSet;
			}

			public void getWithJson(BiConsumer<BeatmapSet, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public BeatmapSet getSample() {
				return BeatmapSet.getSample();
			}
		};
	}
	
	/**<h1>Request<{@linkplain BeatmapScore}></h1>
	 * <p>
	 * Make a request for a Beatmap Scoreboard.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param beatmapId specify a beatmap_id to return metadata from.
	 *  @param limit Is the limit of responses you want to receive. <br>(range between 1 and 100 - defaults to 50).</br>
	 *  
	 *  @see Request
	 *  @see BeatmapSet
	 */
	public Request<BeatmapScore> getBeatmapScore(int beatmapId, int limit) {
		final OusuAPI api = this;
		return new Request<BeatmapScore>() {
			private String json;
			private BeatmapScore beatmapScore;

			public boolean wasRequested() {
				return beatmapScore != null;
			}
			
			public BeatmapScore get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_scores";
					json = executeGet(get, Map.of("k", api.getToken(), "b", beatmapId + "", "limit", limit + ""));
					
					checkHasValid(json);
					JsonArray jsonArray = parseJsonArray(json);
					
					if (jsonArray.size() == 0) {
						throw new BeatmapException("This requested beatmap was not found.", null);
					}

					List<Score> scores = new ArrayList<>();
					for (JsonElement ele : jsonArray) {
						JsonObject object = ele.getAsJsonObject();
						scores.add(new ScoreImpl(object, beatmapId, api));
					}

					beatmapScore = new BeatmapScoreImpl(scores);
				}
				return beatmapScore;
			}

			public void getWithJson(BiConsumer<BeatmapScore, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public BeatmapScore getSample() {
				return BeatmapScore.getSample();
			}
		};
	}
	
	/**<h1>Request<{@linkplain Score}></h1>
	 * <p>
	 * Request a score from a specific player.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param user specify a user_id or a username to return score information for.
	 *  
	 *  @see Request
	 *  @see Score
	 */
	public Request<Score> getBeatmapScore(int beatmapid, String user) {
		final OusuAPI api = this;
		return new Request<Score>() {

			private String json;
			private Score score;

			public boolean wasRequested() {
				return score != null;
			}
			
			public Score get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_scores";
					json = executeGet(get, Map.of("k", api.getToken(), "b", beatmapid+"", "u", user, "limit", "1"));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					
					if (array.size() == 0) {
						throw new ScoreException("The requested player's score could not be found on this beatmap or the beatmap is invalid.", null);
					}
					score = new ScoreImpl(array.get(0).getAsJsonObject(), beatmapid, api);
				}
				
				return score;
			}

			public void getWithJson(BiConsumer<Score, String> biConsumer) {
				biConsumer.accept(get(), json);
			}

			public Score getSample() {
				return Score.getSample();
			}
		};
	}
	
	/**<h1>Request<{@linkplain Beatmap}> </h1>
	 * <p>
	 * Make a request for maps created by a specific player.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param user specify a user_id or a username to return score information for.
	 *  @param limit Is the limit of responses you want to receive. <br>(range between 1 and 100 - defaults to 50).</br>
	 *  
	 *  @see Request
	 *  @see Beatmap
	 */
	public Request<List<Beatmap>> getBeatmapFromCreator(String user, int limit) {
		final OusuAPI api = this;
		return new Request<List<Beatmap>>() {
			private String json;
			private List<Beatmap> beatmaps;
			
			public void getWithJson(BiConsumer<List<Beatmap>, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public List<Beatmap> getSample() {
				return BeatmapSet.getSample().getAsList();
			}
			
			public boolean wasRequested() {
				return this.beatmaps != null;
			}
			
			public List<Beatmap> get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_beatmaps";
					json = executeGet(get, Map.of("k", api.getToken(), "u", user, "limit", (limit == 0) ? "500" : limit + ""));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					if (array.size() == 0) {
						throw new BeatmapException("It was not possible to find beatmaps created by this player or the player does not exist.", null);
					}


					List<Beatmap> beatmaps = new ArrayList<>();
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
	
	/**<h1>Request<{@linkplain Beatmap}> </h1>
	 * <p>
	 * Make a request for available maps on a specific date.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param date return all beatmaps ranked or loved since this date. Must be a MySQL date. In UTC

	 *  @see Request
	 *  @see Beatmap
	 */
	public Request<List<Beatmap>> getBeatmapSince(Date date) {
		final OusuAPI api = this;
		return new Request<List<Beatmap>>() {

			private String json;
			
			private List<Beatmap> beatmaps;
			public void getWithJson(BiConsumer<List<Beatmap>, String> biConsumer) {
				biConsumer.accept(get(), json);
				
			}
			
			public List<Beatmap> getSample() {
				return BeatmapSet.getSample().getAsList();
			}
			
			public List<Beatmap> get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_beatmaps";
					json = executeGet(get, Map.of("k", api.getToken(), "since", new SimpleDateFormat("yyyy-MM-dd").format(date)));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					if (array.size() == 0) {
						throw new BeatmapException("It was not possible to find beatmaps for the requested date.", null);
					}
					List<Beatmap> beatmaps = new ArrayList<>();
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
	
	/**<h1>Request<{@linkplain RecentScore}> </h1>
	 * <p>
	 * Make a request for recent scores by a specific player.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param user Specify a user_id or a username to return recent plays from.
	 *  @param gamemode Is the gamemode (Standard, Taiko, Catch the beat, Mania). default value is Standard.
	 *  @param limit Is the limit of responses you want to receive.<br>Range between 1 and 50 - the default is 10</br>.
	 *  
	 *  @see Request
	 *  @see RecentScore
	 */
	public Request<List<RecentScore>> getRecentUser(String user, Gamemode gamemode, int limit) {
		final OusuAPI api = this;
		return new Request<List<RecentScore>>() {

			private String json;
			private List<RecentScore> scores;
			
			public boolean wasRequested() {
				return scores != null;
			}
			
			public void getWithJson(BiConsumer<List<RecentScore>, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public List<RecentScore> getSample() {
				return RecentScore.getSampleCollection();
			}
			
			public List<RecentScore> get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_user_recent";
					json = executeGet(get, Map.of("k", api.getToken(), "u", user, "m", gamemode.getId()+"","limit", limit+""));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					if (array.size() == 0) {
						throw new ScoreException("It was not possible to find recent maps of the requested player, or the requested player did not exist.", null);
					}
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
	
	/**<h1>Request<{@linkplain Score}> </h1>
	 * <p>
	 * Make a request a specific player.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param user Specify a user_id or a username to return top plays from.
	 *  @param limit Is the limit of responses you want to receive.<br>Range between 1 and 50 - the default is 10</br>.
	 *  
	 *  @see Request
	 *  @see RecentScore
	 */
	public Request<List<Score>> getTopUser(String user, Gamemode mode, int limit) {
		
		final OusuAPI api = this;
		return new Request<List<Score>>() {

			private String json;
			private List<Score> scores;
			
			public boolean wasRequested() {
				return scores != null;
			}
			
			public void getWithJson(BiConsumer<List<Score>, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public List<Score> getSample() {
				return Score.getSampleTop();
			}
			
			public List<Score> get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_user_best";
					json = executeGet(get, Map.of("k", api.getToken(), "u", user, "limit", limit+"", "m", mode.getId()+""));
					
					checkHasValid(json);
					JsonArray array = parseJsonArray(json);
					
					if (array.size() == 0) {
						throw new ScoreException("It was not possible to find top maps of the requested player, or the requested player did not exist.", null);
					}
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
	
	/**<h1>Request<{@linkplain User}> </h1>
	 * <p>
	 * Make a request a specific player.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param gamemode Is the gamemode (Standard, Taiko, Catch the beat, Mania). default value is Standard.
	 *  
	 *  @see Request
	 *  @see RecentScore
	 */
	public Request<User> getUser(String username, Gamemode gamemode) {
		final OusuAPI api = this;
		return new Request<User>() {

			private String json;
			private User user;
			
			public boolean wasRequested() {
				return user != null;
			}
			public void getWithJson(BiConsumer<User, String> biConsumer) {
				biConsumer.accept(get(), json);
			}
			
			public User getSample() {
				return User.getSample();
			}
			
			public User get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_user";
					json = executeGet(get, Map.of("k", api.getToken(), "u", username, "m", gamemode.getId()+""));
					
					checkHasValid(json);
					JsonArray jsonArray = parseJsonArray(json);
					if (jsonArray.size() == 0) {
						throw new UserException("This requested user was not found.", null);
					}
					user = new UserImpl(jsonArray.get(0).getAsJsonObject(), gamemode, api);
				}
				return user;
			}
		};
	}
	
	
	/**<h1>Request<{@linkplain Replay}> </h1>
	 * <p>
	 * Make a request for replay by a specific player and beatmap.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param user the user that has played the beatmap
	 *  @param beatmapid the beatmap ID (not beatmap set ID!) in which the replay was played
	 *  
	 *  @see Request
	 *  @see RecentScore
	 */
	public Request<Replay> getReplay(String user, int beatmapid) {
		final OusuAPI api = this;
		return new Request<Replay>() {

			private Replay replay;
			private String json;
			
			
			public boolean wasRequested() {
				return replay != null;
			}
			
			public void getWithJson(BiConsumer<Replay, String> biConsumer) {
				biConsumer.accept(replay, json);
			}
			
			public Replay getSample() {
				return Replay.getSample();
			}
			
			public Replay get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_replay";
					json = executeGet(get, Map.of("k", api.getToken(), "b", beatmapid+"", "u", user));
					
					checkHasValid(json);
					JsonObject object = parseJsonObject(json);
					if (object.size() == 0) {
						throw new ReplayException("It was not possible to find the replay of this user, or beatmap/user does not exist.", null);
					}
					
					replay = new ReplayImpl(object, beatmapid, api);
				}
				return replay;
			}
		};
	}
	
	/**<h1>Request<{@linkplain Match}> </h1>
	 * <p>
	 * Make a request for match by match_Id.
	 * 
	 * <br>The request is received in Json, and converted to a Class.</br>
	 * <br>The information obtained is or can be limited.</br>
	 * </p>
	 *  
	 *  <p>Read the Oficial Wiki at:
	 *  <br>(https://github.com/ppy/osu-api/wiki)</br></p>
	 *  
	 *  @param matchId for match information
	 *  
	 *  @see Request
	 *  @see RecentScore
	 */
	public Request<Match> getMatch(long matchId){
		final OusuAPI api = this;
		return new Request<Match>() {
			private Match match;
			private String json;
			
			public boolean wasRequested() {
				return match != null;
			}
			
			public void getWithJson(BiConsumer<Match, String> biConsumer) {
				biConsumer.accept(match, json);
			}
			
			public Match getSample() {
				return Match.getSample();
			}
			
			public Match get() {
				if (!wasRequested()) {
					String get = "https://osu.ppy.sh/api/get_match";
					json = executeGet(get, Map.of("k", api.getToken(), "mp", String.valueOf(matchId)));
					
					checkHasValid(json);
					JsonObject object = parseJsonObject(json);
					
					if (object.size() == 0 || object.get("match").getAsLong() == 0) {
						throw new me.skiincraft.api.ousu.exceptions.MatchException("The requested match was not found.", null);
					}
					
					
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
