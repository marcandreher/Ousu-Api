package me.skiincraft.api.ousu.modifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Mods {

	NO_FAIL(1, "No Fail"),
	EASY(2, "Easy"),
	TOUCH_DEVICE(4, "Touch Device"),
	HIDDEN(8, "Hidden"),
	HARD_ROCK(16, "Hard Rock"),
	SUDDEN_DEATH(32, "Sudden Death"),
	DOUBLE_TIME(64, "Double Time"),
	RELAX(128, "Relax"),
	HALF_TIME(256, "Half Time"),
	NIGHTCORE(512, "Nightcore"),
	FLASHLIGHT(1024, "Flashlight"),
	AUTOPLAY(2048, "Autoplay"),
	SPUNOUT(4096, "Spunout"),
	AUTOPILOT(8192, "Autopilot"),
	PERFECT(16384, "Perfect"),
	KEY_4(32768, "4K"),
	KEY_5(65536, "5K"),
	KEY_6(131072, "6K"),
	KEY_7(262144, "7K"),
	KEY_8(524288, "8K"),
	FADE_IN(1048576, "Fade-In"),
	RANDOM(2097152, "Random"),
	CINEMA(4194304, "Cinema"),
	TARGET(8388608, "Target Practice"),
	KEY_9(16777216, "9K"),
	KEY_COOP(33554432, "Co-Op"),
	KEY_1(67108864, "1K"),
	KEY_3(134217728, "3K"),
	KEY_2(268435456, "2K"),
	SCORE_V2(536870912, "Score v2"),
	LAST_MOD(1073741824, "Last Mod");
	
	private long id;
	private String displayName;
	
	Mods(int id, String displayName) {
		this.id = id;
		this.displayName = displayName; 
	}
	
	// Author - \'/
	// oopsjpg
	// https://github.com/oopsjpeg/osu4j/blob/master/src/main/java/com/oopsjpeg/osu4j/GameMod.java
	
	public static Mods[] get(long bit) {
		List<Mods> values = Arrays.stream(values())
				.sorted(Comparator.comparingLong(Mods::getId))
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
		List<Mods> mods = new ArrayList<>();

		while (bit > 0) for (Mods mod : values)
			if (mod.id <= bit) {
				mods.add(mod);
				bit -= mod.id;
			}

		return mods.toArray(new Mods[mods.size()]);
	}
	//
	
	public long getId() {
		return this.id;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
}
