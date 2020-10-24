package me.skiincraft.api.ousu.entity.objects;

import java.util.Arrays;

/**<h1>Team</h1>
 * <p>Team Type are a few different game modes for multiplayer</p>
 * <p>There are 4 types of scores available
 * <br>
 * HeadToHead(0),
 * Tag_Coop(1),
 * Team_Versus(2),
 * Tag_Team_Versus(3),
 * </br>
 * </p>
 */
public enum Team {

	HeadToHead(0), Tag_Coop(1), Team_Versus(2), Tag_Team_Versus(3);
	
	private final int id;
	
	Team(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Team getTeamById(int id) {
		return Arrays.stream(values()).filter(t -> t.getId() == id).findFirst().orElse(null);
	}
	
}
