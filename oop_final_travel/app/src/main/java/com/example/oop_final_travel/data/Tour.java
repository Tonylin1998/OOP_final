package com.example.oop_final_travel.data;


public class Tour {
    // variables for tour
    public int tour_id;
    public String title;
    public int travel_code;
    public String product_key;
    public int price;
    public String start_date;
    public String end_date;
    public int lower_bound;
    public int upper_bound;
    public int count;

    // construct a new tour
    public Tour(int tour_id, String title, int travel_code, String product_key, int price, String start_date,
                String end_date, int lower_bound, int upper_bound, int count) {
        this.tour_id = tour_id;
        this.title = title;
        this.travel_code = travel_code;
        this.product_key = product_key;
        this.price = price;
        this.start_date = start_date;
        this.end_date = end_date;
        this.lower_bound = lower_bound;
        this.upper_bound = upper_bound;
        this.count = count;
    }

    // checking function of data
    @Override
    public String toString() {
        return this.tour_id + " " + this.title + " " + this.travel_code + " " + this.product_key + " " + this.price + " "
                + this.start_date + " " + this.end_date + " " + this.lower_bound + " "
                + this.upper_bound + " " + this.count;
    }
}
