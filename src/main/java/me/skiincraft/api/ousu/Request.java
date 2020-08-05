package me.skiincraft.api.ousu;

import java.util.function.BiConsumer;

public interface Request<T> {
	
	public T get();
	public void getWithJson(BiConsumer<T, String> biConsumer);
	public T getSample();
	
	public default boolean isRequested() {
		return false;
	}

}
