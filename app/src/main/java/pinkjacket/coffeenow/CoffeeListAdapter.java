package pinkjacket.coffeenow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoffeeListAdapter extends RecyclerView.Adapter<CoffeeListAdapter.CoffeeViewHolder> {
    private ArrayList<Coffee> mCoffees = new ArrayList<>();
    private Context mContext;

    public CoffeeListAdapter(Context context, ArrayList<Coffee> coffees){
        mContext = context;
        mCoffees = coffees;
    }

    @Override
    public CoffeeListAdapter.CoffeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_list_item, parent, false);
        CoffeeViewHolder viewHolder = new CoffeeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CoffeeListAdapter.CoffeeViewHolder holder, int position){
        holder.bindCoffee(mCoffees.get(position));
    }

    @Override
    public int getItemCount(){
        return mCoffees.size();
    }

    public class CoffeeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.coffeeImageView) ImageView mCoffeeImageView;
        @BindView(R.id.coffeeNameView) TextView mCoffeeNameView;

        private Context mContext;

        public CoffeeViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, CoffeeDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("coffees", Parcels.wrap(mCoffees));
            mContext.startActivity(intent);
        }

        public void bindCoffee(Coffee coffee){
            Picasso.with(mContext).load(coffee.getImageUrl()).into(mCoffeeImageView);
            mCoffeeNameView.setText(coffee.getName());

        }
    }
}
