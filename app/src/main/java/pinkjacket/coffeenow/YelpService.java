package pinkjacket.coffeenow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpService {
    public static void findCoffee(String location, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.YELP_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.YELP_LOCATION_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.YELP_TOKEN)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Coffee> processResults(Response response){
        ArrayList<Coffee> coffees = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject yelpJSON = new JSONObject(jsonData);
            JSONArray businessesJSON = yelpJSON.getJSONArray("businesses");
            for (int i = 0; i < businessesJSON.length(); i++){
                JSONObject coffeeJSON = businessesJSON.getJSONObject(i);
                String name = coffeeJSON.getString("name");
                String phone = coffeeJSON.optString("display_phone", "No phone");
                String website = coffeeJSON.getString("url");
                String imageUrl = coffeeJSON.getString("image_url");
                double latitude = (double) coffeeJSON.getJSONObject("coordinates").getDouble("latitude");
                double longitude = (double) coffeeJSON.getJSONObject("coordinates").getDouble("longitude");

                ArrayList<String> address = new ArrayList<>();
                JSONArray addressJSON = coffeeJSON.getJSONObject("location")
                        .getJSONArray("display_address");
                for (int j = 0; j < addressJSON.length(); j++){
                    address.add(addressJSON.get(j).toString());
                }
                Coffee coffee = new Coffee(name, phone, website, imageUrl, address, latitude, longitude);
                coffees.add(coffee);
            }
        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }
        return coffees;
    }
}
