package me.skiincraft.api.ousu.entity.beatmap;

import java.util.List;
import java.util.stream.Stream;

public interface BeatmapSet extends Iterable<Beatmap> {
	
	List<Beatmap> getAsList();
	Stream<Beatmap> getAsStream();
	Beatmap getBeatmapById(long beatmapid);
	default Beatmap get(int i) {
		return getAsList().get(i);
	}
	int size();
}
