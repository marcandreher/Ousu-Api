package me.skiincraft.api.ousu.users;

import java.util.List;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.exceptions.InvalidUserException;
import me.skiincraft.api.ousu.json.EndPointUser;
import me.skiincraft.api.ousu.modifiers.Gamemode;
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
	
	private void connectionRequest() throws InvalidUserException {
		HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "u", nameorid, "m", mode.getId()+"");
		
		if (nameorid == null || nameorid == "") {
			throw new InvalidUserException("Usuário solicitado esta nulo.");
		}
		
		bc.accept("application/json").contentType();
		
		Gson g = new Gson();
		EndPointUser[] us = g.fromJson(bc.body(), EndPointUser[].class);
		
		try {
			user = us[0];	
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new InvalidUserException("Este usuario solicitado não existe");
		}
	}
	
	public User build() throws InvalidUserException {
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

			@Override
			public List<Score> getTopScore(int limit) {
				return api.getTopUser(user.getUser_id()+"", mode, limit);
			}

			@Override
			public List<Score> getRecentScore(int limit) {
				return api.getRecentUser(user.getUser_id()+"", mode, limit);
			}
		};
		return us;
	}

}