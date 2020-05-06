package me.skiincraft.api.ousu.beatmaps;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.exceptions.InvalidBeatmapException;
import me.skiincraft.api.ousu.json.EndPointBeatmap;
import me.skiincraft.api.ousu.modifiers.Approvated;
import me.skiincraft.api.ousu.modifiers.Gamemode;
import me.skiincraft.api.ousu.modifiers.Genre;
import me.skiincraft.api.ousu.utils.SortBeatmap;

public class BeatmapSetBuilder {

	private String get = "https://osu.ppy.sh/api/get_beatmaps";
	private OusuAPI api;
	
	private EndPointBeatmap[] beatmap;
	
	private int beatmapSetId;
	
	public BeatmapSetBuilder(int setId) {
		this.beatmapSetId = setId;
	}
	
	public void setAPI(OusuAPI api) {
		this.api = api;
	}
	
	private void connectionRequest() {
		HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "s", Integer.toString(beatmapSetId));
		bc.accept("application/json").contentType();
		
		Gson g = new Gson();
		EndPointBeatmap[] us = g.fromJson(bc.body(), EndPointBeatmap[].class);
		beatmap = us;
	}
	
	public List<Beatmap> build() {
		connectionRequest();
		List<Beatmap> l = new ArrayList<Beatmap>();
		for (EndPointBeatmap bp : this.beatmap) {
			l.add(new Beatmap() {
				
				@Override
				public boolean hasVideo() {
					if (bp.isVideo() != 0) {
						return true;
					}
					return false;
				}
				
				@Override
				public boolean hasUnavailable() {
					if (bp.isDownload_unavailable() != 0) {
						return true;
					}
					return false;
				}
				
				@Override
				public boolean hasStoryboard() {
					if (bp.isStoryboard() != 0) {
						return true;
					}
					return false;
				}
				
				@Override
				public boolean hasAudioUnavailable() {
					if (bp.isAudio_unavailable() != 0) {
						return true;
					}
					return false;
				}
				
				@Override
				public String getVersion() {
					return bp.getVersion();
				}
				
				@Override
				public int getTotalLength() {
					return bp.getTotal_length();
				}
				
				@Override
				public String getTitleUnicode() {
					return bp.getTitle_unicode();
				}
				
				@Override
				public String getTitle() {
					return bp.getTitle();
				}
				
				@Override
				public String[] getTags() {
					return bp.getTags().split(" ");
				}
				
				@Override
				public float getStars() {
					return bp.getDifficultyrating();
				}
				
				@Override
				public int getSpinners() {
					return bp.getCount_spinner();
				}
				
				@Override
				public String getSource() {
					return bp.getSource();
				}
				
				@Override
				public int getSliders() {
					return bp.getCount_slider();
				}
				
				@Override
				public float getRating() {
					return bp.getRating();
				}
				
				@Override
				public Date getPublishDate() {
					try {
						return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bp.getSubmit_date());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				@Override
				public int getPlayCount() {
					return bp.getPlaycount();
				}
				
				@Override
				public int getPassCount() {
					return bp.getPasscount();
				}
				
				@Override
				public int getMaxCombo() {
					return bp.getMax_combo();
				}
				
				@Override
				public Date getLastUpdateDate() {
					try {
						return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bp.getLast_update());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				@Override
				public int getHitLength() {
					return bp.getHit_length();
				}
				
				@Override
				public Genre getGenre() {
					for (Genre genre : Genre.values()) {
						if (bp.getGenre_id() == genre.getID()) {
							return genre;
						}
					}
					return null;
				}
				
				@Override
				public Gamemode getGameMode() {
					for (Gamemode gamemode : Gamemode.values()) {
						if (bp.getMode() == gamemode.getId()) {
							return gamemode;
						}
					}
					return null;
				}
				
				@Override
				public String getFileMD5() {
					return bp.getFile_md5();
				}
				
				@Override
				public int getFavoriteCount() {
					return bp.getFavourite_count();
				}
				
				@Override
				public double getDifficultSpeed() {
					return bp.getDiff_speed();
				}
				
				@Override
				public double getDifficultSize() {
					return bp.getDiff_size();
				}
				
				@Override
				public double getDifficultOverall() {
					return bp.getDiff_overall();
				}
				
				@Override
				public double getDifficultDrain() {
					return bp.getDiff_drain();
				}
				
				@Override
				public double getDifficultApproach() {
					return bp.getDiff_approach();
				}
				
				@Override
				public double getDifficultAim() {
					return bp.getDiff_aim();
				}
				
				@Override
				public int getCreatorId() {
					return bp.getCreator_id();
				}
				
				@Override
				public String getCreator() {
					return bp.getCreator();
				}
				
				@Override
				public int getCircles() {
					return bp.getCount_normal();
				}
				
				@Override
				public int getBeatmapSetID() {
					return bp.getBeatmapset_id();
				}
				
				@Override
				public int getBeatmapID() {
					return bp.getBeatmap_id();
				}
				
				@Override
				public float getBPM() {
					return bp.getBpm();
				}
				
				@Override
				public String getArtistUnicode() {
					return bp.getArtist_unicode();
				}
				
				@Override
				public String getArtist() {
					return bp.getArtist();
				}
				
				@Override
				public Date getApprovedDate() {
					try {
						return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bp.getApproved_date());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return null;
				}
				
				@Override
				public Approvated getApprovated() {
					for (Approvated approvated : Approvated.values()) {
						if (bp.getApproved() == approvated.getId()) {
							return approvated;
						}
					}
					return null;
				}
				
				@Override
				public String getBeatmapCoverUrl() {
					return "https://assets.ppy.sh/beatmaps/"+ bp.getBeatmapset_id() +"/covers/cover.jpg";
				}

				@Override
				public String getBeatmapThumbnailUrl() {
					return "https://b.ppy.sh/thumb/"+ bp.getBeatmapset_id() +"l.jpg";
				}

				@Override
				public String getSuccessRate() {
					float plays = bp.getPlaycount();
					float pass = bp.getPasscount();
					
					DecimalFormat df = new DecimalFormat("#.0");
					
					return df.format((pass*100)/plays) + "%";
				}

				@Override
				public InputStream getBeatmapPreview() throws IOException  {
					URLConnection conn = new URL("http://b.ppy.sh/preview/" + bp.getBeatmapset_id() + ".mp3").openConnection();
					return conn.getInputStream();
				}

				@Override
				public String getStarsEmoji() {
					String dif = bp.getDifficultyrating() + "";
					int one = Integer.valueOf(dif.charAt(0) + "");
					int two = Integer.valueOf(dif.charAt(2) + "");

					String fullstars = "";
					for (int i = 0; i < one; i++) {
						fullstars += "★";
					}
					if (two >= 5) {
						return "**" + fullstars + "✩** (" + getStars() + ")";
					} else {
						return "**" + fullstars + "** (" + getStars() + ")";
					}
				}

				@Override
				public String getURL() {
					return "https://osu.ppy.sh/beatmapsets/" + bp.getBeatmapset_id() + "#osu/" + bp.getBeatmap_id();
				}
			});
		}
		
		try {
			l.get(0).getBPM();
		} catch (IndexOutOfBoundsException e) {
				throw new InvalidBeatmapException("Este beatmapID solicitado esta invalido. (id:"+ beatmapSetId +")", e);
		}
		Collections.sort(l, new SortBeatmap());
		return l;
	}

}