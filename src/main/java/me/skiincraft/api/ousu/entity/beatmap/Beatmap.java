package me.skiincraft.api.ousu.entity.beatmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.OffsetDateTime;

import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.objects.Approval;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Genre;
import me.skiincraft.api.ousu.entity.user.User;
import me.skiincraft.api.ousu.impl.BeatmapImpl;
import me.skiincraft.api.ousu.json.SimpleJson;

/**<h1>Beatmap</h1>
 * <p>Interface to show the requested Beatmaps
 * after a {@link Request}</p>
 * 
 * <p>{@link BeatmapSet} are a set of beatmaps are not other types of beatmap</p>
 * 
 * @see BeatmapImpl A Implementation Class
 */
public interface Beatmap {
	
	/**<h1>Request<{@linkplain BeatmapSet}></h1>
	 * <p>A BeatmapSet request for this beatmap.
	 * 
	 * <br>If this beatmap is in a {@link BeatmapSet},
	 *  you may not need to make another request</br></p>
	 *  
	 *  <p>Check if it is available using:
	 *  <br><code>getBeatmapSet().wasRequested()</br></code></p>
	 *  @see Request
	 */
	Request<BeatmapSet> getBeatmapSet();
	
	/**<p>This {@code Long} is the BeatmapSet Id.
	 * <br> With this code it is possible to search
	 * Osu beatmaps set.</br>
	 */
	long getBeatmapSetId();
	
	/**<p>This {@code Long} is the Beatmap Id.
	 * <br> With this code it is possible to search
	 * Osu beatmap.</br>
	 */
	long getBeatmapId();
	
	/**<p>This {@link Approval} is the beatmap approval.</p>
	 * <br> You can return all possible qualifications.</br>
	 */
	Approval getApprovated();
	
	/**<p>This {@code Integer} is the Total Length of the beatmaps.</p>
	 */
	int getTotalLength();
	
	/**<p>This {@code Integer} is the Hit Length of the beatmaps.</p>
	 */
	int getHitLength();

	/**<p>This {@code String} is the difficult name of this beatmap.</p>
	 */
	String getVersion();
	/**<p>This {@code String} is the md5 of this beatmap.</p>
	 */
	String getFileMD5();
	
	/**<h1>Difficult</h1>
	 * <p>Is one of BeatMap's difficulty factors</p>
	 */
	float getDifficultSize();
	/**<h1>Difficult</h1>
	 * <p>Is one of BeatMap's difficulty factors</p>
	 */
	float getDifficultOverall();
	/**<h1>Difficult</h1>
	 * <p>Is one of BeatMap's difficulty factors</p>
	 */
	float getDifficultApproach();
	/**<h1>Difficult</h1>
	 * <p>Is one of BeatMap's difficulty factors</p>
	 */
	float getDifficultDrain();
	/**<h1>Difficult</h1>
	 * <p>Is one of BeatMap's difficulty factors</p>
	 */
	float getDifficultSpeed();
	/**<h1>Difficult</h1>
	 * <p>Is one of BeatMap's difficulty factors</p>
	 */
	float getDifficultAim();
	
	/**<p> {@link Gamemode} is the Gamemode from the beatmap</p>
	 */
	Gamemode getGameMode();
	
	int getSpinners();
	int getSliders();
	int getCircles();
	
	/**<h1>Publish Date</h1>
	 * <p> is the date the map was published on the Osu!</p>
	 */
	OffsetDateTime getPublishDate();
	
	/**<h1>Approved Date</h1>
	 * <p> is the date the map was approved on the Osu!</p>
	 */
	OffsetDateTime getApprovedDate();
	
	/**<h1>Approved Date</h1>
	 * <p> is the date of the last update on the beatmap.</p>
	 */
	OffsetDateTime getLastUpdateDate();
	
	/**
	 * <p>The Artist of this beatmap</p>
	 */
	String getArtist();
	
	/**
	 * <p>The Artist of this beatmap</p>
	 */
	String getArtistUnicode();
	
	/**
	 * <p>The Title of this beatmap</p>
	 */
	String getTitle();
	/**
	 * <p>The Title of this beatmap</p>
	 */
	String getTitleUnicode();
	
	/**<h1>Request<{@linkplain User}></h1>
	 * <p>A user request from the creator of this beatmap.
	 * 
	 * <br>If this user has been requested before,
	 *   you may not need to make another request</br></p>
	 *  
	 *  <p>Check if it is available using:
	 *  <br><code>getCreator().isRequested()</br></code></p>
	 *  
	 *  @param gamemode is the gamemode
	 *  @see Request
	 */
	Request<User> getCreator(Gamemode gamemode);
	
	/**<h1>Request<{@linkplain User}></h1>
	 * <p>A user request from the creator of this beatmap.
	 * 
	 * <br>If this user has been requested before,
	 *   you may not need to make another request</br></p>
	 *  
	 *  <p>Check if it is available using:
	 *  <br><code>getCreator().isRequested()</br></code></p>
	 *  
	 * @see Request 
	 */
	default Request<User> getCreator(){
		return getCreator(Gamemode.Standard);
	}
	
	/**
	 * <p>The Creator nickname of this beatmap</p>
	 */
	String getCreatorName();
	
	/**
	 * <p>The Creator Id of this beatmap</p>
	 */
	long getCreatorId();
	
	/**
	 * <p>The BPM of this beatmap</p>
	 */
	float getBPM();
	
	/**
	 * <p>Is the success rate of the map in percentage</p>
	 * <br>The calculation made is the total number of plays divided by the complete ones
	 * <br>{@code (getPlayCount() + getPassCount())/getPassCount()}</br>
	 * </br>
	 */
	String getSuccessRate();
	
	/**
	 * <p>This was not specified in the Original API</p>
	 */
	String getSource();
	
	/**
	 * <p>These are the beatmap tags, divided by "spaces"</p>
	 * <br>{@code String[] tags = argsRaw.split(" ")}</br>
	 */
	String[] getTags();
	
	/**<h1>Genre</h1>
	 * <p>Is the beatmap music genre</p> 
	 */
	Genre getGenre();
	
	/**
	 * <p>Counter of players who favored this beatmap.</p>
	 */
	int getFavoriteCount();
	
	/**
	 * <p>Counter of Players who played this map.</p>
	 */
	int getPlayCount();
	
	/**
	 * <p>Counter of Players who passed this map.</p>
	 */
	int getPassCount();
	
	/**
	 * <p>This was not specified in the Original API</p>
	 */
	float getRating();
	
	/**
	 * <p>If the beatmap has Storyboard, it will return "true".</p>
	 */
	boolean hasStoryboard();
	
	
	/**
	 * <p>If the beatmap has Video, it will return "true".</p>
	 */
	boolean hasVideo();
	
	/**
	 * <p>If the beatmap has Download unavailable, it will return "true".</p>
	 */
	boolean hasUnavailable();
	
	/**
	 * <p>If the beatmap has Audio unavailable, it will return "true".</p>
	 */
	boolean hasAudioUnavailable();

	
	/**
	 * <p>Is the maximum number of combo of this beatmap.</p>
	 */
	int getMaxCombo();
	
	/**
	 * <p>Is the stars (difficult) of this beatmap.</p>
	 */
	float getStars();
	
	/**
	 * <p>Is the stars (difficult) of this beatmap with emojis.</p>
	 */
	default String getStarsEmoji() {
		String dif = String.valueOf(getStars());
		int one = Integer.parseInt(String.valueOf(dif.charAt(0)));
		int two = Integer.parseInt(String.valueOf(dif.charAt(2)));
		
		StringBuilder fstars = new StringBuilder();
		float stars = Float.parseFloat(new DecimalFormat("#.0").format(getStars()).replace(",", "."));  
				
		for (int i = 0; i < one; i++) {
			fstars.append("★");
		}
		if (two >= 5) {
			return "**" + fstars.toString() + "✩** (" + stars + ")";
		} else {
			return "**" + fstars.toString() + "** (" + stars + ")";
		}
	}
	
	/**
	 * <p>Is the beatmap url.</p>
	 */
	default String getURL() {
		return "https://osu.ppy.sh/beatmapsets/" + getBeatmapSetId() + "#osu/" + getBeatmapId();
	}

	/**
	 * <p>Is the beatmap cover url.</p>
	 */
	default String getBeatmapCoverUrl() {
		return "https://assets.ppy.sh/beatmaps/"+ getBeatmapSetId() +"/covers/cover.jpg";
	}
	
	/**
	 * <p>Is the beatmap cover url.</p>
	 */
	default String getBeatmapThumbnailUrl() {
		return "https://b.ppy.sh/thumb/"+ getBeatmapSetId() + "l.jpg";
	}
	
	/**
	 * <p>Is the beatmap cover url.</p>
	 */
	default InputStream getBeatmapPreview() throws IOException {
		URLConnection conn = URI.create("http://b.ppy.sh/preview/" + getBeatmapSetId() + ".mp3").toURL().openConnection();
		return conn.getInputStream();
	}
	/**<h1>Get a Sample</h1>
	 * <p>This example was taken directly from the official Osu! </p>
	 * <p>This example will not make any Request</p>
	 */
	static Beatmap getSample() {
		return new BeatmapImpl(new SimpleJson().getJsonAsResource("beatmapJson.json").get(0).getAsJsonObject(), null);
	}
}
