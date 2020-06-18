package com.example.oop_final_travel.activities;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oop_final_travel.R;

import java.util.ArrayList;

public class SearchResultAdapter extends ArrayAdapter<SearchResult> {
    private Activity c;

    public SearchResultAdapter(Activity content, ArrayList<SearchResult> result) {
        super(content, 0, result);
        this.c = content;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.search_tour_item, parent, false
            );
        }

        SearchResult tour = getItem(position);

        //TextView tour_id_view = (TextView) listItemView.findViewById(R.id.tour_id);
        //tour_id_view.setText(String.valueOf(tour.getTour_id()));
        final int tour_id = tour.getTour_id();
        final int currentPosition = position;



        TextView title_view = (TextView) listItemView.findViewById(R.id.title);
        title_view.setText(tour.getTitle());

        TextView date_view = (TextView) listItemView.findViewById(R.id.date);
        String date = tour.getStart_date() + "~\n" + tour.getEnd_date();
        date_view.setText(date);

        TextView price_view = (TextView) listItemView.findViewById(R.id.price);
        String price = "$" + tour.getPrice();
        price_view.setText(price);

        TextView limit_view = (TextView) listItemView.findViewById(R.id.limit);
        String limit = "人數上限: " + tour.getUpper_bound() + "人";
        limit_view.setText(limit);

        Log.d("gggg", "onClick: "+limit);

        Button order = (Button) listItemView.findViewById(R.id.order_button1);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("press", "onClick: "+tour_id);
                Toast.makeText(c, "item at position " + currentPosition, Toast.LENGTH_SHORT);
                Intent OrderIntent = new Intent(c, OrderActivity.class);
                OrderIntent.putExtra("tour_id", tour_id);
                c.startActivity(OrderIntent);
            }
        });

        return listItemView;
    }
}
