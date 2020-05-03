package me.skiincraft.api.ousu.json;

public class EndPointScore {

	private int beatmap_id;
    private int score_id;
    private int score;
    private String username;
    private int maxcombo;
    private int count50;
    private int count100;
    private int count300;
    private int countmiss;
    private int countkatu;
    private int countgeki;
    private int perfect;
    private long enabled_mods;
    private int user_id;
    private String date;
    private String rank;
    private String pp;
    private int replay_available;

	
	public EndPointScore() {
		super();
	}


	public int getBeatmap_id() {
		return beatmap_id;
	}


	public void setBeatmap_id(int beatmap_id) {
		this.beatmap_id = beatmap_id;
	}


	public int getScore_id() {
		return score_id;
	}


	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public int getMaxcombo() {
		return maxcombo;
	}


	public void setMaxcombo(int maxcombo) {
		this.maxcombo = maxcombo;
	}


	public int getCount50() {
		return count50;
	}


	public void setCount50(int count50) {
		this.count50 = count50;
	}


	public int getCount100() {
		return count100;
	}


	public void setCount100(int count100) {
		this.count100 = count100;
	}


	public int getCount300() {
		return count300;
	}


	public void setCount300(int count300) {
		this.count300 = count300;
	}


	public int getCountmiss() {
		return countmiss;
	}


	public void setCountmiss(int countmiss) {
		this.countmiss = countmiss;
	}


	public int getCountkatu() {
		return countkatu;
	}


	public void setCountkatu(int countkatu) {
		this.countkatu = countkatu;
	}


	public int getCountgeki() {
		return countgeki;
	}


	public void setCountgeki(int countgeki) {
		this.countgeki = countgeki;
	}


	public int getPerfect() {
		return perfect;
	}


	public void setPerfect(int perfect) {
		this.perfect = perfect;
	}


	public long getEnabled_mods() {
		return enabled_mods;
	}


	public void setEnabled_mods(long enabled_mods) {
		this.enabled_mods = enabled_mods;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public String getPp() {
		return pp;
	}


	public void setPp(String pp) {
		this.pp = pp;
	}


	public int getReplay_available() {
		return replay_available;
	}


	public void setReplay_available(int replay_available) {
		this.replay_available = replay_available;
	}
	
	

}
