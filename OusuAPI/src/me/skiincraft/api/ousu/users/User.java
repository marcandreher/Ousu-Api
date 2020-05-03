package me.skiincraft.api.ousu.users;

public interface User {
	
	int getUserID();
	String getUserName();
	String getJoinDate();
	int getLevel();
	
	int get300();
	int get100();
	int get50();
	
	int getSS();
	int getSSh();
	int getS();
	int getSh();
	int getA();
	
	int getPlayCount();
	String getUserAvatar();
	
	long getRankedScore();
	long getTotalScore();
	
	int getRanking();
	int getNacionalRanking();
	
	int getPP();
	Object[] getEvents();
	

}
