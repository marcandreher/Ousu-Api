package me.skiincraft.api.ousu.modifiers;

public class PlayedHours {
	
	private int days;
	private int horas;
	private int minutos;
	private int segundos;
	
	public PlayedHours(int days ,int hours ,int minutes,int secounds) {
		this.days = days;
		this.horas = hours;
		this.minutos = minutes;
		this.segundos = secounds;
	}
	
	public int getDays() {
		return days;
	}
	
	public int getHours() {
		return horas;
	}

	public int getMinutes() {
		return minutos;
	}

	public int getSecounds() {
		return segundos;
	}

	@Override
	public String toString() {
		if (getDays() == 0) {
			return horas + "h" + minutos + "m";
		}
		if (getHours() == 0) {
			return minutos + "m " + segundos + "s";
		}
		return getDays() +"d "+ getHours() + "h "+ minutos + "m";
	}
	
	
	

}
