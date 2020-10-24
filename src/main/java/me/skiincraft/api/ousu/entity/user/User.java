package me.skiincraft.api.ousu.entity.user;

import java.time.OffsetDateTime;
import java.util.List;

import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.PlayedTime;
import me.skiincraft.api.ousu.entity.objects.ProfileEvents;
import me.skiincraft.api.ousu.entity.score.RecentScore;
import me.skiincraft.api.ousu.entity.score.Score;
import me.skiincraft.api.ousu.impl.UserImpl;
import me.skiincraft.api.ousu.json.SimpleJson;

public interface User {
	
	/**
	 * <p>Is the user id of the requested user</p>
	 */
	long getUserId();
	
	/**
	 * <p>Is the nickname of the requested user</p>
	 */
	String getUsername();
	
	/**
	 * <p>Is the join date of the requested user</p>
	 */
	OffsetDateTime getJoinDate();
	
	/**
	 * <p>Is the game mode of the requested user</p>
	 */
	Gamemode getGamemode();
	/**
	 * <p>Is the account level of the requested user</p>
	 */
	float getLevel();
	
	/**
	 * <p>Is the country of the requested user</p>
	 */
	String getCountryCode();
	
	/**
	 * <p>Is the accuracy of the requested user</p>
	 */
	float getAccuracy();
	
	int get300();
	int get100();
	int get50();
	
	int getSS();
	int getSSh();
	int getS();
	int getSh();
	int getA();
	
	/**
	 * <p>Is the game mode of the requested user</p>
	 */
	int getPlayCount();
	
	/**
	 * <p>Are how many times the requested user played</p>
	 */
	PlayedTime getPlayedHours();
	
	/**
	 * <p>Returns the URL of this user's avatar
	 * <br>(http://s.ppy.sh/a/userid.png)</br></p>
	 * 
	*/
	default String getUserAvatar() {
		return "http://s.ppy.sh/a/" + getUserId() + ".png";
	}
	/**
	 * <p>Returns the flag of this user's country
	 * <br>(https://osu.ppy.sh/images/flags/CountryCode.png)</br></p>
	 * 
	*/
	default String getUserFlag() {
		return "https://osu.ppy.sh/images/flags/" + getCountryCode() + ".png";
	}
	
	/**
	 * <p>Returns the URL of this user's profile
	 * <br>(http://s.ppy.sh/a/userid.png)</br></p>
	 * 
	*/
	default String getURL() {
		return "https://osu.ppy.sh/users/" + getUserId();
	}
	
	/**<p>Contains events for this user</p>
	*/
	List<ProfileEvents> getProfileEvents();
	
	/**<p>Is Ranked Total Score from this user</p>
	*/
	long getRankedScore();
	
	/**<p>Is Total Score from this user</p>
	*/
	long getTotalScore();
	
	/**<p>Is the global ranking of this user (The ranking is measured from the PP)</p>
	*/
	int getRanking();
	
	/**<p>Is the country ranking of this user (The ranking is measured from the PP)</p>
	*/
	int getCountryRanking();
	
	/**<p> Is the Performance Point of this user</p>
	 * <p>For inactive players this will be 0 to purge them from leaderboards</p>
	 */
	float getPP(); 
	
	default Request<List<Score>> getTopScore(int limit){
		return getTopScore(getGamemode(), limit);
	}
	Request<List<Score>> getTopScore(Gamemode gamemode, int limit);

	default Request<List<RecentScore>> getRecentScore(int limit){
		return getRecentScore(getGamemode(), limit);
	}
	Request<List<RecentScore>> getRecentScore(Gamemode gamemode, int limit);
	
	/**<h1>Get a Sample</h1>
	 * <p>This example will not make any Request</p>
	 */
	static User getSample() {
		return new UserImpl(new SimpleJson().getJsonAsResource("userJson.json").get(0).getAsJsonObject(), Gamemode.Standard, null);
	}
}
