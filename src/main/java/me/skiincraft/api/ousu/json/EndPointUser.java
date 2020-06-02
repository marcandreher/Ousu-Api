package me.skiincraft.api.ousu.json;

import java.util.List;
import java.util.Map;

public class EndPointUser {
	
	private int user_id;
	private String username;
	private String join_date;
	private float level;
	
	private int count300;
	private int count100;
	private int count50;
	
	private int playcount;
	
	private long ranked_score;
	private long total_score;
	
	private int pp_rank;
	private float pp_raw;
	
	private float accuracy;
	
    private int count_rank_ss;
    private int count_rank_ssh;
    private int count_rank_s;
    private int count_rank_sh;
    private int count_rank_a;
    
    private String country;
    private long total_seconds_played;
    private int pp_country_rank;
    private List<Map<String, Object>> events;

	
	public EndPointUser() {
		super();
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getJoin_date() {
		return join_date;
	}


	public void setJoin_date(String join_date) {
		this.join_date = join_date;
	}


	public float getLevel() {
		return level;
	}


	public void setLevel(float level) {
		this.level = level;
	}


	public int getCount300() {
		return count300;
	}


	public void setCount300(int count300) {
		this.count300 = count300;
	}


	public int getCount100() {
		return count100;
	}


	public void setCount100(int count100) {
		this.count100 = count100;
	}


	public int getCount50() {
		return count50;
	}


	public void setCount50(int count50) {
		this.count50 = count50;
	}


	public int getPlaycount() {
		return playcount;
	}


	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}


	public long getRanked_score() {
		return ranked_score;
	}


	public void setRanked_score(long ranked_score) {
		this.ranked_score = ranked_score;
	}


	public long getTotal_score() {
		return total_score;
	}


	public void setTotal_score(long total_score) {
		this.total_score = total_score;
	}


	public int getPp_rank() {
		return pp_rank;
	}


	public void setPp_rank(int pp_rank) {
		this.pp_rank = pp_rank;
	}


	public float getPp_raw() {
		return pp_raw;
	}


	public void setPp_raw(float pp_raw) {
		this.pp_raw = pp_raw;
	}


	public float getAccuracy() {
		return accuracy;
	}


	public void setAccuracy(float accuracy) {
		this.accuracy = accuracy;
	}


	public int getCount_rank_ss() {
		return count_rank_ss;
	}


	public void setCount_rank_ss(int count_rank_ss) {
		this.count_rank_ss = count_rank_ss;
	}


	public int getCount_rank_ssh() {
		return count_rank_ssh;
	}


	public void setCount_rank_ssh(int count_rank_ssh) {
		this.count_rank_ssh = count_rank_ssh;
	}


	public int getCount_rank_s() {
		return count_rank_s;
	}


	public void setCount_rank_s(int count_rank_s) {
		this.count_rank_s = count_rank_s;
	}


	public int getCount_rank_sh() {
		return count_rank_sh;
	}


	public void setCount_rank_sh(int count_rank_sh) {
		this.count_rank_sh = count_rank_sh;
	}


	public int getCount_rank_a() {
		return count_rank_a;
	}


	public void setCount_rank_a(int count_rank_a) {
		this.count_rank_a = count_rank_a;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public long getTotal_seconds_played() {
		return total_seconds_played;
	}


	public void setTotal_seconds_played(long total_seconds_played) {
		this.total_seconds_played = total_seconds_played;
	}


	public int getPp_country_rank() {
		return pp_country_rank;
	}


	public void setPp_country_rank(int pp_country_rank) {
		this.pp_country_rank = pp_country_rank;
	}


	public List<Map<String,Object>> getEvents() {
		return events;
	}


	public void setEvents(List<Map<String,Object>> events) {
		this.events = events;
	}
	
	
}
