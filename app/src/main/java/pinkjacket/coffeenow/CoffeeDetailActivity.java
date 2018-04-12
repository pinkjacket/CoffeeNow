package pinkjacket.coffeenow;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoffeeDetailActivity extends AppCompatActivity {
    @BindView(R.id.viewPager) ViewPager mViewPager;
    private CoffeePagerAdapter adapterViewPager;
    ArrayList<Coffee> mCoffees = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_detail);
        ButterKnife.bind(this);

        mCoffees = Parcels.unwrap(getIntent().getParcelableExtra("coffees"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new CoffeePagerAdapter(getSupportFragmentManager(), mCoffees);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
