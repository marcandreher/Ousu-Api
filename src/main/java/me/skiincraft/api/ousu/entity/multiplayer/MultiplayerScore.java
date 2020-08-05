package me.skiincraft.api.ousu.entity.multiplayer;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.user.User;

public interface MultiplayerScore {

	int getSlot();
	int getTeam();
	Request<User> getUser(Gamemode gamemode);
	long getUserId();
	long getScore();
	int getMaxCombo();
	int get50();
	int get100();
	int get300();
	int getMiss();
	int getGekis();
	int getKatus();
	boolean isPerfect();
	boolean isPassed();
	Mods[] getEnabledMods();
	Game getGame();
}
