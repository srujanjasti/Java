package com.yahooweather;

import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.http.HttpRequest;
import org.apache.http.impl.client.CloseableHttpClient;


@Path("Weather")
public class YahooWeather {

	@GET
	@Produces("MediaType.APPLICATION_JSON")
	
	public static void doHttpGet() throws Exception {
		
		final String appId = "Ay5yLw3e";
		final String consumerKey= "dj0yJmk9VlNSUnBhVkhVbVBGJnM9Y29uc3VtZXJzZWNyZXQmc3Y9MCZ4PWYz";
		final String consumerSecret= "6c6a0c834e576d7a7b5af6f102acaeb5d1d4a410";
		final String url= "https://weather-ydn-yql.media.yahoo.com/forecastrss";
		
		long timestamp = new Date().getTime() / 1000;
		byte[] nonce = new byte[32];
		Random rand = new Random();
        rand.nextBytes(nonce);
        String oauthNonce = new String(nonce).replaceAll("\\W", "");
		
		List<String> parameters = new ArrayList<>();
        parameters.add("oauth_consumer_key=" + consumerKey);
        parameters.add("oauth_nonce=" + oauthNonce);
        parameters.add("oauth_signature_method=HMAC-SHA1");
        parameters.add("oauth_timestamp=" + timestamp);
        parameters.add("oauth_version=1.0");
        parameters.add("format=json");
		
        String auth = "OAuth " + 
        		"oauth_consumer_key=\"" + consumerKey + "\", " + "" + 
        		"oauth_nonce=\"" + oauthNonce + "\", " +
                "oauth_timestamp=\"" + timestamp + "\", " + 
        		"oauth_signature_method=\"HMAC-SHA1\", " + 
                "oauth_signature=\"" + signature + "\", " + 
        		"oauth_version=\"1.0\"";
        
        CloseableHttpClient client = HttpClient.createDefault();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url + "?location=sunnyvale,ca&format=json"))
            .header("Authorization", auth)
            .header("Yahoo-App-Id", appId)
            .header("MediaType", "application/json")
            .build();
        
	}
	
}
