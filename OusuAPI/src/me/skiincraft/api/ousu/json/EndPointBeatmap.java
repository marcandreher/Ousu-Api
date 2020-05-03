package me.skiincraft.api.ousu.json;

public class EndPointBeatmap {
	
    private int beatmapset_id;
    private int beatmap_id;
    private int approved;
    private int total_length;
    private int hit_length;
    private String version;
    private String file_md5;
    private double diff_size;
    private double diff_overall;
    private double diff_approach;
    private double diff_drain;
    private int mode;
    private int count_normal;
    private int count_slider;
    private int count_spinner;
    private String submit_date;
    private String approved_date;
    private String last_update;
    private String artist;
    private String artist_unicode;
    private String title;
    private String title_unicode;
    private String creator;
    private int creator_id;
    private float bpm;
    private String source;
    private String tags;
    private int genre_id;
    private int language_id;
    private int favourite_count;
    private float rating;
    private int storyboard;
    private int video;
    private int download_unavailable;
    private int audio_unavailable;
    private int playcount;
    private int passcount;
    private Object packs;
    private int max_combo;
    private float diff_aim;
    private float diff_speed;
    private float difficultyrating;
    
    public EndPointBeatmap() {
    	
    }

	public int getBeatmapset_id() {
		return beatmapset_id;
	}

	public void setBeatmapset_id(int beatmapset_id) {
		this.beatmapset_id = beatmapset_id;
	}

	public int getBeatmap_id() {
		return beatmap_id;
	}

	public void setBeatmap_id(int beatmap_id) {
		this.beatmap_id = beatmap_id;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public int getTotal_length() {
		return total_length;
	}

	public void setTotal_length(int total_length) {
		this.total_length = total_length;
	}

	public int getHit_length() {
		return hit_length;
	}

	public void setHit_length(int hit_length) {
		this.hit_length = hit_length;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFile_md5() {
		return file_md5;
	}

	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}

	public double getDiff_size() {
		return diff_size;
	}

	public void setDiff_size(double diff_size) {
		this.diff_size = diff_size;
	}

	public double getDiff_overall() {
		return diff_overall;
	}

	public void setDiff_overall(double diff_overall) {
		this.diff_overall = diff_overall;
	}

	public double getDiff_approach() {
		return diff_approach;
	}

	public void setDiff_approach(double diff_approach) {
		this.diff_approach = diff_approach;
	}

	public double getDiff_drain() {
		return diff_drain;
	}

	public void setDiff_drain(double diff_drain) {
		this.diff_drain = diff_drain;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getCount_normal() {
		return count_normal;
	}

	public void setCount_normal(int count_normal) {
		this.count_normal = count_normal;
	}

	public int getCount_slider() {
		return count_slider;
	}

	public void setCount_slider(int count_slider) {
		this.count_slider = count_slider;
	}

	public int getCount_spinner() {
		return count_spinner;
	}

	public void setCount_spinner(int count_spinner) {
		this.count_spinner = count_spinner;
	}

	public String getSubmit_date() {
		return submit_date;
	}

	public void setSubmit_date(String submit_date) {
		this.submit_date = submit_date;
	}

	public String getApproved_date() {
		return approved_date;
	}

	public void setApproved_date(String approved_date) {
		this.approved_date = approved_date;
	}

	public String getLast_update() {
		return last_update;
	}

	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getArtist_unicode() {
		return artist_unicode;
	}

	public void setArtist_unicode(String artist_unicode) {
		this.artist_unicode = artist_unicode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle_unicode() {
		return title_unicode;
	}

	public void setTitle_unicode(String title_unicode) {
		this.title_unicode = title_unicode;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public int getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(int creator_id) {
		this.creator_id = creator_id;
	}

	public float getBpm() {
		return bpm;
	}

	public void setBpm(float bpm) {
		this.bpm = bpm;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(int genre_id) {
		this.genre_id = genre_id;
	}

	public int getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(int language_id) {
		this.language_id = language_id;
	}

	public int getFavourite_count() {
		return favourite_count;
	}

	public void setFavourite_count(int favourite_count) {
		this.favourite_count = favourite_count;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int isStoryboard() {
		return storyboard;
	}

	public void setStoryboard(int storyboard) {
		this.storyboard = storyboard;
	}

	public int isVideo() {
		return video;
	}

	public void setVideo(int video) {
		this.video = video;
	}

	public int isDownload_unavailable() {
		return download_unavailable;
	}

	public void setDownload_unavailable(int download_unavailable) {
		this.download_unavailable = download_unavailable;
	}

	public int isAudio_unavailable() {
		return audio_unavailable;
	}

	public void setAudio_unavailable(int audio_unavailable) {
		this.audio_unavailable = audio_unavailable;
	}

	public int getPlaycount() {
		return playcount;
	}

	public void setPlaycount(int playcount) {
		this.playcount = playcount;
	}

	public int getPasscount() {
		return passcount;
	}

	public void setPasscount(int passcount) {
		this.passcount = passcount;
	}

	public Object getPacks() {
		return packs;
	}

	public void setPacks(Object packs) {
		this.packs = packs;
	}

	public int getMax_combo() {
		return max_combo;
	}

	public void setMax_combo(int max_combo) {
		this.max_combo = max_combo;
	}

	public float getDiff_aim() {
		return diff_aim;
	}

	public void setDiff_aim(float diff_aim) {
		this.diff_aim = diff_aim;
	}

	public float getDiff_speed() {
		return diff_speed;
	}

	public void setDiff_speed(float diff_speed) {
		this.diff_speed = diff_speed;
	}

	public float getDifficultyrating() {
		return difficultyrating;
	}

	public void setDifficultyrating(float difficultyrating) {
		this.difficultyrating = difficultyrating;
	}
    
    
}
