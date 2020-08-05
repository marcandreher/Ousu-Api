package me.skiincraft.api.ousu.entity.beatmap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.objects.Approval;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Genre;
import me.skiincraft.api.ousu.entity.user.User;

public interface Beatmap {
	
	Request<BeatmapSet> getBeatmapSet();
	
	long getBeatmapSetId();
	long getBeatmapId();
	Approval getApprovated();
	
	int getTotalLength();
	int getHitLength();
	
	String getVersion();
	String getFileMD5();
	
	float getDifficultSize();
	float getDifficultOverall();
	float getDifficultApproach();
	float getDifficultDrain();
	float getDifficultSpeed();
	float getDifficultAim();
	
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
	
	Request<User> getCreator(Gamemode gamemode);
	
	default Request<User> getCreator(){
		return getCreator(Gamemode.Standard);
	}
	String getCreatorName();
	long getCreatorId();
	
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
