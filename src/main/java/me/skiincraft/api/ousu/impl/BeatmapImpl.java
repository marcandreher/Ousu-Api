package me.skiincraft.api.ousu.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;

import com.google.gson.JsonObject;

import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.requests.Request;
import me.skiincraft.api.ousu.entity.beatmap.Beatmap;
import me.skiincraft.api.ousu.entity.beatmap.BeatmapSet;
import me.skiincraft.api.ousu.entity.objects.Approval;
import me.skiincraft.api.ousu.entity.objects.Gamemode;
import me.skiincraft.api.ousu.entity.objects.Genre;
import me.skiincraft.api.ousu.entity.user.User;

public class BeatmapImpl implements Beatmap {

	private final JsonObject object;
	private final OusuAPI api;
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
	
	private OffsetDateTime getDate(String parse) {
		if (object.get(parse).isJsonNull()){
			return null;
		}
		LocalDateTime time = LocalDateTime.parse(object.get(parse).getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return OffsetDateTime.of(time, ZoneOffset.UTC);
	}
	
	boolean isNull(String str) {
		return object.get(str).isJsonNull();
	}
	
	public Request<BeatmapSet> getBeatmapSet() {
		if (beatmapset == null) {
			return api.getBeatmapSet(getBeatmapSetId());
		}
		return new Request<BeatmapSet>() {

			public boolean wasRequested() {
				return true;
			}
			
			public BeatmapSet get() {
				return beatmapset;
			}

			public void getWithJson(BiConsumer<BeatmapSet, String> biConsumer) {
				biConsumer.accept(get(), "[]");
			}

			public BeatmapSet getSample() {
				return BeatmapSet.getSample();
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
		return isNull("diff_size") ? 0 :  object.get("diff_size").getAsFloat();
	}

	public float getDifficultOverall() {
		return isNull("diff_overall") ? 0 : object.get("diff_overall").getAsFloat();
	}

	public float getDifficultApproach() {
		return isNull("diff_approach") ? 0 : object.get("diff_approach").getAsFloat();
	}

	public float getDifficultDrain() {
		return isNull("diff_drain") ? 0 : object.get("diff_drain").getAsFloat();
	}

	public float getDifficultSpeed() {
		return isNull("diff_speed") ? 0 : object.get("diff_speed").getAsFloat();
	}

	public float getDifficultAim() {
		return isNull("diff_aim") ? 0 : object.get("diff_aim").getAsFloat();
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

	public OffsetDateTime getPublishDate() {
		return getDate("submit_date");
	}

	public OffsetDateTime getApprovedDate() {
		return getDate("approved_date");
	}

	public OffsetDateTime getLastUpdateDate() {
		return getDate("last_update");
	}

	public String getArtist() {
		return object.get("artist").getAsString();
	}

	public String getArtistUnicode() {
		return isNull("title_unicode")? null : object.get("artist_unicode").getAsString();
	}

	public String getTitle() {
		return object.get("title").getAsString();
	}

	public String getTitleUnicode() {
		return isNull("title_unicode")? null : object.get("title_unicode").getAsString();
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
		return isNull("max_combo") ? 0 : object.get("max_combo").getAsInt();
	}

	public float getStars() {
		return object.get("difficultyrating").getAsFloat();
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
		URLConnection conn = URI.create("http://b.ppy.sh/preview/" + getBeatmapSetId() + ".mp3").toURL().openConnection();
		return conn.getInputStream();
	}

	public String toString() {
		return "BeatmapImpl [getBeatmapId()="
				+ getBeatmapId() + ", getTitle()=" + getTitle() + ", getVersion()=" + getVersion() + ", getURL()="
				+ getURL() + "]";
	}
	
	

}
