package me.skiincraft.api.ousu.entity.beatmap;

import java.util.List;
import java.util.stream.Stream;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.impl.BeatmapSetImpl;
import me.skiincraft.api.ousu.json.SimpleJson;

/**<h1>BeatmapSet</h1>
 * <p>Interface to show the requested BeatmapSet
 * after a {@link Request}</p>
 * 
 * <p>{@link BeatmapSet} are a set of beatmaps are not other types of beatmap</p>
 * 
 * @see Iterable This class is Iterable
 * @see BeatmapSetImpl A Implementation Class
 */
public interface BeatmapSet extends Iterable<Beatmap> {
	
	/**<p>Obtain all beatmaps contained in this instance through a List</p>
	 */
	List<Beatmap> getAsList();
	
	/**<p>Obtain all beatmaps contained in this instance through a Stream</p>
	 */
	Stream<Beatmap> getAsStream();
	
	/**<p>Get a beatmap by id</p>	 */
	Beatmap getBeatmapById(long beatmapid);
	default Beatmap get(int i) {
		return getAsList().get(i);
	}
	
	/**<p>Get a BeatmapSet size()</p>
	 */
	int size();
	
	/**<h1>Get a Sample</h1>
	 * <p>This example was taken directly from the official Osu! </p>
	 * <p>This example will not make any Request</p>
	 */
	public static BeatmapSet getSample() {
		return new BeatmapSetImpl(new SimpleJson().getJsonAsResource("beatmapsetJson.json"), null);
	}
}
