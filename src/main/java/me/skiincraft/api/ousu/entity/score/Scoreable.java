package me.skiincraft.api.ousu.entity.score;

import me.skiincraft.api.ousu.entity.objects.Mods;

/**<h1>Scoreable</h1>
 * <p>Implementing class for other Scoreables</p>
 */
public interface Scoreable {

	
	/**<p>This {@code Long} is the Beatmap Id.
	 * <br> With this code it is possible to search
	 * Osu beatmap.</br>
	 */
	long getBeatmapId();
	
	
	/**<p>This {@code Long} is the Score Id.
	 * <br> With this code it is possible to search
	 * Osu Scores.</br><p>
	 * <p>If the ScoreId is unavailable it will return "0"</p>
	 */
	default long getScoreId() {
		return 0;
	}
	
	/**<p>This {@code Long} is the Total Score.</p>
	 */
	long getScore();
	
	/**<p>Is the maximum number of combo of this Score.</p>
	 */
	int getMaxCombo();
	
	/**<p>Is the count of the Scores that add up to 50</p>
	 */
	int get50();
	
	/**<p>Is the count of the Scores that add up to 100</p>
	 */
	int get100();
	
	/**<p>Is the count of the Scores that add up to 300</p>
	 */
	int get300();
	
	/**<p>Is the count of errors occurred in the gameplay</p>
	 */
	int getMiss();
	
	/**<p>Is the count of the scores that add up to Katus</p>
	 */
	int getKatus();
	
	/**<p>Is the count of the scores that add up to Gekis</p>
	 */
	int getGekis();
	
	/**<p>If the score is perfect it will return "true"</p>
	 */
	boolean isPerfect();
	
	/**<p>If the Replay is Available it will return "true"</p>
	 */
	default boolean isReplayAvailable() {
		return false;
	}

	/**<p>Show the enabled mods on this Replay</p>
	 */
	Mods[] getEnabledMods();
	
}
