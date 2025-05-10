package me.skiincraft.api.ousu.json;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SimpleJson {
	
	private InputStream asInputStream(String resource) {
		return SimpleJson.class.getResourceAsStream(resource);
	}
	
	public JsonArray getJsonAsResource(String resource) {
		InputStreamReader reader = new InputStreamReader(asInputStream(resource));
		return JsonParser.parseReader(reader).getAsJsonArray();
	}
	
	public JsonObject getJsonObjectAsResource(String resource) {
		InputStreamReader reader = new InputStreamReader(asInputStream(resource));
		return JsonParser.parseReader(reader).getAsJsonObject();
	}
	
	public String getJsonStringAsResource(String resource) {
		InputStreamReader reader = new InputStreamReader(asInputStream(resource));
		JsonElement parser = JsonParser.parseReader(reader);
		return parser.toString();
	}

}
