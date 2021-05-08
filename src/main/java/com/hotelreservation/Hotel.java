package com.hotelreservation;

public class Hotel {
    private String hotelName;
    private int weekdayRateForRegular, weekendRateForRegular;
    private int weekdayRateForReward, weekendRateForReward;
    private int rating;

    public Hotel(String hotelName, int weekdayRateForRegular, int weekendRateForRegular, int weekdayRateForReward,
                 int weekendRateForReward, int rating) {
        this.hotelName = hotelName;
        this.weekdayRateForRegular = weekdayRateForRegular;
        this.weekendRateForRegular = weekendRateForRegular;
        this.weekdayRateForReward = weekdayRateForReward;
        this.weekendRateForReward = weekendRateForReward;
        this.rating = rating;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getWeekdayRateForRegular() {
        return weekdayRateForRegular;
    }

    public int getWeekendRateForRegular() {
        return weekendRateForRegular;
    }

    public int getWeekdayRateForReward() {
        return weekdayRateForReward;
    }

    public int getWeekendRateForReward() {
        return weekendRateForReward;
    }

    public int getRating() {
        return rating;
    }
}
