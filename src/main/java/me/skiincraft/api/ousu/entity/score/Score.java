package me.skiincraft.api.ousu.entity.score;

import java.util.Date;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.user.User;

public interface Score {
	
	long getBeatmapId();
	long getScoreId();
	
	Request<Beatmap> getBeatmap();
	
	long getScore();
	Request<User> getUser(Gamemode gamemode);
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
	float getScorePP();
	
	

}
