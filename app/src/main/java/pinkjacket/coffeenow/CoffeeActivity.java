package pinkjacket.coffeenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CoffeeActivity extends AppCompatActivity {
    @BindView(R.id.zipView) TextView mZipView;
    public static final String TAG = CoffeeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        mZipView.setText("Zip code: " + location);
        getCoffee(location);
    }

    private void getCoffee(String location){
        final YelpService yelpService = new YelpService();
        yelpService.findCoffee(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
