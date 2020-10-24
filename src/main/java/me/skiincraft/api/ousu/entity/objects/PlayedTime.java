package me.skiincraft.api.ousu.entity.objects;

public class PlayedTime {
	
	private final int days;
	private final int horas;
	private final int minutos;
	private final int segundos;
	
	public PlayedTime(int days ,int hours ,int minutes,int secounds) {
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
