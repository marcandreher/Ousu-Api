package me.skiincraft.api.ousu.utils;

import java.util.Comparator;

import me.skiincraft.api.ousu.beatmaps.Beatmap;

public class SortBeatmap implements Comparator<Beatmap> {

	@Override
	public int compare(Beatmap o1, Beatmap o2) {
		return Float.compare(o1.getStars(), o2.getStars());
	}
}
