package me.skiincraft.api.ousu.beatmaps;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import me.skiincraft.api.ousu.modifiers.Approvated;
import me.skiincraft.api.ousu.modifiers.Gamemode;
import me.skiincraft.api.ousu.modifiers.Genre;

public interface Beatmap {
	
	int getBeatmapSetID();
	int getBeatmapID();
	Approvated getApprovated();
	
	int getTotalLength();
	int getHitLength();
	
	String getVersion();
	String getFileMD5();
	
	double getDifficultSize();
	double getDifficultOverall();
	double getDifficultApproach();
	double getDifficultDrain();
	double getDifficultSpeed();
	double getDifficultAim();
	
	Gamemode getGameMode();
	
	int getSpinners();
	int getSliders();
	int getCircles();
	
	Date getPublishDate();
	Date getApprovedDate();
	Date getLastUpdateDate();
	
	String getArtist();
	String getArtistUnicode();
	String getTitle();
	String getTitleUnicode();
	
	String getCreator();
	int getCreatorId();
	
	float getBPM();
	String getSuccessRate();
	
	String getSource();
	String[] getTags();
	Genre getGenre();
	
	int getFavoriteCount();
	int getPlayCount();
	int getPassCount();
	
	float getRating();
	
	boolean hasStoryboard();
	boolean hasVideo();
	boolean hasUnavailable();
	boolean hasAudioUnavailable();

	int getMaxCombo();
	
	float getStars();
	String getStarsEmoji();
	
	String getURL();
	String getBeatmapCoverUrl();
	String getBeatmapThumbnailUrl();
	InputStream getBeatmapPreview() throws IOException;

}
