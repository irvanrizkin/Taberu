package com.example.taberu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantHolder> {
  private ArrayList<Restaurant> restaurants;
  private RecyclerListener recyclerListener;

  public RestaurantAdapter(ArrayList<Restaurant> restaurants, RecyclerListener recyclerListener) {
    this.restaurants = restaurants;
    this.recyclerListener = recyclerListener;
  }

  @NonNull
  @Override
  public RestaurantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.card_home,
            parent,
            false);
    return new RestaurantHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull RestaurantHolder holder, int position) {
    Restaurant restaurant = restaurants.get(position);
    holder.titleItem.setText(restaurant.getName());
    holder.locationItem.setText(restaurant.getCity());
    holder.descriptionItem.setText(restaurant.getDescription());
    holder.ratingItem.setRating(restaurant.getRating());

  }

  @Override
  public int getItemCount() {
    return restaurants.size();
  }

  public interface RecyclerListener{
    void onClick(View v, int position);
  }

  public class RestaurantHolder extends RecyclerView.ViewHolder {
    TextView titleItem, locationItem, descriptionItem;
    RatingBar ratingItem;

    public RestaurantHolder(@NonNull View itemView) {
      super(itemView);
      titleItem = itemView.findViewById(R.id.cardhome_edittext_title);
      locationItem = itemView.findViewById(R.id.cardhome_edittext_location);
      descriptionItem = itemView.findViewById(R.id.cardhome_edittext_desc);
      ratingItem = itemView.findViewById(R.id.cardhome_ratingbar_rating);
      itemView.setOnClickListener(clickListener);
    }

    public View.OnClickListener clickListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        recyclerListener.onClick(itemView, getAdapterPosition());
      }
    };
  }
}
