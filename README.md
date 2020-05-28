# OusuAPI - Osu!Api
A Simple osu!API Wrapper

## My Objective
`With this improvised "API", I intend to use it in my projects. I would not release it in open source. I decided to leave the source code released because I had trouble finding a Java Wrapper that worked correctly. I hope you enjoy the basic functions of this API`

Sorry my english :3

## Dependencias
[http-request-3.0](https://github.com/kevinsawicki/http-request) - KevinSawicki

Jsoup - idk

[Gson-2.8.2](https://github.com/google/gson) - Google

Utilizei somente essas duas dependencias.

## Simple Use
I made it pretty simple.

```java
 //If desired, create a static field for easy access. (This is not necessary)
	private static OusuAPI api;
	@Getters;
 
	public static Main(String[] args) {
		//Enter your Osu API token
		api = new OusuAPI("API_KEY");
		
		//Getting information from a player.
		User user = api.getUser("skiincraft", Gamemode.Standard);
		
		//Print the information obtained
		System.out.println("Username: " + user.getUserName());
		System.out.println("Ranking: #" + user.getRanking());
		System.out.println("PP: " + user.getPP());
	}
```
In the case of beatmaps do
```java
[....]
		//Getting information from a beatmap.
		Beatmap beatmap = api.getBeatmap(1018869);
		    // List<Beatmap> beatmapset = api.getBeatmapSet(474376); in the case of beatmapset
		
		//Print the information obtained
		System.out.println("Title: " + beatmap.getTitle());
		System.out.println("Artist: #" + beatmap.getArtist());
		System.out.println("Creator: " + beatmap.getCreator());
		System.out.println("BMP: " + beatmap.getBPM());
```

This project is not complete, I intend to update whenever I can.

Thanks for reading me :D
