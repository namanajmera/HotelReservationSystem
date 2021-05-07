package com.hotelreservation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Hotel {
    private String hotelName;
    private int rate;
    private int weekDaysRate, weekEndsRate;
    private String startDate, endDate;

    public Hotel(String hotelName, int rate) {
        this.hotelName = hotelName;
        this.rate = rate;
    }

    public Hotel(String hotelName, int rate, String startDate, String endDate) {
        this.hotelName = hotelName;
        this.rate = rate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Hotel(String hotelName, int weekDaysRate, int weekEndsRate) {
        this.hotelName = hotelName;
        this.weekDaysRate = weekDaysRate;
        this.weekEndsRate = weekEndsRate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public int getRate() {
        return rate;
    }
    public int getWeekDaysRate() {
        return weekDaysRate;
    }

    public int getWeekEndsRate() {
        return weekEndsRate;
    }

    public int calculateNumberOfDays(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy");
        LocalDate localStartDate = LocalDate.parse(startDate, formatter);
        LocalDate localEndDate = LocalDate.parse(endDate, formatter);
        return Period.between(localStartDate, localEndDate).getDays();
    }

    public int calculatePrice() {
        return calculateNumberOfDays(startDate, endDate) * rate;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", weekDaysRate=" + weekDaysRate +
                ", weekEndsRate=" + weekEndsRate +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }

    public void display() {
        System.out.println("Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", weekDaysRate=" + weekDaysRate +
                ", weekEndsRate=" + weekEndsRate +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}');
    }
}
