package me.skiincraft.api.ousu.entity.multiplayer;

import java.util.Date;
import java.util.List;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.impl.MatchImpl;
import me.skiincraft.api.ousu.json.SimpleJson;

/**<h1>Match</h1>
 * <p>Interface to show the requested Match
 * after a {@link Request}</p>
 * 
 * @see MatchImpl A Implementation Class
 */
public interface Match {
	
	long getMatchId();
	String getName();
	Date getStartTime();
	Date getEndTime();
	
	List<Game> getGames();

	public static Match getSample() {
		return new MatchImpl(new SimpleJson().getJsonObjectAsResource("matchJson.json"), null);
	}
}
