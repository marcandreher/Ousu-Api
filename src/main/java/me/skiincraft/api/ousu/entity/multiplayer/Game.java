package me.skiincraft.api.ousu.entity.multiplayer;

import java.util.Date;
import java.util.List;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.objects.Scoring;
import me.skiincraft.api.ousu.entity.objects.Team;

public interface Game {
	
	Match getMatch();
	long getGameId();
	Date getGameStartTime();
	Date getGameEndTime();
	long getBeatmapId();
	Request<Beatmap> getBeatmap();
	
	Gamemode getGamemode();
	Scoring getScoreType();
	Team getTeamType();
	Mods[] getGlobalMods();
	List<MultiplayerScore> getScores();	
	

}
