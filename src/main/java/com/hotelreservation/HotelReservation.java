package com.hotelreservation;

import java.util.*;

public class HotelReservation {
    public List<Hotel> hotelList = new ArrayList<>();

    public void addHotelToHotelReservation(Hotel hotel) {
        hotelList.add(hotel);
    }

    public int findMinimumPrice() {
        int minPrice = hotelList.get(0).calculatePrice();
        for (Hotel hotel : hotelList) {
            int price = hotel.calculatePrice();
            if (price < minPrice)
                minPrice = price;
        }
        return minPrice;
    }

    public String findBestRatedHotel() {
        int maxRating = hotelList.get(0).getRating();
        for (Hotel hotel : hotelList) {
            int rating = hotel.getRating();
            if (rating > maxRating)
                maxRating = rating;
        }
        for (Hotel hotel : hotelList) {
            if (maxRating == hotel.getRating())
                return hotel.getHotelName();
        }
        return null;
    }

    public String findTheCheapestBestRatedHotel() {
        NavigableMap<Integer, String> cheapestHostelWithRating = new TreeMap<>();
        for (Hotel hotel : hotelList) {
            if (findMinimumPrice() == hotel.calculatePrice())
                cheapestHostelWithRating.put(hotel.getRating(), hotel.getHotelName());
        }
        Map.Entry<Integer, String> lastEntry = cheapestHostelWithRating.lastEntry();
        return lastEntry.getValue();
    }

    public void printWelcome() {
        System.out.println("Welcome to Hotel Reservation Program");
    }
}
