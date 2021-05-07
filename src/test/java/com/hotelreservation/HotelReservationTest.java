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
        System.out.println(hotelReservation.findMinimumPrice());
        String hotelName = hotelReservation.findTheCheapestHotel();
        System.out.println(hotelName);
        Assertions.assertEquals("Lakewood", hotelName);
    }

    @Test
    public void givenHotelsDetailWithRates_ShouldReturnDetails() {
        Hotel hotel1 = new Hotel("Lakewood", 110, 90);
        Hotel hotel2 = new Hotel("Bridgewood", 160, 50);
        Hotel hotel3 = new Hotel("Ridgewood", 220, 150);

        boolean hotel1Result = hotel1.getWeekDaysRate() == 110 && hotel1.getWeekEndsRate() == 90 && hotel1.getHotelName().equals("Lakewood");
        boolean hotel2Result = hotel2.getWeekDaysRate() == 160 && hotel2.getWeekEndsRate() == 50 && hotel2.getHotelName().equals("Bridgewood");
        boolean hotel3Result = hotel3.getWeekDaysRate() == 220 && hotel3.getWeekEndsRate() == 150 && hotel3.getHotelName().equals("Ridgewood");

        Assertions.assertTrue(hotel1Result);
        Assertions.assertTrue(hotel2Result);
        Assertions.assertTrue(hotel3Result);
    }
}
