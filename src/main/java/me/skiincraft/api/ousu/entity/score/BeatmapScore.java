package me.skiincraft.api.ousu.entity.score;

import java.util.List;
import java.util.stream.Stream;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.impl.BeatmapScoreImpl;
import me.skiincraft.api.ousu.json.SimpleJson;

/**<h1>BeatmapScore</h1>
 * <p>Interface to display the requested scores from a Beatmap 
 * after a {@link Request}</p>
 * 
 * <p>{@link BeatmapScore} contains a list of scores from a beatmap, it is not another type of score.</p>
 * 
 * @see Iterable This class is Iterable
 * @see BeatmapScoreImpl A Implementation Class
 */
public interface BeatmapScore extends Iterable<Score> {
	
	/**<p>Obtain all score contained in this instance through a List</p>
	 */
	List<Score> getAsList();
	
	/**<p>Obtain all score contained in this instance through a Stream</p>
	 */
	Stream<Score> getAsStream();
	
	/**<p>Get a score by score id</p>	 
	*/
	Score getScoreById(long scoreid);
	
	default Score get(int i) {
		return getAsList().get(i);
	}
	
	/**<p>Get a BeatmapScore size()</p>
	 */
	int size();
	
	/**<h1>Get a Sample</h1>
	 * <p>This example will not make any Request</p>
	 */
	public static BeatmapScore getSample() {
		return new BeatmapScoreImpl(new SimpleJson().getJsonAsResource("beatmapScoreJson.json"), 1065901, null);
		// The beatmap :3 https://osu.ppy.sh/beatmapsets/488396#osu - Emilia-tan
	}
}
