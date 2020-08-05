package me.skiincraft.api.ousu.entity.multiplayer;

import java.util.Date;
import java.util.List;

public interface Match {
	
	long getMatchId();
	String getName();
	Date getStartTime();
	Date getEndTime();
	
	List<Game> getGames();


}
