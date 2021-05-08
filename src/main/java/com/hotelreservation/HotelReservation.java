package com.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HotelReservation {
    private String startDate, endDate;
    public List<Hotel> hotelList = new ArrayList<>();

    public HotelReservation(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addHotelToHotelReservation(Hotel hotel) {
        hotelList.add(hotel);
    }

    public LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        return LocalDate.parse(date, formatter);
    }

    public int calculateNumberOfDays(String startDate, String endDate) {
        LocalDate localStartDate = formatDate(startDate);
        LocalDate localEndDate = formatDate(endDate);
        return Period.between(localStartDate, localEndDate).getDays() + 1;
    }

    public int noOfWeekends(String date) {
        int count = 0;
        LocalDate localDate = formatDate(date);
        int noOfDays = calculateNumberOfDays(startDate, endDate);
        for (int day = 0; day < noOfDays; day++) {
            if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                count++;
            localDate = localDate.plusDays(1);
        }
        return count;
    }

    public int calculatePrice(Hotel hotel) {
        int nonWeekdays = noOfWeekends(startDate);
        int noOfWeekdays = calculateNumberOfDays(startDate, endDate) - nonWeekdays;
        return noOfWeekdays * hotel.getWeekdayRateForRegular() + nonWeekdays * hotel.getWeekendRateForRegular();
    }

    public int findMinimumPrice() {
        int minPrice = calculatePrice(hotelList.get(0));
        for (Hotel hotel : hotelList) {
            int price = calculatePrice(hotel);
            if (price < minPrice)
                minPrice = price;
        }
        return minPrice;
    }

    public String findTheCheapestHotel() {
        String hotelName = "";
        for (Hotel hotel : hotelList) {
            if (findMinimumPrice() == calculatePrice(hotel))
                hotelName = hotelName + hotel.getHotelName() + " ";
        }
        return hotelName;
    }

    public String findTheCheapestBestRatedHotel() {
        NavigableMap<Integer, String> cheapestHostelWithRating = new TreeMap<>();
        for (Hotel hotel : hotelList) {
            if (findMinimumPrice() == calculatePrice(hotel))
                cheapestHostelWithRating.put(hotel.getRating(), hotel.getHotelName());
        }
        Map.Entry<Integer, String> lastEntry = cheapestHostelWithRating.lastEntry();
        return lastEntry.getValue();
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

    public void printWelcome() {
        System.out.println("Welcome to Hotel Reservation Program");
    }
}
