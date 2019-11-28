package com.example.walkinclinic;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ReviewList  extends ArrayAdapter<Review> {

    private Activity context;
    List<Review> reviews;

    public ReviewList(Activity cont, List<Review> revs) {
        super(cont, R.layout.layout_review_list, revs);
        this.context = cont;
        this.reviews = revs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_review_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAll = (TextView) listViewItem.findViewById(R.id.textViewAll);

        Review review = reviews.get(position);
        String rate = "Rating: "+review.getRating()+"  \n";
        String comment =  "Username: "+review.getUsername();

        String all = rate+comment;

        textViewName.setText(review.getComment());
        textViewAll.setText(all);

        return listViewItem;
    }


}
