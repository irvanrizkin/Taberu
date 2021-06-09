package com.example.taberu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
  private ArrayList<Review> reviews;

  public ReviewAdapter(ArrayList<Review> reviews) {
    this.reviews = reviews;
  }

  @NonNull
  @Override
  public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.card_restaurant_detail,
            parent,
            false);
    return new ReviewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
    Review review = reviews.get(position);
    holder.reviewLabel.setText(review.getReview());
    holder.dateLabel.setText(review.getDate());
    holder.nameLabel.setText(review.getName());
  }

  @Override
  public int getItemCount() {
    return reviews.size();
  }

  public class ReviewHolder extends RecyclerView.ViewHolder {
    TextView reviewLabel, dateLabel, nameLabel;

    public ReviewHolder(@NonNull View itemView) {
      super(itemView);
      this.reviewLabel = itemView.findViewById(R.id.cardhome_edittext_review);
      this.dateLabel = itemView.findViewById(R.id.cardhome_edittext_date);
      this.nameLabel = itemView.findViewById(R.id.cardhome_edittext_name);
    }
  }
}
