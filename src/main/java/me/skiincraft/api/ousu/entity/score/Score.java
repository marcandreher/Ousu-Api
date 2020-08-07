package me.skiincraft.api.ousu.entity.score;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.user.User;
import me.skiincraft.api.ousu.impl.ScoreImpl;
import me.skiincraft.api.ousu.json.SimpleJson;
import me.skiincraft.api.ousu.util.MakeList;

public interface Score extends Scoreable {
	
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
	
	/**<h1>Request<{@linkplain User}></h1>
	 * <p>A user request for this Score.
	 * 
	 * <br>If this user has been requested before,
	 *   you may not need to make another request</br></p>
	 *  
	 *  <p>Check if it is available using:
	 *  <br><code>getCreator().isRequested()</br></code></p>
	 *  
	 * @see Request
	 * @see User 
	 */
	Request<User> getUser(Gamemode gamemode);
	
	/**<h1>Request<{@linkplain User}></h1>
	 * <p>A user request for this Score.
	 * 
	 * <br>If this user has been requested before,
	 *   you may not need to make another request</br></p>
	 *  
	 *  <p>Check if it is available using:
	 *  <br><code>getCreator().isRequested()</br></code></p>
	 *  
	 * @see Request 
	 */
	default Request<User> getUser(){
		return getUser(Gamemode.Standard);
	}
	
	/**Is the owner of this score 
	 */
	String getUsername();
	
	/**Is the date the score was earned 
	 */
	Date getScoreDate();
	String getRank();
	float getScorePP();
	
	public static Score getSample() {
		return new ScoreImpl(new SimpleJson().getJsonAsResource("beatmapScoreJson.json").get(0).getAsJsonObject(), 1065901, null);
	}
	
	public static List<Score> getSampleTop() {
		return new MakeList<Score>() {

			public List<Score> make() {
				List<Score> scores = new ArrayList<Score>();
				JsonArray array = new SimpleJson().getJsonAsResource("bestScoreJson.json");
				for (JsonElement ele : array) {
					JsonObject object = ele.getAsJsonObject();
					scores.add(new ScoreImpl(object, object.get("beatmap_id").getAsLong(), null));	
				}
				
				return scores;
			}
		}.make();
	}

}
