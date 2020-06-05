package com.example.oop_final_travel.activities;


import java.io.Serializable;


public class SearchResult implements Comparable<SearchResult>, Serializable {

    private int tour_id;
    private String title;
    private String start_date;
    private String end_date;
    private int upper_bound;
    private int price;

    public SearchResult(int tour_id, String title, String start_date, String end_date, int upper_bound, int price) {
        this.tour_id = tour_id;
        this.title = title;
        this.start_date = start_date;
        this.end_date = end_date;
        this.upper_bound = upper_bound;
        this.price = price;
    }

    public int getTour_id() {
        return tour_id;
    }

    public String getTitle() {
        return title;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getUpper_bound() {
        return upper_bound;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int compareTo(SearchResult o) {
        if (price > o.price) return 1;
        else if (price == o.price) return 0;
        else return -1;
    }
    /*

    @Override
    public String toString() {
        return star + " " + price;
    }
    */
}


