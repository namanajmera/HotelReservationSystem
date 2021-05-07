package com.hotelreservation;

public class Hotel {
    String hotelName;
    int rate;

    public Hotel(String hotelName, int rate) {
        this.hotelName = hotelName;
        this.rate = rate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", rate=" + rate +
                '}';
    }
    public void display()
    {
        System.out.println("Hotel{" +
                "hotelName='" + hotelName + '\'' +
                ", rate=" + rate +
                '}');
    }
}
