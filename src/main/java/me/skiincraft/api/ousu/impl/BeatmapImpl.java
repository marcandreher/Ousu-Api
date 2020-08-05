package me.skiincraft.api.ousu.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.BiConsumer;

import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.beatmap.BeatmapSet;
import me.skiincraft.api.ousu.entity.objects.Approval;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Genre;
import me.skiincraft.api.ousu.entity.user.User;

public class BeatmapImpl implements Beatmap {

	private JsonObject object;
	private OusuAPI api;
	private BeatmapSet beatmapset;
	
	public BeatmapImpl(JsonObject jsonObject, OusuAPI api) {
		this.object = jsonObject;
		this.api = api;
	}
	
	public BeatmapImpl(JsonObject jsonObject, BeatmapSet beatmapset ,OusuAPI api) {
		this.object = jsonObject;
		this.beatmapset = beatmapset;
		this.api = api;
	}
	
	private Date getDate(String parse) {
		try {
			return (object.get(parse).isJsonNull()) ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(object.get(parse).getAsString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Request<BeatmapSet> getBeatmapSet() {
		if (beatmapset == null) {
			return api.getBeatmapSet(getBeatmapSetId());
		}
		return new Request<BeatmapSet>() {

			public boolean isRequested() {
				return true;
			}
			
			public BeatmapSet get() {
				return beatmapset;
			}

			public void getWithJson(BiConsumer<BeatmapSet, String> biConsumer) {
				biConsumer.accept(get(), "[]");
			}

			public BeatmapSet getSample() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	public long getBeatmapSetId() {
		return object.get("beatmapset_id").getAsLong();
	}

	public long getBeatmapId() {
		return object.get("beatmap_id").getAsLong();
	}

	public Approval getApprovated() {
		return Approval.getById(object.get("approved").getAsInt());
	}

	public int getTotalLength() {
		return object.get("total_length").getAsInt();
	}

	public int getHitLength() {
		return object.get("hit_length").getAsInt();
	}

	public String getVersion() {
		return object.get("version").getAsString();
	}

	public String getFileMD5() {
		return object.get("file_md5").getAsString();
	}

	public float getDifficultSize() {
		return object.get("diff_size").getAsFloat();
	}

	public float getDifficultOverall() {
		return object.get("diff_overall").getAsFloat();
	}

	public float getDifficultApproach() {
		return object.get("diff_approach").getAsFloat();
	}

	public float getDifficultDrain() {
		return object.get("diff_drain").getAsFloat();
	}

	public float getDifficultSpeed() {
		return object.get("diff_speed").getAsFloat();
	}

	public float getDifficultAim() {
		return object.get("diff_aim").getAsFloat();
	}

	public Gamemode getGameMode() {
		return Gamemode.getById(object.get("mode").getAsInt());
	}

	public int getSpinners() {
		return object.get("count_spinner").getAsInt();
	}

	public int getSliders() {
		return object.get("count_slider").getAsInt();
	}

	public int getCircles() {
		return object.get("count_normal").getAsInt();
	}

	public Date getPublishDate() {
		return getDate("submit_date");
	}

	public Date getApprovedDate() {
		return getDate("approved_date");
	}

	public Date getLastUpdateDate() {
		return getDate("last_update");
	}

	public String getArtist() {
		return object.get("artist").getAsString();
	}

	public String getArtistUnicode() {
		return object.get("artist_unicode").getAsString();
	}

	public String getTitle() {
		return object.get("title").getAsString();
	}

	public String getTitleUnicode() {
		return object.get("title_unicode").getAsString();
	}

	public Request<User> getCreator(Gamemode mode) {
		return api.getUser(String.valueOf(getCreatorId()), mode);
	}

	public String getCreatorName() {
		return object.get("creator").getAsString();
	}

	public long getCreatorId() {
		return object.get("creator_id").getAsLong();
	}

	public float getBPM() {
		return object.get("bpm").getAsFloat();
	}

	public String getSuccessRate() {
		float plays = getPlayCount();
		float pass = getPassCount();
		
		DecimalFormat df = new DecimalFormat("#.0");
		
		return df.format((pass*100)/plays) + "%";
	}

	public String getSource() {
		return object.get("source").getAsString();
	}

	public String[] getTags() {
		return object.get("tags").getAsString().split(" ");
	}

	public Genre getGenre() {
		return Genre.getById(object.get("genre_id").getAsInt());
	}

	public int getFavoriteCount() {
		return object.get("favourite_count").getAsInt();
	}

	public int getPlayCount() {
		return object.get("playcount").getAsInt();
	}

	public int getPassCount() {
		return object.get("passcount").getAsInt();
	}

	public float getRating() {
		return object.get("rating").getAsFloat();
	}

	public boolean hasStoryboard() {
		return object.get("storyboard").getAsInt() != 0;
	}

	public boolean hasVideo() {
		return object.get("video").getAsInt() != 0;
	}

	public boolean hasUnavailable() {
		return object.get("download_unavailable").getAsInt() != 0;
	}

	public boolean hasAudioUnavailable() {
		return object.get("audio_unavailable").getAsInt() != 0;
	}

	public int getMaxCombo() {
		return object.get("max_combo").getAsInt();
	}

	public float getStars() {
		return object.get("diff_rating").getAsFloat();
	}

	public String getStarsEmoji() {
		String rating = String.valueOf(getStars());
		int one = Integer.valueOf(rating.charAt(0));
		int two = Integer.valueOf(rating.charAt(2));
		
		StringBuffer stars = new StringBuffer();
		float floatStars = new Float(new DecimalFormat("#.0").format(getStars()).replace(",", "."));
		for (int i = 0;i < one; i++) {
			stars.append("★");
		}
		
		return (two >= 5) ? "**" + stars.toString() + "✩** (" + floatStars + ")": "**" + stars.toString() + "** (" + floatStars + ")";
	}

	public String getURL() {
		return "https://osu.ppy.sh/beatmapsets/" + getBeatmapSetId() + "#osu/" + getBeatmapId();
	}

	public String getBeatmapCoverUrl() {
		return "https://assets.ppy.sh/beatmaps/"+ getBeatmapSetId() +"/covers/cover.jpg";
	}

	public String getBeatmapThumbnailUrl() {
		return "https://b.ppy.sh/thumb/"+ getBeatmapSetId() + "l.jpg";
	}

	public InputStream getBeatmapPreview() throws IOException {
		URLConnection conn = new URL("http://b.ppy.sh/preview/" + getBeatmapSetId() + ".mp3").openConnection();
		return conn.getInputStream();
	}

}
