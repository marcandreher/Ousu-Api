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
		JsonArray parser = new JsonParser().parse(reader).getAsJsonArray();
		return parser;
	}
	
	public JsonObject getJsonObjectAsResource(String resource) {
		InputStreamReader reader = new InputStreamReader(asInputStream(resource));
		JsonObject parser = new JsonParser().parse(reader).getAsJsonObject();
		return parser;
	}
	
	public String getJsonStringAsResource(String resource) {
		InputStreamReader reader = new InputStreamReader(asInputStream(resource));
		JsonElement parser = new JsonParser().parse(reader);
		return parser.toString();
	}

}
