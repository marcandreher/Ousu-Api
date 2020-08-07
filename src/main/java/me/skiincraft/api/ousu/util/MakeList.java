package me.skiincraft.api.ousu.util;

import java.util.List;

@FunctionalInterface
public abstract interface MakeList<T> {
	
	public abstract List<T> make();
	

}
