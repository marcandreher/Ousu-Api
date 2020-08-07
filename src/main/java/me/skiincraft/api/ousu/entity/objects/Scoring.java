package me.skiincraft.api.ousu.entity.objects;

import java.util.Arrays;

/**<h1>Scoring</h1>
 * <p>Scoring is the type of score of a game in a multiplayer room on Osu!</p>
 * <p>There are 4 types of scores available
 * <br>
 * Score(0),
 * Accuracy(1),
 * Combo(2),
 * Combo v2(3),
 * </br>
 */
public enum Scoring {

	Score(0), Accuracy(1), Combo(2), Score_v2(3);
	
	private int id;
	
	Scoring(int id){
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static Scoring getTeamById(int id) {
		return Arrays.stream(values()).filter(t -> t.getId() == id).findFirst().orElse(null);
	}
	
}
