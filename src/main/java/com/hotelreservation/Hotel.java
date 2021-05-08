package com.hotelreservation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Hotel {
    private String hotelName;
    private int rate;
    private int weekdayRate, weekendRate;
    private String startDate, endDate;

    public Hotel(String hotelName, int weekdayRate, int weekendRate, String startDate, String endDate) {
        this.hotelName = hotelName;
        this.weekdayRate = weekdayRate;
        this.weekendRate = weekendRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getWeekdayRate() {
        return weekdayRate;
    }

    public int getWeekendRate() {
        return weekendRate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getRate() {
        return rate;
    }

    public int noOfWeekends(String date) {
        int count = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        int noOfDays = calculateNumberOfDays(startDate, endDate);
        for (int day = 0; day <= noOfDays; day++) {
            if (localDate.getDayOfWeek() == DayOfWeek.SATURDAY || localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                count++;
            localDate = localDate.plusDays(1);
        }
        return count;
    }

    public int calculateNumberOfDays(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);
        return Period.between(localStartDate, localEndDate).getDays();
    }

    public int calculatePrice() {
        int nonWeekdays = noOfWeekends(startDate);
        int noOfWeekdays = calculateNumberOfDays(startDate, endDate) + 1 - nonWeekdays;
        return noOfWeekdays * weekdayRate + nonWeekdays * weekendRate;
    }
}
