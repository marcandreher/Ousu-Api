package me.skiincraft.api.ousu.entity.user;

import java.util.List;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.PlayedTime;
import me.skiincraft.api.ousu.entity.objects.ProfileEvents;
import me.skiincraft.api.ousu.entity.score.RecentScore;
import me.skiincraft.api.ousu.entity.score.Score;
import me.skiincraft.api.ousu.exceptions.NoHistoryException;

public interface User {
	
	/** 
	@return Retorna a ID do Jogador;
	*/
	long getUserId();
	/**
	@return Retorna o username do Jogador
	*/
	String getUsername();
	/**
	@return Retorna a data do cadastro do Jogador em String.
	*/
	String getJoinDate();
	/**
	@return Retorna o gamemode do jogador.
	*/
	Gamemode getGamemode();
	/**
	@return Retorna o level do jogador.
	*/
	float getLevel();
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
	int getCountryRanking();
	
	float getPpRaw();

	/**
	 * Ao utilizar este metodo, ira fazer um novo request
	 * <p>Cuidado com o limite de 60 requests por minuto.
	 * 
	@see Score;
	@param limit Ele ira retornar no maximo 100 pontuações por request
	@return Retorna as melhores pontuações deste jogador.
	@throws NoHistoryException
	*/
	default Request<List<Score>> getTopScore(int limit){
		return getTopScore(getGamemode(), limit);
	};
	Request<List<Score>> getTopScore(Gamemode gamemode, int limit);
	/**
	 * Ao utilizar este metodo, ira fazer um novo request
	 * <p>Cuidado com o limite de 60 requests por minuto.
	 * 
	 @see Score;
	 @param limit Ele ira retornar no maximo 100 pontuações por request
	 @return Retorna os ultimos beatmaps jogador.
	 @throws NoHistoryException
	*/
	default Request<List<RecentScore>> getRecentScore(int limit){
		return getRecentScore(getGamemode(), limit);
	};
	Request<List<RecentScore>> getRecentScore(Gamemode gamemode, int limit);
	
}
