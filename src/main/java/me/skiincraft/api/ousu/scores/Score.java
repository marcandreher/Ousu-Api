package me.skiincraft.api.ousu.scores;

import java.util.Date;
import java.util.List;

import me.skiincraft.api.ousu.beatmaps.Beatmap;
import me.skiincraft.api.ousu.modifiers.Mods;
import me.skiincraft.api.ousu.users.User;

public interface Score {
	
	int getBeatmapID();
	long getScoreID();
	
	Beatmap getBeatmap();
	List<Beatmap> getBeatmapSet();
	
	int getScore();
	User getUser();
	
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
	float getScorePP();
	
	

}
