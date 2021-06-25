# OusuAPI - Osu!Api
A Simple osu!API Wrapper

## My Objective
`With this improvised "API", I intend to use it in my projects. I would not release it in open source. I decided to leave the source code released because I had trouble finding a Java Wrapper that worked correctly. I hope you enjoy the basic functions of this API`

Sorry my english :3

## Dependencias
The dependencies are inside build.gradle

## Add your dependencies!
[![](https://jitpack.io/v/Cristian-Sknz/Ousu-Api.svg)](https://jitpack.io/#Cristian-Sknz/Ousu-Api)
* Gradle

```groovy
repositories {
     maven { url 'https://jitpack.io' }
}

dependencies {
     compile 'com.github.Cristian-Sknz:Ousu-Api:v2.0.1.1'
}
```
* Maven
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
}

<dependency>
    <groupId>com.github.Cristian-Sknz</groupId>
    <artifactId>Ousu-Api</artifactId>
    <version>c</version>
</dependency>
}
```
## Simple Use
I made it pretty simple.

```java
OusuAPI api = new OusuAPI("API_KEY");

// Requesting a player
Request<User> userRequest = api.getUser("skiincraft", Gamemode.Standard);

/* Always remember that a "Request<?>" Class
 * will always make a new request. 
 * If the request has already been made, check using:
 */
userRequest.wasRequested(); // if requested it will return "true"

//Getting information from a player.
User user = userRequest.get();

//Print the information obtained
System.out.println("Username: " + user.getUsername());
System.out.println("Ranking: #" + user.getRanking());
System.out.println("PP: " + user.getPP());
```
In the case of beatmaps do
```java
[....]
// Requesting a beatmap
Request<Beatmap> beatmapRequest = api.getBeatmap(1018869); // beatmapId

// Getting information from a beatmap.
Beatmap beatmap = beatmapRequest.get();
// BeatmapSet beatmapset = api.getBeatmapSet(474376).get(); in the case of beatmapset

//Print the information obtained
System.out.println("Title: " + beatmap.getTitle());
System.out.println("Artist: #" + beatmap.getArtist());
System.out.println("Creator: " + beatmap.getCreator());
System.out.println("BMP: " + beatmap.getBPM());
```

This project is not complete, I intend to update whenever I can.

Thanks for reading me :D
