package com.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

    // Checking whether data is null or empty
    public void isEmptyOrNull(String data) throws CustomerDetailsException {
        if (data == null)
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_NULL,
                    "Data can't be null");
        if (data.length() == 0)
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_EMPTY,
                    "Data can't be empty");
    }

    // Checking the validity of date format
    public boolean isDateValid(String date) throws CustomerDetailsException {
        isEmptyOrNull(date);
        Pattern pattern = Pattern.compile(Date_Pattern);
        if (!pattern.matcher(date).matches())
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_INVALID,
                    "Enter valid date");
        return true;
    }

    // Checking the validity of customer type
    public boolean isCustomerTypeValid(String customerType) throws CustomerDetailsException {
        isEmptyOrNull(customerType);
        if (!(customerType.equals("Regular") | customerType.equals("Reward")))
            throw new CustomerDetailsException(CustomerDetailsException.ExceptionType.ENTERED_INVALID,
                    "Enter valid customer type");
        return true;
    }

    // Converting the String into LocalDate
    public LocalDate formatDate(String date) throws CustomerDetailsException {
        isDateValid(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        return LocalDate.parse(date, formatter);
    }

    // Calculating the number of days to stay at Hotel
    public int calculateNumberOfDays(String startDate, String endDate) throws CustomerDetailsException {
        LocalDate localStartDate = formatDate(startDate);
        LocalDate localEndDate = formatDate(endDate);
        return Period.between(localStartDate, localEndDate).getDays() + 1;
    }

    // Calculating the no of days of weekends in given date range
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

    // Calculating the price of stay at each hotel
    public int calculatePrice(Hotel hotel, String customerType) throws CustomerDetailsException {
        int nonWeekdays = noOfWeekends(startDate);
        int noOfWeekdays = calculateNumberOfDays(startDate, endDate) - nonWeekdays;
        if (customerType.equals("Regular"))
            return noOfWeekdays * hotel.getWeekdayRateForRegular() + nonWeekdays * hotel.getWeekendRateForRegular();
        return noOfWeekdays * hotel.getWeekdayRateForReward() + nonWeekdays * hotel.getWeekendRateForReward();
    }

    // Finding the minimum price of stay
    public int findMinimumPrice() {
        int minPrice = hotelList.stream().map(hotel -> {
            try {
                return calculatePrice(hotel, customerType);
            } catch (CustomerDetailsException e) {
                return 0;
            }
        }).min((x, y) -> x - y).get();
        return minPrice;
    }

    // Finding the cheapest hotel
    public String findTheCheapestHotel() {
        String hotelName = hotelList.stream().filter(hotel -> {
            try {
                return findMinimumPrice() == calculatePrice(hotel, customerType);
            } catch (CustomerDetailsException e) {
                return false;
            }
        }).map(hotel -> hotel.getHotelName()).collect(Collectors.joining(", "));
        return hotelName;
    }

    // Finding the best rated hotel from the list of cheapest hotels
    public String findTheCheapestBestRatedHotel() {
        Map<Integer, String> cheapestHostelWithRating = new TreeMap<>();
        cheapestHostelWithRating = hotelList.stream().filter(hotel -> {
            try {
                return findMinimumPrice() == calculatePrice(hotel, customerType);
            } catch (CustomerDetailsException e) {
                return false;
            }
        }).collect(Collectors.toMap(hotel -> hotel.getRating(), hotel -> hotel.getHotelName()));
        NavigableMap<Integer, String> cheapestHotel = new TreeMap<>(cheapestHostelWithRating);
        Map.Entry<Integer, String> lastEntry = cheapestHotel.lastEntry();
        return lastEntry.getValue();
    }

    // Finding the best rated hotel
    public String findBestRatedHotel() {
        int maxRating = hotelList.stream().map(hotel -> hotel.getRating()).max((x, y) -> x - y).get();
        String hotelName = hotelList.stream().filter(hotel -> maxRating == hotel.getRating())
                .map(hotel -> hotel.getHotelName()).findFirst().orElse(null);
        return hotelName;
    }

    // Printing the welcome message
    public void printWelcome() {
        System.out.println("Welcome to Hotel Reservation Program");
    }
}