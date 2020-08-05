package me.skiincraft.api.ousu.entity.replay;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;

public interface Replay {

	Request<Beatmap> getBeatmap();
	long getBeatmapId();
	
	String getBase64String();
	String getEncodeType();
	byte[] getDecode();
	
	
}
