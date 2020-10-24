package me.skiincraft.api.ousu.entity.objects;

import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ProfileEvents {

	private final String displayHtml;
	private final int beatmapid;
	private final int beatmapSetID;
	
	private final String eventDate;
	private final int epicfactor;
	
	public ProfileEvents(String displayHtml, int beatmapid, int beatmapSetID, String eventDate, int epicfactor) {
		this.displayHtml = displayHtml;
		this.beatmapid = beatmapid;
		this.beatmapSetID = beatmapSetID;
		this.eventDate = eventDate;
		this.epicfactor = epicfactor;
	}

	public String getDisplayHtml() {
		return displayHtml;
	}

	public int getBeatmapid() {
		return beatmapid;
	}

	public int getBeatmapSetID() {
		return beatmapSetID;
	}

	public String getEventDate() {
		return eventDate;
	}
	
	public EventDisplay getEventDisplay() {
		return new EventDisplay(displayHtml);
	}

	public int getEpicfactor() {
		return epicfactor;
	}
	
	public static class EventDisplay {
		
		public final String eventhtml;
		private final Document parse;
		
		public EventDisplay(String eventhtml) {
			this.eventhtml = eventhtml;
			this.parse = Jsoup.parse(eventhtml);
		}
		
		public String getUsername() {
			String[] textsplit = parse.text().split(" ");
			StringBuilder b = new StringBuilder();
			for (String t :textsplit) {
				if (t.contains("achieved")) {
					break;
				}
				b.append(t);
			}
			
			return b.toString();
		}
		
		public Integer getUserID() {
			Elements us = parse.select("b").select("a");
			return Integer.valueOf(us.attr("href").replaceAll("\\D+", ""));
		}
		
		public int getBeatmapID() {
			String htmlid = parse.select("a").get(1).attr("href").substring(3);
			int val = 0;
			if (htmlid.contains("?")) {
				char[] chars = htmlid.toCharArray();
				for (char c : chars) {
					if (c == '?') {
						break;
					}
					val++;
				}
			}
			return Integer.parseInt(htmlid.substring(0, val));
		}
		
		public String getBeatmapDisplay() {
			List<String> textsplit = Arrays.asList(parse.text().split(" "));
			int acho = 1;
			StringBuilder map = new StringBuilder();
			for (String t :textsplit) {
				if (!t.equals("on")) {
					acho++;
					continue;
				}
				for (int i = acho; i < textsplit.size();i++) {
					map.append(textsplit.get(i)).append(" ");
				}
				return map.toString();
				
			}
			return null;
			//select("a").get(1).text()
		}
		
		public String getRankingPosition() {
			String[] textsplit = parse.text().split(" ");
			for (String t : textsplit) {
				if (t.contains("#")) return t.replace("#", "");
			}
			return null;
		}
		
		public String getRanking() {
			Elements img = parse.select("img");
			return img.get(0)
					.attr("src")
					.substring(8)
					.substring(0, 2).replace("_", "");
		}
		
		public String getAchievedMessage() {
			return parse.text();
		}
	}
	
	

}
