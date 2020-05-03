package me.skiincraft.api.ousu.beatmaps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.json.EndPointBeatmap;
import me.skiincraft.api.ousu.modifiers.Approvated;
import me.skiincraft.api.ousu.modifiers.Gamemode;
import me.skiincraft.api.ousu.modifiers.Genre;

public class BeatmapBuilder {

	private String get = "https://osu.ppy.sh/api/get_beatmaps";
	private OusuAPI api;
	
	private int beatmapid;
	
	private EndPointBeatmap beatmap;
	
	public BeatmapBuilder(int id) {
		this.beatmapid = id;
	}
	
	private void connectionRequest() {
		HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "b", Integer.toString(beatmapid));
		bc.accept("application/json").contentType();
		
		Gson g = new Gson();
		EndPointBeatmap[] us = g.fromJson(bc.body(), EndPointBeatmap[].class);
		beatmap = us[0];
	}
	
	public Beatmap build() {
		connectionRequest();
		return new Beatmap() {
			
			@Override
			public boolean hasVideo() {
				if (beatmap.isVideo() != 0) {
					return true;
				}
				return false;
			}
			
			@Override
			public boolean hasUnavailable() {
				if (beatmap.isDownload_unavailable() != 0) {
					return true;
				}
				return false;
			}
			
			@Override
			public boolean hasStoryboard() {
				if (beatmap.isStoryboard() != 0) {
					return true;
				}
				return false;
			}
			
			@Override
			public boolean hasAudioUnavailable() {
				if (beatmap.isAudio_unavailable() != 0) {
					return true;
				}
				return false;
			}
			
			@Override
			public String getVersion() {
				return beatmap.getVersion();
			}
			
			@Override
			public int getTotalLength() {
				return beatmap.getTotal_length();
			}
			
			@Override
			public String getTitleUnicode() {
				return beatmap.getTitle_unicode();
			}
			
			@Override
			public String getTitle() {
				return beatmap.getTitle();
			}
			
			@Override
			public String[] getTags() {
				return beatmap.getTags().split(" ");
			}
			
			@Override
			public float getStars() {
				return beatmap.getDifficultyrating();
			}
			
			@Override
			public int getSpinners() {
				return beatmap.getCount_spinner();
			}
			
			@Override
			public String getSource() {
				return beatmap.getSource();
			}
			
			@Override
			public int getSliders() {
				return beatmap.getCount_slider();
			}
			
			@Override
			public float getRating() {
				return beatmap.getRating();
			}
			
			@Override
			public Date getPublishDate() {
				try {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beatmap.getSubmit_date());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			public int getPlayCount() {
				return beatmap.getPlaycount();
			}
			
			@Override
			public int getPassCount() {
				return beatmap.getPasscount();
			}
			
			@Override
			public int getMaxCombo() {
				return beatmap.getMax_combo();
			}
			
			@Override
			public Date getLastUpdateDate() {
				try {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beatmap.getLast_update());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			public int getHitLength() {
				return beatmap.getHit_length();
			}
			
			@Override
			public Genre getGenre() {
				for (Genre genre : Genre.values()) {
					if (beatmap.getGenre_id() == genre.getID()) {
						return genre;
					}
				}
				return null;
			}
			
			@Override
			public Gamemode getGameMode() {
				for (Gamemode gamemode : Gamemode.values()) {
					if (beatmap.getMode() == gamemode.getId()) {
						return gamemode;
					}
				}
				return null;
			}
			
			@Override
			public String getFileMD5() {
				return beatmap.getFile_md5();
			}
			
			@Override
			public int getFavoriteCount() {
				return beatmap.getFavourite_count();
			}
			
			@Override
			public double getDifficultSpeed() {
				return beatmap.getDiff_speed();
			}
			
			@Override
			public double getDifficultSize() {
				return beatmap.getDiff_size();
			}
			
			@Override
			public double getDifficultOverall() {
				return beatmap.getDiff_overall();
			}
			
			@Override
			public double getDifficultDrain() {
				return beatmap.getDiff_drain();
			}
			
			@Override
			public double getDifficultApproach() {
				return beatmap.getDiff_approach();
			}
			
			@Override
			public double getDifficultAim() {
				return beatmap.getDiff_aim();
			}
			
			@Override
			public int getCreatorId() {
				return beatmap.getCreator_id();
			}
			
			@Override
			public String getCreator() {
				return beatmap.getCreator();
			}
			
			@Override
			public int getCircles() {
				return beatmap.getCount_normal();
			}
			
			@Override
			public int getBeatmapSetID() {
				return beatmap.getBeatmapset_id();
			}
			
			@Override
			public int getBeatmapID() {
				return beatmap.getBeatmap_id();
			}
			
			@Override
			public float getBPM() {
				return beatmap.getBpm();
			}
			
			@Override
			public String getArtistUnicode() {
				return beatmap.getArtist_unicode();
			}
			
			@Override
			public String getArtist() {
				return beatmap.getArtist();
			}
			
			@Override
			public Date getApprovedDate() {
				try {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beatmap.getApproved_date());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			public Approvated getApprovated() {
				for (Approvated approvated : Approvated.values()) {
					if (beatmap.getApproved() == approvated.getId()) {
						return approvated;
					}
				}
				return null;
			}

			@Override
			public String getBeatmapCoverUrl() {
				return "https://assets.ppy.sh/beatmaps/"+ beatmap.getBeatmapset_id() +"/covers/cover.jpg";
			}

			@Override
			public String getBeatmapThumbnailUrl() {
				return "https://b.ppy.sh/thumb/"+ beatmap.getBeatmapset_id() +"l.jpg";
			}
		};
	}

	public void setAPI(OusuAPI api) {
		this.api = api;
	}

}