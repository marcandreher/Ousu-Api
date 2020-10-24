package me.skiincraft.api.ousu.entity.replay;

import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.impl.ReplayImpl;
import me.skiincraft.api.ousu.json.SimpleJson;

/**<h1>Replay</h1>
 * <p>Interface to show the requested Replays
 * after a {@link Request}</p>
 * 
 * <p>The replays included in this class cannot be downloaded, as they need an algorithm to work.</p>
 * 
 * @see ReplayImpl A Implementation Class
 */
public interface Replay {

	/**<h1>Request<{@linkplain Beatmap}></h1>
	 * <p>A beatmap request for this Replay.
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
	
	/**<p>This {@code Long} is the Beatmap Id.
	 * <br> With this code it is possible to search
	 * osu beatmap.</br>
	 */
	long getBeatmapId();
	
	/**
	 * <p>This {@code String} containing the key "content", which is a base64-encoded replay</p>
	 * <h3>From original documentation:</h3>
	 * <p>Note that the binary data you get when you decode above base64-string, is not the contents of an .osr-file.
	 * <br>It is the LZMA stream referred to by the osu-wiki here:</br></p>
	 * <p>The remaining data contains information about mouse movement and key presses in an wikipedia:<br>LZMA stream (https://osu.ppy.sh/help/wiki/osu!_File_Formats/Osr_(file_format)})<br></p>
	 */
	String getBase64String();
	
	String getEncodeType();
	
	/**<p>Contains the {@code getBase64String} decoded.</p>
	 */
	byte[] getDecode();
	
	static Replay getSample() {
		return new ReplayImpl(new SimpleJson().getJsonObjectAsResource("replayJson.json"), 0, null);
	}
}
