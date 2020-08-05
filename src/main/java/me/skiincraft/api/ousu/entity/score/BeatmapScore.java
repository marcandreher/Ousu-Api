package me.skiincraft.api.ousu.entity.score;

import java.util.List;
import java.util.stream.Stream;

public interface BeatmapScore extends Iterable<Score> {
	
	List<Score> getAsList();
	Stream<Score> getAsStream();
	Score getScoreById(long beatmapid);
	
	default Score get(int i) {
		return getAsList().get(i);
	}
	
	int size();
}
