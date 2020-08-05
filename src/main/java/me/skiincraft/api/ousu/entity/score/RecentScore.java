package me.skiincraft.api.ousu.entity.score;

import java.util.Date;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.user.User;

public interface RecentScore {

	long getBeatmapId();
	long getScore();
	int getMaxCombo();
	int get50();
	int get100();
	int get300();
	int getMiss();
	int getKatus();
	int getGekis();
	boolean isPerfect();
	Mods[] getEnabledMods();
	long getUserId();
	Request<User> getUser(Gamemode gamemode);
	Date getDate();
	String getRank();
	
}
