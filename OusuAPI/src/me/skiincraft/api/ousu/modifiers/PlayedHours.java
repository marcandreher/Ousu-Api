package me.skiincraft.api.ousu.modifiers;

import java.text.DecimalFormat;

public class PlayedHours {
	
	private int horas;
	private int minutos;
	private int segundos;
	
	public PlayedHours(int hours ,int minutes,int secounds) {
		this.horas = hours;
		this.minutos = minutes;
		this.segundos = secounds;
	}
	
	public int getDays() {
		return horas/24;
	}
	
	public int getHours() {
		float f = new Float(horas);
		f = f/60;
		String h = new DecimalFormat("#.0").format(f)
				.replace(".", "")
				.replace(",", "");
		
		return Integer.valueOf(h);
	}
	
	public int getTotalHours() {
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
			return horas/60 + "h" + minutos + "m";
		}
		if (getHours() == 0) {
			return minutos + "m " + segundos + "s";
		}
		return getDays() +"d "+ getHours() + "h "+ minutos + "m";
	}
	
	
	

}
