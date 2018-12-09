# song-recommender

A application that suggest a playlist based on the temperature of a location

## Structure

The service is a Spring Boot application with two REST endpoints: one that receives a city name as parameter and the other receives a geographic coordinate (latitude and longitude).

It integrates with the OpenWeather API in order to get the temperature from the location and with the Spotify API in order to find a playlist and its songs.

The integration is made with Spring Cloud Open Feign. In case any request for any of the external services (OpenWeather and Spotify) fails, a fallback is triggered. Therefore a playlist is still returned by the song-recommender service even if some of the integration points are down.

The rules to generate a playlist are stored in AWS S3 as a JSON file:

```json
[
  {
    "maxTemperature": 10.0,
    "genre": "classical"
  },
  {
    "minTemperature": 10.0,
    "maxTemperature": 15.0,
    "genre": "rock"
  },
  {
    "minTemperature": 15.0,
    "maxTemperature": 30.0,
    "genre": "pop"
  },
  {
    "minTemperature": 30.0,
    "genre": "party"
  }
]
```

The file above determines what kind of playlist is going to be searched on Spotify based on the temperature. The temperature interval is inclusive for the minTemperature and exclusive for the maxTemperature. That means that, for instance, we will build a rock playlist if:

```
10.0 >= temperature < 15.0
```

We stored the rules in S3 because it's very cheap and we are able to potentially change the rules in execution time, that is, we don't need to deploy the application again if we want to change the rules. If the integration with S3 fails, we will build a pop playlist by default.

## Running

Make sure you put the keys into src/main/resources/application.properties:

```
open-weather.api.key=YOUR_OPEN_WEATHER_KEY
spotify.api.client.id=YOUR_SPOTIFY_CLIENT_ID
spotify.api.client.secret=YOUR_SPOTIFY_SECRET_CLIENT_ID
cloud.aws.credentials.accessKey=YOUR_AWS_ACCESS_KEY
cloud.aws.credentials.secretKey=YOUR_AWS_CREDENTIALS_SECRET_KEY
```
### Running with Docker
Build the docker image:

```console
./gradlew build docker
```

Run the image. Application runs on port 8081:

```console
docker run -p 8081:8081 -t com.gmail.jvbrandaom/song-recommender
```
### Running without docker

```console
./gradlew bootRun
```

## Sending Requests

Once application is up an running, you can send a GET request to:

- Get playlist for a city
```
http://localhost:8081/playlist/city/Campinas
```
- Get playlist for a geographic coordinate
```
http://localhost:8081/playlist/latitude/50/longitude/100
```

The application will respond a JSON like:

```json
[
  {
    "name": "Ecce venit / Psalm 94",
    "artist": "Anonymous & Kadri Hunt & Mikk Uleoja & Vox Clamantis & Jaan-Eik Tulve"
  },
  {
    "name": "Beata Viscera",
    "artist": "Pérotin & The Hilliard Ensemble"
  },
  {
    "name": "A Worcester Ladymass: Sanctus",
    "artist": "Traditional & Trio Mediæval"
  },
  {
    "name": "Léonin / Pérotin: Magnus Liber / Easter: Cristus resurgens - Dicant nunc (à 3) (Processional Antiphon)",
    "artist": "Léonin & Pérotin & Orlando Consort & Robert Harre-Jones & Stephen Charlesworth & Charles Pott & Michael McCarthy & Julian Clarkson & Westminster Cathedral Choir & James O'Donnell"
  },
  {
    "name": "Aspire refus contre doulce priere",
    "artist": "Jacopo da Bologna & Munir Beken & August Denhard"
  },
  {
    "name": "Tout par compas suy composés",
    "artist": "Baude Cordier & Ferrara Ensemble & Crawford Young"
  },
  {
    "name": "Es fügt sich: I, II, III",
    "artist": "Oswald von Wolkenstein & Andreas Scholl & Shields of Harmony & Crawford Young"
  },
  {
    "name": "Antiphon, O quam mirabilis est",
    "artist": "Hildegard von Bingen & Anonymous 4"
  }
]
```
