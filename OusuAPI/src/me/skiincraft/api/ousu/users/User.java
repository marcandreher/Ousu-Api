package me.skiincraft.api.ousu.users;

import java.util.List;

import me.skiincraft.api.ousu.modifiers.PlayedHours;
import me.skiincraft.api.ousu.scores.Score;

public interface User {
	
	int getUserID();
	String getUserName();
	String getJoinDate();
	int getLevel();
	String getCountryCode();
	
	
	float getAccuracy();
	
	int get300();
	int get100();
	int get50();
	
	int getSS();
	int getSSh();
	int getS();
	int getSh();
	int getA();
	
	int getPlayCount();
	PlayedHours getPlayedHours();
	String getUserAvatar();
	
	String getURL();
	
	long getRankedScore();
	long getTotalScore();
	
	int getRanking();
	int getNacionalRanking();
	
	int getPP();
	Object[] getEvents();
	
	List<Score> getTopScore(int limit);
	List<Score> getRecentScore(int limit);
}
