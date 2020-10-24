package me.skiincraft.api.ousu.entity.score;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.user.User;
import me.skiincraft.api.ousu.impl.RecentScoreImpl;
import me.skiincraft.api.ousu.json.SimpleJson;
import me.skiincraft.api.ousu.util.MakeList;

/**<h1>RecentScore</h1>
 * <p>Interface to display the requested Recent Score from a User 
 * after a {@link Request}</p>
 * 
 * <p>This class is similar to {@link Score}.</p>
 * @see RecentScoreImpl A Implementation Class
 */
public interface RecentScore extends Scoreable {
	
	long getUserId();
	
	
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
	Request<User> getUser(Gamemode gamemode);
	
	/**
	 * Is the date the score was earned
	 */
	OffsetDateTime getDate();
	String getRank();
	
	static RecentScore getSample() {
		return new RecentScoreImpl(new SimpleJson().getJsonAsResource("recentScoreJson.json").get(0).getAsJsonObject(), null);
	}
	
	static List<RecentScore> getSampleCollection() {
		List<RecentScore> score = new ArrayList<>();
		JsonArray array = new SimpleJson().getJsonAsResource("recentScoreJson.json");
		for (JsonElement ele : array) {
			JsonObject object = ele.getAsJsonObject();
			score.add(new RecentScoreImpl(object, null));
		}
		return score;
	}
}
