package me.skiincraft.api.ousu.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import me.skiincraft.api.ousu.entity.score.BeatmapScore;
import me.skiincraft.api.ousu.entity.score.Score;

public class BeatmapScoreImpl implements BeatmapScore {

	private Score[] scores;
	
	public BeatmapScoreImpl(List<Score> scores) {
		this.scores = scores.toArray(new Score[scores.size()]);
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
