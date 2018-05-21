package pinkjacket.coffeenow;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CoffeeDetailFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.coffeeDetailImageView) ImageView mImageLabel;
    @BindView(R.id.coffeeNameTextView) TextView mNameLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    private Coffee mCoffee;

    public static CoffeeDetailFragment newInstance(Coffee coffee) {
        CoffeeDetailFragment coffeeDetailFragment = new CoffeeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("coffee", Parcels.wrap(coffee));
        coffeeDetailFragment.setArguments(args);
        return coffeeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mCoffee = Parcels.unwrap(getArguments().getParcelable("coffee"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coffee_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mCoffee.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mImageLabel);

        mNameLabel.setText(mCoffee.getName());
        mPhoneLabel.setText(mCoffee.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(",", mCoffee.getAddress()));
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v){
        if (v == mWebsiteLabel){
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mCoffee.getWebsite()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel){
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mCoffee.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel){
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mCoffee.getLatitude()
                            + "," + mCoffee.getLongitude()
                    + "?q=(" + mCoffee.getName() + ")"));
            startActivity(mapIntent);
        }
    }

}
