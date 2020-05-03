package me.skiincraft.api.ousu.users;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.json.EndPointScore;
import me.skiincraft.api.ousu.modifiers.Gamemode;
import me.skiincraft.api.ousu.modifiers.Mods;
import me.skiincraft.api.ousu.scores.Score;
import me.skiincraft.api.ousu.users.User;
import me.skiincraft.api.ousu.users.UserBuilder;

public class TopScoreBuilder {

	private String get = "https://osu.ppy.sh/api/get_user_best";
	private OusuAPI api;
	
	private EndPointScore[] score;
	
	private String user;
	private int limit;
	private Gamemode mode;
	
	public TopScoreBuilder(String user, int limit) {
		this.user = user;
		this.limit = limit;
		this.mode = null;
	}
	
	public TopScoreBuilder(String user, Gamemode mode ,int limit) {
		this.user = user;
		this.limit = 1;
		this.mode = mode;
	}
	
	private void connectionRequest() {
		HttpRequest bc;
		if (mode == null) {
			bc = HttpRequest.get(get, true, "k", api.getToken(), "u", user, "limit", limit+"");	
		} else {
			bc = HttpRequest.get(get, true, "k", api.getToken(), "u", user, "limit", limit+"", "m", mode.getId()+"");
		}
		bc.accept("application/json").contentType();
		
		Gson g = new Gson();
		EndPointScore[] us = g.fromJson(bc.body(), EndPointScore[].class);
		score = us;
	}
	
	public void setAPI(OusuAPI api) {
		this.api = api;
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
			public String getUsername() {
				return sc.getUsername();
			}
			
			@Override
			public User getUser() {
				return new UserBuilder(sc.getUser_id()+"").build();
			}
			
			@Override
			public int getScorePP() {
				return Integer.valueOf(sc.getPp().replace(".", ""));
			}
			
			@Override
			public int getScoreID() {
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
		};
	}
	
	public List<Score> buildList() {
		List<Score> l = new ArrayList<Score>();
		for (EndPointScore sc : score) {
			l.add(new Score() {
				
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
				public String getUsername() {
					return sc.getUsername();
				}
				
				@Override
				public User getUser() {
					return new UserBuilder(sc.getUser_id()+"").build();
				}
				
				@Override
				public int getScorePP() {
					return Integer.valueOf(sc.getPp().replace(".", ""));
				}
				
				@Override
				public int getScoreID() {
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
			});
		}
		return l;
	}

}