package pinkjacket.coffeenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CoffeeActivity extends AppCompatActivity {
    @BindView(R.id.zipView) TextView mZipView;
    @BindView(R.id.listView) ListView mListView;
    public ArrayList<Coffee> coffees = new ArrayList<>();
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
            public void onResponse(Call call, Response response) {
                coffees = yelpService.processResults(response);
                CoffeeActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] coffeeNames = new String[coffees.size()];
                        for (int i = 0; i < coffeeNames.length; i++) {
                            coffeeNames[i] = coffees.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(CoffeeActivity.this, android.R.layout.simple_list_item_1, coffeeNames);
                        mListView.setAdapter(adapter);

                        for (Coffee coffee : coffees) {
                            Log.d(TAG, "Name: " + coffee.getName());
                            Log.d(TAG, "Phone: " + coffee.getPhone());
                            Log.d(TAG, "Website: " + coffee.getWebsite());
                            Log.d(TAG, "Image url: " + coffee.getImageUrl());
                            Log.d(TAG, "Address : " + coffee.getAddress());
                        }
                    }

                });
            }
        });
    }
}