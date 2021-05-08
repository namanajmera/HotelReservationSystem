package com.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class HotelReservation {
    // For simplicity, we have restricted our interest to the years 1900 – 2999.
    private final static String Date_Pattern = "^(29Feb(2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26]))))$"
            + "|^((0[1-9]|1[0-9]|2[0-8])Feb((19|2[0-9])[0-9]{2}))$"
            + "|^((0[1-9]|[12][0-9]|3[01])(Jan|Mar|May|Jul|Aug|Oct|Dec)((19|2[0-9])[0-9]{2}))$"
            + "|^((0[1-9]|[12][0-9]|30)(Apr|Jun|Sep|Nov)((19|2[0-9])[0-9]{2}))$";

    private String customerType;
    private String startDate, endDate;
    public List<Hotel> hotelList = new ArrayList<>();

    public HotelReservation(String customerType, String startDate, String endDate) {
        this.customerType = customerType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void addHotelToHotelReservation(Hotel hotel) {
        hotelList.add(hotel);
    }

    public void isEmptyOrNull(String data) throws CustomerDetailsException {
        if (data == null)
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_NULL,
                    "Data can't be null");
        if (data.length() == 0)
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_EMPTY,
                    "Data can't be empty");
    }

    public boolean isDateValid(String date) throws CustomerDetailsException {
        isEmptyOrNull(date);
        Pattern pattern = Pattern.compile(Date_Pattern);
        if (!pattern.matcher(date).matches())
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_INVALID,
                    "Enter valid date");
        return true;
    }

    public boolean isCustomerTypeValid(String customerType) throws CustomerDetailsException {
        isEmptyOrNull(customerType);
        if (!(customerType.equals("Regular") | customerType.equals("Reward")))
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_INVALID,
                    "Enter valid customer type");
        return true;
    }

    public LocalDate formatDate(String date) throws CustomerDetailsException {
        isDateValid(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        return LocalDate.parse(date, formatter);
    }

    public int calculateNumberOfDays(String startDate, String endDate) throws CustomerDetailsException {
        LocalDate localStartDate = formatDate(startDate);
        LocalDate localEndDate = formatDate(endDate);
        return Period.between(localStartDate, localEndDate).getDays() + 1;
    }

    public int noOfWeekends(String date) throws CustomerDetailsException {
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

    public int calculatePrice(Hotel hotel, String customerType) throws CustomerDetailsException {
        int nonWeekdays = noOfWeekends(startDate);
        int noOfWeekdays = calculateNumberOfDays(startDate, endDate) - nonWeekdays;
        if (customerType.equals("Regular"))
            return noOfWeekdays * hotel.getWeekdayRateForRegular() + nonWeekdays * hotel.getWeekendRateForRegular();
        return noOfWeekdays * hotel.getWeekdayRateForReward() + nonWeekdays * hotel.getWeekendRateForReward();
    }

    public int findMinimumPrice() throws CustomerDetailsException {
        int minPrice = calculatePrice(hotelList.get(0), customerType);
        for (Hotel hotel : hotelList) {
            int price = calculatePrice(hotel, customerType);
            if (price < minPrice)
                minPrice = price;
        }
        return minPrice;
    }

    public String findTheCheapestHotel() throws CustomerDetailsException {
        String hotelName = "";
        for (Hotel hotel : hotelList) {
            if (findMinimumPrice() == calculatePrice(hotel, customerType))
                hotelName = hotelName + hotel.getHotelName() + " ";
        }
        return hotelName;
    }

    public String findTheCheapestBestRatedHotel() throws CustomerDetailsException {
        NavigableMap<Integer, String> cheapestHostelWithRating = new TreeMap<>();
        for (Hotel hotel : hotelList) {
            if (findMinimumPrice() == calculatePrice(hotel, customerType))
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