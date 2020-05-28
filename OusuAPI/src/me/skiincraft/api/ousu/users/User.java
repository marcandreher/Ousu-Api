package me.skiincraft.api.ousu.users;

import java.util.List;

import me.skiincraft.api.ousu.exceptions.NoHistoryException;
import me.skiincraft.api.ousu.modifiers.PlayedTime;
import me.skiincraft.api.ousu.modifiers.ProfileEvents;
import me.skiincraft.api.ousu.scores.Score;

public interface User {
	
	/** 
	@return Retorna a ID do Jogador;
	*/
	int getUserID();
	/**
	@return Retorna o username do Jogador
	*/
	String getUserName();
	/**
	@return Retorna a data do cadastro do Jogador em String.
	*/
	String getJoinDate();
	/**
	@return Retorna o level do jogador.
	*/
	int getLevel();
	/**
	@return Retorna o codigo do pais.
	*/
	String getCountryCode();
	
	/**
	@return Retorna a precisão do jogador.
	*/
	float getAccuracy();
	
	int get300();
	int get100();
	int get50();
	
	int getSS();
	int getSSh();
	int getS();
	int getSh();
	int getA();
	
	/**
	@return Retorna a quantidade de vezes jogadas.
	*/
	int getPlayCount();
	/**
	@return Retorna as horas jogadas.
	*/
	PlayedTime getPlayedHours();
	
	/**
	@return Retorna o link do avatar do jogador.
	*/
	String getUserAvatar();
	/**
	@return Retorna a bandeira do pais em miniatura.
	*/
	String getUserFlag();
	
	/**
	@return Retorna o link de perfil do jogador.
	*/
	String getURL();
	
	/**
	@return Retorna os ultimos eventos deste jogador.
	*/
	List<ProfileEvents> getProfileEvents();
	
	/**
	@return Retorna toda a pontuação em mapas ranked.
	*/
	long getRankedScore();
	/**
	@return Retorna toda a pontuação do jogador.
	*/
	long getTotalScore();
	
	/**
	@return Retorna a classificação global do jogador.
	*/
	int getRanking();
	/**
	@return Retorna a classificação nacional do jogador.
	*/
	int getNacionalRanking();
	
	int getPP();

	/**
	 * Ao utilizar este metodo, ira fazer um novo request
	 * <p>Cuidado com o limite de 60 requests por minuto.
	 * 
	@see Score;
	@param limit Ele ira retornar no maximo 100 pontuações por request
	@return Retorna as melhores pontuações deste jogador.
	@throws NoHistoryException
	*/
	List<Score> getTopScore(int limit);
	/**
	 * Ao utilizar este metodo, ira fazer um novo request
	 * <p>Cuidado com o limite de 60 requests por minuto.
	 * 
	 @see Score;
	 @param limit Ele ira retornar no maximo 100 pontuações por request
	 @return Retorna os ultimos beatmaps jogador.
	 @throws NoHistoryException
	*/
	List<Score> getRecentScore(int limit);
}
