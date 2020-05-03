package me.skiincraft.api.ousu.scores;

import java.util.Date;

import me.skiincraft.api.ousu.modifiers.Mods;
import me.skiincraft.api.ousu.users.User;

public interface Score {
	
	int getBeatmapID();
	int getScoreID();
	int getScore();
	User getUser();
	String getUsername();
	
	int getMaxCombo();
	
	int get300();
	int get100();
	int get50();
	int getMiss();
	int getKatus();
	int getGekis();
	
	boolean isPerfect();
	boolean isReplayAvailable();
	
	Mods[] getEnabledMods();
	Date getScoreDate();
	String getRank();
	int getScorePP();
	
	

}
