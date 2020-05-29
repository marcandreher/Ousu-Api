package me.skiincraft.api.ousu.modifiers;

public enum Approvated {
	
	UNSPECIFIED(-3),Graveyard(-2), WIP(-1),Pending(0), Ranked(1), Approved(2), Qualified(3), Loved(4);

	private int id;
	Approvated(int id){
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
}
