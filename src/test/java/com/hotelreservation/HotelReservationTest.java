package com.hotelreservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelReservationTest {
    @Test
    public void givenHotelsWhenAddedShouldReturnTheCheapestHotel() {
        Hotel firstHotel = new Hotel("Lakewood", 110, "10Sep2020", "11Sep2020");
        Hotel secondHotel = new Hotel("Bridgewood", 160, "10Sep2020", "11Sep2020");
        Hotel thirdHotel = new Hotel("Ridgewood", 220, "10Sep2020", "11Sep2020");
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.addHotelToHotelReservation(firstHotel);
        hotelReservation.addHotelToHotelReservation(secondHotel);
        hotelReservation.addHotelToHotelReservation(thirdHotel);
//        System.out.println(hotelReservation.findMinimumPrice());
        String hotelName = hotelReservation.findTheCheapestHotel();
        System.out.println(hotelName);
        Assertions.assertEquals("Lakewood", hotelName);
    }
}
