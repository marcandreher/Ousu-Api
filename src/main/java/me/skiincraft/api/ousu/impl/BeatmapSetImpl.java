package me.skiincraft.api.ousu.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.beatmap.BeatmapSet;

public class BeatmapSetImpl implements BeatmapSet {

	private Beatmap[] beatmaps;
	
	public BeatmapSetImpl(List<Beatmap> beatmaps) {
		this.beatmaps = beatmaps.toArray(new Beatmap[beatmaps.size()]);
	}
	
	public BeatmapSetImpl(JsonArray beatmapsArray, OusuAPI api) {
		this.beatmaps = new Beatmap[beatmapsArray.size()];
		int i = 0;
		for (JsonElement e :beatmapsArray) {
			JsonObject obj = e.getAsJsonObject();
			beatmaps[i] = new BeatmapImpl(obj, this, api);
			i++;
		}
	}
	
	public Iterator<Beatmap> iterator() {
		return Arrays.asList(beatmaps).iterator();
	}

	public List<Beatmap> getAsList() {
		return Arrays.asList(beatmaps);
	}

	public Stream<Beatmap> getAsStream() {
		return Arrays.asList(beatmaps).stream();
	}

	public Beatmap getBeatmapById(long beatmapid) {
		return Arrays.asList(beatmaps).stream().filter(b -> b.getBeatmapId() == beatmapid).findFirst().orElse(null);
	}

	public int size() {
		return beatmaps.length;
	}

}
