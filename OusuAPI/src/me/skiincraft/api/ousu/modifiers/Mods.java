package me.skiincraft.api.ousu.modifiers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Mods {

	NO_FAIL(1, "No Fail", "NF"),
	EASY(2, "Easy", "EZ"),
	TOUCH_DEVICE(4, "Touch Device", "TD"),
	HIDDEN(8, "Hidden", "HD"),
	HARD_ROCK(16, "Hard Rock", "HR"),
	SUDDEN_DEATH(32, "Sudden Death", "SD"),
	DOUBLE_TIME(64, "Double Time", "DT"),
	RELAX(128, "Relax", "RX"),
	HALF_TIME(256, "Half Time", "HF"),
	NIGHTCORE(512, "Nightcore","NC"),
	FLASHLIGHT(1024, "Flashlight", "FL"),
	AUTOPLAY(2048, "Autoplay", "AU"),
	SPUNOUT(4096, "Spunout", "SO"),
	AUTOPILOT(8192, "Autopilot", "AU"),
	PERFECT(16384, "Perfect","PF"),
	KEY_1(67108864, "1K","1K"),
	KEY_3(134217728, "3K", "3K"),
	KEY_2(268435456, "2K", "2K"),
	KEY_4(32768, "4K", "4K"),
	KEY_5(65536, "5K", "5K"),
	KEY_6(131072, "6K", "6K"),
	KEY_7(262144, "7K", "7K"),
	KEY_8(524288, "8K", "8K"),
	KEY_9(16777216, "9K", "9K"),
	FADE_IN(1048576, "Fade-In", "Fade_In"),
	RANDOM(2097152, "Random", "Random"),
	CINEMA(4194304, "Cinema", "Cine"),
	TARGET(8388608, "Target Practice", "TC"),
	KEY_COOP(33554432, "Co-Op", "Co-op"),
	SCORE_V2(536870912, "Score v2", "2xScore"),
	LAST_MOD(1073741824, "Last Mod", "Last Mod");
	
	private long id;
	private String displayName;
	private String sigla;
	
	Mods(int id, String displayName, String sigla) {
		this.id = id;
		this.displayName = displayName;
		this.sigla = sigla; 
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
	
	public String getCode() {
		return sigla;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
}
