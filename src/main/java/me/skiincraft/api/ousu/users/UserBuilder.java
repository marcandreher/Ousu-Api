package me.skiincraft.api.ousu.users;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.exceptions.InvalidUserException;
import me.skiincraft.api.ousu.json.EndPointUser;
import me.skiincraft.api.ousu.modifiers.Gamemode;
import me.skiincraft.api.ousu.modifiers.PlayedTime;
import me.skiincraft.api.ousu.modifiers.ProfileEvents;
import me.skiincraft.api.ousu.scores.Score;

public class UserBuilder {

	private String get = "https://osu.ppy.sh/api/get_user";
	private OusuAPI api;
	
	private EndPointUser user;
	
	private String nameorid;
	private Gamemode mode; 
	
	public UserBuilder(String nameorid) {
		this.nameorid = nameorid;
		this.mode = Gamemode.Standard;
	}
	
	public UserBuilder(String nameorid, Gamemode mode) {
		this.nameorid = nameorid;
		this.mode = mode;
	}
	
	public void setAPI(OusuAPI api) {
		this.api = api;
	}
	
	private void connectionRequest() {
		HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "u", nameorid, "m", mode.getId()+"");
		
		if (nameorid == null || nameorid == "") {
			throw new InvalidUserException("Usuário solicitado esta nulo.", null);
		}
		
		bc.accept("application/json").contentType();
		
		Gson g = new Gson();
		EndPointUser[] us = g.fromJson(bc.body(), EndPointUser[].class);
		
		try {
			user = us[0];	
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidUserException("Este usuario solicitado não existe", e);
		}
	}
	
	public User build() {
		connectionRequest();
		
		User us = new User() {
			
			@Override
			public String getUserName() {
				return user.getUsername();
			}
			
			@Override
			public int getUserID() {
				return user.getUser_id();
			}
			
			@Override
			public long getTotalScore() {
				return user.getTotal_score();
			}
			
			@Override
			public int getSh() {
				return user.getCount_rank_sh();
			}
			
			@Override
			public int getSSh() {
				return user.getCount_rank_ssh();
			}
			
			@Override
			public int getSS() {
				return user.getCount_rank_ss();
			}
			
			@Override
			public int getS() {
				return user.getCount_rank_s();
			}
			
			@Override
			public int getRanking() {
				return user.getPp_rank();
			}
			
			@Override
			public long getRankedScore() {
				return user.getRanked_score();
			}
			
			@Override
			public int getPlayCount() {
				return user.getPlaycount();
			}
			
			@Override
			public int getPP() {
				return (int) user.getPp_raw();
			}
			
			@Override
			public int getNacionalRanking() {
				return user.getPp_country_rank();
			}
			
			@Override
			public int getLevel() {
				return (int) user.getLevel();
			}
			
			@Override
			public String getJoinDate() {
				return user.getJoin_date();
			}
			
			@Override
			public List<ProfileEvents> getProfileEvents() {
				List<ProfileEvents> evnlist = new ArrayList<ProfileEvents>();
				if (user.getEvents() == null) {
					return evnlist;
				}
				
				if (user.getEvents().size() == 0) {
					return evnlist;
				}
				for (Map<String, Object> evn : user.getEvents()) {
					//Se for uma medalha ao inves de beatmap
					if (evn.get("beatmap_id") == null) {
						continue;
					}
					evnlist.add(new ProfileEvents(
							String.valueOf(evn.get("display_html")), 
							Integer.valueOf((String) evn.get("beatmap_id")), 
							Integer.valueOf((String) evn.get("beatmapset_id")),
							String.valueOf(evn.get("date")),
							Integer.valueOf((String) evn.get("epicfactor"))));
				}
				
				return evnlist;
			}
			
			@Override
			public int getA() {
				return user.getCount_rank_a();
			}
			
			@Override
			public int get50() {
				return user.getCount50();
			}
			
			@Override
			public int get300() {
				return user.getCount300();
			}
			
			@Override
			public int get100() {
				return user.getCount100();
			}

			@Override
			public String getUserAvatar() {
				return "http://s.ppy.sh/a/" + user.getUser_id() + ".png";
			}

			@Override
			public List<Score> getTopScore(int limit) {
				return api.getTopUser(user.getUser_id()+"", mode, limit);
			}

			@Override
			public List<Score> getRecentScore(int limit) {
				return api.getRecentUser(user.getUser_id()+"", mode, limit);
			}

			@Override
			public float getAccuracy() {
				return user.getAccuracy();
			}

			@Override
			public PlayedTime getPlayedHours() {
				BigDecimal decimal = new BigDecimal(user.getTotal_seconds_played());
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
				return user.getCountry();
			}

			@Override
			public String getURL() {
				return "https://osu.ppy.sh/users/" + user.getUser_id();
			}

			@Override
			public String getUserFlag() {
				return "https://osu.ppy.sh/images/flags/" + user.getCountry() + ".png";
			}

			@Override
			public Gamemode getGamemode() {
				return mode;
			}
		};
		return us;
	}

}