package me.skiincraft.api.ousu.replay;

import java.util.Base64;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import me.skiincraft.api.ousu.OusuAPI;
import me.skiincraft.api.ousu.exceptions.InvalidBeatmapException;

public class ReplayBuilder {

	private String get = "https://osu.ppy.sh/api/get_replay";
	private OusuAPI api;
	
	private byte[] replaybyte;
	private Conteudo content;
	
	private int beatmapid;
	private String user;
	
	public ReplayBuilder(int beatmapid, String user) {
		this.user = user;
		this.beatmapid = beatmapid;
	}
	
	public void setAPI(OusuAPI api) {
		this.api = api;
	}
	
	private void connectionRequest() {
		HttpRequest bc = HttpRequest.get(get, true, "k", api.getToken(), "b", beatmapid+"", "u", user);
		
		Gson g = new Gson();
		
		bc.accept("application/json").contentType();
		String body = bc.body();
		
		try {
			api.getBeatmap(beatmapid).getArtist();
		} catch (NullPointerException e) {
				throw new InvalidBeatmapException("Este beatmap solicitado não existe", e);
		}
		
		Conteudo us = g.fromJson(body, Conteudo.class);
		content = us;
		
		replaybyte = Base64.getDecoder().decode(us.getContent());
	}
	
	private class Conteudo {
		private String content;
		private String encoding;
		
		public String getContent() {
			return content;
		}
		
		@SuppressWarnings("unused")
		public String getEncoding() {
			return encoding;
		}
		
		@SuppressWarnings("unused")
		public void setEncoding(String enco) {
			this.encoding = enco;
		}
		
		@SuppressWarnings("unused")
		public void setContent(String con) {
			this.content = con;
		}
	}
	
	public Replay build() {
		connectionRequest();
		return new Replay() {

			@Override
			public String getBase64String() {
				return content.getContent();
			}

			@Override
			public String getEncodeType() {
				return content.getEncoding();
			}

			@Override
			public byte[] getDecode() {
				return replaybyte;
			}
			

		};
	}

}