package com.example.oop_final_travel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.oop_final_travel.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SearchTourResultActivity extends AppCompatActivity {
    ArrayList<SearchResult> results_sort_by_date, results_sort_by_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tour_result);

        results_sort_by_date = (ArrayList<SearchResult>) getIntent().getSerializableExtra("Tour_list");
        results_sort_by_price = new ArrayList<SearchResult>(results_sort_by_date);
        Collections.sort(results_sort_by_price);

        ListView resultListView = (ListView) findViewById(R.id.search_tour_list);
        SearchResultAdapter adapter = new SearchResultAdapter(this, results_sort_by_date);
        resultListView.setAdapter(adapter);

        Button sort_by_date = (Button) findViewById(R.id.sort_by_date);
        sort_by_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_by_date_view();
            }
        });
        Button sort_by_price = (Button) findViewById(R.id.sort_by_price);
        sort_by_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort_by_price_view();
            }
        });
    }
    private void sort_by_date_view(){
        ListView resultListView = (ListView) findViewById(R.id.search_tour_list);
        SearchResultAdapter adapter = new SearchResultAdapter(this, results_sort_by_date);
        resultListView.setAdapter(adapter);
    }
    private void sort_by_price_view(){
        ListView resultListView = (ListView) findViewById(R.id.search_tour_list);
        SearchResultAdapter adapter = new SearchResultAdapter(this, results_sort_by_price);
        resultListView.setAdapter(adapter);
    }
}
