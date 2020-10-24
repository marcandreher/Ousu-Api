package me.skiincraft.api.ousu.entity.multiplayer;

import java.time.OffsetDateTime;
import java.util.List;

import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Mods;
import me.skiincraft.api.ousu.entity.objects.Scoring;
import me.skiincraft.api.ousu.entity.objects.Team;

public interface Game {
	
	Match getMatch();
	long getGameId();
	OffsetDateTime getGameStartTime();
	OffsetDateTime getGameEndTime();
	
	/**<p>Get a beatmap by id</p>	 */
	long getBeatmapId();
	
	/**<h1>Request<{@linkplain Beatmap}></h1>
	 * <p>A beatmap request for this Game.
	 * 
	 * <br>If this beatmap is in a {@link Beatmap},
	 *  you may not need to make another request</br></p>
	 *  
	 *  <p>Check if it is available using:
	 *  <br><code>getBeatmap().wasRequested()</br></code></p>
	 *  @see Request
	 *  @see Beatmap
	 */
	Request<Beatmap> getBeatmap();
	
	Gamemode getGamemode();
	Scoring getScoreType();
	Team getTeamType();
	Mods[] getGlobalMods();
	
	List<MultiplayerScore> getScores();	
	

}
