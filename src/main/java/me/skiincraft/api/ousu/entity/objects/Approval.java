package me.skiincraft.api.ousu.entity.objects;

import java.util.Arrays;

public enum Approval {
	
	UNSPECIFIED(-3),Graveyard(-2), WIP(-1),Pending(0), Ranked(1), Approved(2), Qualified(3), Loved(4);

	private int id;
	Approval(int id){
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static Approval getById(int id) {
		return Arrays.stream(Approval.values()).filter(o -> o.id == id).findFirst().orElse(null);
	}
}
