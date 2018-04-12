package pinkjacket.coffeenow;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class CoffeePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Coffee> mCoffees;

    public CoffeePagerAdapter(FragmentManager fm, ArrayList<Coffee> coffees){
        super(fm);
        mCoffees = coffees;
    }

    @Override
    public Fragment getItem(int position){
        return CoffeeDetailFragment.newInstance(mCoffees.get(position));
    }

    @Override
    public int getCount(){
        return mCoffees.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return mCoffees.get(position).getName();
    }
}
