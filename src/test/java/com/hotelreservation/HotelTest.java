package com.hotelreservation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelTest {
    @Test
    public void givenLakewoodRateDetailsWhenAddedShouldReturnTrue() {
        Hotel hotel = new Hotel("Lakewood", 110, 90, 3);
        boolean result = (hotel.getWeekdayRate() == 110) && (hotel.getWeekendRate() == 90) && (hotel.getRating() == 3);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenBridgewoodRateDetailsWhenAddedShouldReturnTrue() {
        Hotel hotel = new Hotel("Bridgewood", 150, 50, 4);
        boolean result = (hotel.getWeekdayRate() == 150) && (hotel.getWeekendRate() == 50) && (hotel.getRating() == 4);
        Assertions.assertTrue(result);
    }

    @Test
    public void givenRidgewoodRateDetailsWhenAddedShouldReturnTrue() {
        Hotel hotel = new Hotel("Ridgewood", 220, 150, 5);
        boolean result = (hotel.getWeekdayRate() == 220) && (hotel.getWeekendRate() == 150) && (hotel.getRating() == 5);
        Assertions.assertTrue(result);
    }
}
