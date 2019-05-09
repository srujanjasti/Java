package REST.WeatherApplication;

import javax.ws.rs.GET;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/** Example resource class hosted at the URI path "/myresource"
 */
@Path("/Weather")
public class Weather() {
    
    
    @GET 
    @Produces("MediaType.APPLICATION_JSON")
    public static void doHttpGet() {

        String url = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/348735?apikey=" + ApiKey.getApiKey();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse resp = null;
        try {
            resp = client.execute(get);
            HttpEntity entity = resp.getEntity();
            System.out.println("Json response");
            System.out.println(EntityUtils.toString(entity));

        }

        catch (Exception e ){
            System.out.println("Unknown error: ");
            e.printStackTrace();
        }
    }
}
}

