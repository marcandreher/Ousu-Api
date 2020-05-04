package me.skiincraft.api.ousu.scores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.beatmaps.Beatmap;
import me.skiincraft.api.ousu.exceptions.InvalidBeatmapException;
import me.skiincraft.api.ousu.exceptions.InvalidUserException;
import me.skiincraft.api.ousu.json.EndPointScore;
import me.skiincraft.api.ousu.modifiers.Mods;
import me.skiincraft.api.ousu.users.User;
import me.skiincraft.api.ousu.users.UserBuilder;

public class ScoreBuilder {

	private String get = "https://osu.ppy.sh/api/get_scores";
	private OusuAPI api;
	
	private EndPointScore[] score;
	
	private int beatmapid;
	private int limit;
	
	private String user;
	
	public ScoreBuilder(int beatmapid, int limit) {
		this.beatmapid = beatmapid;
		this.limit = limit;
		this.user = null;
	}
	
	public ScoreBuilder(int beatmapid, String user) {
		this.beatmapid = beatmapid;
		this.limit = 1;
		this.user = user;
	}
	
	public void setAPI(OusuAPI api) {
		this.api = api;
	}
	
	private void connectionRequest() {
		HttpRequest bc;
		if (user != null) {
			bc = HttpRequest.get(get, true, "k", api.getToken(), "b", beatmapid+"", "u", user, "limit", "1");
		} else {
			bc = HttpRequest.get(get, true, "k", api.getToken(), "b", beatmapid+"", "limit", limit+"");
		}
		
		Gson g = new Gson();
		
		bc.accept("application/json").contentType();
		String body = bc.body();
		
		try {
		api.getBeatmap(beatmapid).getArtist();
		} catch (NullPointerException e) {
				throw new InvalidBeatmapException("Este beatmap solicitado não existe");
		}
		
		System.out.println(body);
		EndPointScore[] us = g.fromJson(body, EndPointScore[].class);
		
		score = us;
		
	}
	
	public Score build() {
		connectionRequest();
		EndPointScore sc = score[0];
		return new Score() {
			
			@Override
			public int getBeatmapID() {
				return sc.getBeatmap_id();
			}
			
			@Override
			public boolean isReplayAvailable() {
				if (sc.getReplay_available() != 1) {
					return false;
				}
				return true;
			}
			
			@Override
			public boolean isPerfect() {
				if (sc.getPerfect() != 1) {
					return false;
				}
				return true;
			}

			@Override
			public User getUser() {
				return new UserBuilder(sc.getUser_id()+"").build();
			}
			
			@Override
			public float getScorePP() {
				return sc.getPp();
			}
			
			@Override
			public long getScoreID() {
				return sc.getScore_id();
			}
			
			@Override
			public Date getScoreDate() {
				try {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sc.getDate());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			public int getScore() {
				return sc.getScore();
			}
			
			@Override
			public String getRank() {
				if (sc.getRank().equalsIgnoreCase("xh")) {
					return "SS+";
				}
				if (sc.getRank().equalsIgnoreCase("x")) {
					return "SS";
				}
				if (sc.getRank().equalsIgnoreCase("sh")) {
					return "S+";
				}
				
				return sc.getRank();
			}
			
			@Override
			public int getMiss() {
				return sc.getCountmiss();
			}
			
			@Override
			public int getMaxCombo() {
				return sc.getMaxcombo();
			}
			
			@Override
			public int getKatus() {
				return sc.getCountkatu();
			}
			
			@Override
			public int getGekis() {
				return sc.getCountgeki();
			}
			
			@Override
			public Mods[] getEnabledMods() {
				return Mods.get(sc.getEnabled_mods());
			}
			
			@Override
			public int get50() {
				return sc.getCount50();
			}
			
			@Override
			public int get300() {
				return sc.getCount300();
			}
			
			@Override
			public int get100() {
				return sc.getCount100();
			}

			@Override
			public Beatmap getBeatmap() {
				return api.getBeatmap(beatmapid);
			}

			@Override
			public List<Beatmap> getBeatmapSet() {
				return api.getBeatmapSet(api.getBeatmap(beatmapid).getBeatmapSetID());
			}
		};
	}
	
	public List<Score> buildList() {
		connectionRequest();
		List<Score> l = new ArrayList<Score>();
		for (EndPointScore sc : score) {
			l.add(new Score() {
				
				@Override
				public int getBeatmapID() {
					return this.getBeatmapID();
				}
				
				@Override
				public boolean isReplayAvailable() {
					if (sc.getReplay_available() != 1) {
						return false;
					}
					return true;
				}
				
				@Override
				public boolean isPerfect() {
					if (sc.getPerfect() != 1) {
						return false;
					}
					return true;
				}
				
				@Override
				public User getUser() {
					try {
						return new UserBuilder(sc.getUser_id()+"").build();
					} catch (InvalidUserException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				@Override
				public float getScorePP() {
					return sc.getPp();
				}
				
				@Override
				public long getScoreID() {
					return sc.getScore_id();
				}
				
				@Override
				public Date getScoreDate() {
					try {
						return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sc.getDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				@Override
				public int getScore() {
					return sc.getScore();
				}
				
				@Override
				public String getRank() {
					if (sc.getRank().equalsIgnoreCase("xh")) {
						return "SS+";
					}
					if (sc.getRank().equalsIgnoreCase("x")) {
						return "SS";
					}
					if (sc.getRank().equalsIgnoreCase("sh")) {
						return "S+";
					}
					
					return sc.getRank();
				}
				
				@Override
				public int getMiss() {
					return sc.getCountmiss();
				}
				
				@Override
				public int getMaxCombo() {
					return sc.getMaxcombo();
				}
				
				@Override
				public int getKatus() {
					return sc.getCountkatu();
				}
				
				@Override
				public int getGekis() {
					return sc.getCountgeki();
				}
				
				@Override
				public Mods[] getEnabledMods() {
					return Mods.get(sc.getEnabled_mods());
				}
				
				@Override
				public int get50() {
					return sc.getCount50();
				}
				
				@Override
				public int get300() {
					return sc.getCount300();
				}
				
				@Override
				public int get100() {
					return sc.getCount100();
				}

				@Override
				public Beatmap getBeatmap() {
					return api.getBeatmap(beatmapid);
				}

				@Override
				public List<Beatmap> getBeatmapSet() {
					return api.getBeatmapSet(api.getBeatmap(beatmapid).getBeatmapSetID());
				}
			});
		}
		try {
			l.get(0).get100();	
		} catch (IndexOutOfBoundsException ex) {
			throw new InvalidUserException("Não foi possivel pegar este score pois o jogador solicitado não está no historico");
		}
		return l;
	}

}