package me.skiincraft.api.ousu.users;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.json.EndPointUser;
import me.skiincraft.api.ousu.modifiers.Gamemode;

public class UserBuilder {

	private String get = "https://osu.ppy.sh/api/get_user";
	private OusuAPI api;
	
	private EndPointUser user;
	
	private String nameorid;
	private Gamemode mode; 
	
	public UserBuilder(String nameorid) {
		this.nameorid = nameorid;
		this.mode = null;
	}
	
	public UserBuilder(String nameorid, Gamemode mode) {
		this.nameorid = nameorid;
		this.mode = mode;
	}
	
	public void setAPI(OusuAPI api) {
		this.api = api;
	}
	
	private void connectionRequest() {
		HttpRequest bc;
		if (mode == null) {
			bc = HttpRequest.get(get, true, "k", api.getToken(), "u", nameorid);	
		} else {
			bc = HttpRequest.get(get, true, "k", api.getToken(), "u", nameorid, "m", mode.getId()+"");
		}
		bc.accept("application/json").contentType();
		
		Gson g = new Gson();
		EndPointUser[] us = g.fromJson(bc.body(), EndPointUser[].class);
		user = us[0];
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
			public Object[] getEvents() {
				return user.getEvents();
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
		};
		return us;
	}

}