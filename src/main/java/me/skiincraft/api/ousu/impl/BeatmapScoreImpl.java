package me.skiincraft.api.ousu.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.entity.score.BeatmapScore;
import me.skiincraft.api.ousu.entity.score.Score;

public class BeatmapScoreImpl implements BeatmapScore {

	private final Score[] scores;
	
	public BeatmapScoreImpl(List<Score> scores) {
		this.scores = scores.toArray(new Score[0]);
	}
	
	public BeatmapScoreImpl(JsonArray jsonScoreArray, long beatmapid, OusuAPI api) {
		this.scores = new Score[jsonScoreArray.size()];
		int i = 0;
		for (JsonElement ele : jsonScoreArray) {
			JsonObject object = ele.getAsJsonObject();
			this.scores[i] = new ScoreImpl(object, beatmapid, api);
			i++;
		}
	}
	
	public Iterator<Score> iterator() {
		return Arrays.asList(scores).iterator();
	}

	public List<Score> getAsList() {
		return Arrays.asList(scores);
	}

	public Stream<Score> getAsStream() {
		return Arrays.stream(scores);
	}

	public Score getScoreById(long scoreid) {
		return getAsStream().filter(bs -> bs.getScoreId() == scoreid).findFirst().orElse(null);
	}

	public int size() {
		return scores.length;
	}

}
