package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.oop_final_travel.R;
import com.example.oop_final_travel.Utils;
import com.example.oop_final_travel.data.TourList;

import java.text.ParseException;
import java.util.ArrayList;

import static com.example.oop_final_travel.data.TourList.place_to_code;

public class SearchTourActivity extends AppCompatActivity {
    // Variable for user inputs
    private EditText input_place, input_depart_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tour);

        input_place = (EditText) findViewById(R.id.place);
        input_depart_date = (EditText) findViewById(R.id.depart_date);

        // click listener on search tour
        Button cancel = (Button) findViewById(R.id.search_tour_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTour();
            }
        });
    }

    /**
     * search tour
     */
    private void searchTour(){
        String place, depart_date;

        // check user input's format
        place = input_place.getText().toString();
        if(!place_to_code.containsKey(place)){
            TextView search_tour_warning = (TextView)findViewById(R.id.search_tour_warning);
            search_tour_warning.setText(R.string.input_place_error);
            return;
        }

        try {
            depart_date = Utils.parseDate(input_depart_date.getText().toString());
        } catch (ParseException e) {
            TextView search_tour_warning = (TextView)findViewById(R.id.search_tour_warning);
            search_tour_warning.setText(R.string.date_format_error);
            return;
        }

        // load data from TourList
        ArrayList<SearchResult> result = new ArrayList<>();
        int travel_code = place_to_code.get(place);
        for(int i = 0 ; i < TourList.tours.size() ; ++i){
            if(TourList.tours.get(i).travel_code == travel_code && depart_date.compareTo(TourList.tours.get(i).start_date) < 0){
                result.add(new SearchResult(i, TourList.tours.get(i).title, TourList.tours.get(i).start_date,
                        TourList.tours.get(i).end_date, TourList.tours.get(i).upper_bound, TourList.tours.get(i).price));
            }
        }
        if (result.size() == 0) {
            TextView search_tour_warning = (TextView)findViewById(R.id.search_tour_warning);
            search_tour_warning.setText(R.string.data_no_found);
            return;
        }

        // pass the result to SSearchTourResultActivity
        Intent intent = new Intent(SearchTourActivity.this, SearchTourResultActivity.class);
        intent.putExtra("Tour_list", result);
        startActivity(intent);


    }
}
