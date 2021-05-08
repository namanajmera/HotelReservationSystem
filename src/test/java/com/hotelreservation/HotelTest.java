package com.hotelreservation;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class HotelTest {
    @Test
    public void givenLakewoodDetails_WhenAdded_ShouldPassTheDeatils() {
        Hotel hotel = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        boolean result = (hotel.getWeekdayRateForRegular() == 110) && (hotel.getWeekendRateForRegular() == 90)
                && (hotel.getWeekdayRateForReward() == 80) && (hotel.getWeekendRateForReward() == 80)
                && (hotel.getRating() == 3);
        Assert.assertTrue(result);
    }

    @Test
    public void givenBridgewoodDetails_WhenAdded_ShouldPassTheDeatils() {
        Hotel hotel = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
        boolean result = (hotel.getWeekdayRateForRegular() == 150) && (hotel.getWeekendRateForRegular() == 50)
                && (hotel.getWeekdayRateForReward() == 110) && (hotel.getWeekendRateForReward() == 50)
                && (hotel.getRating() == 4);
        Assert.assertTrue(result);
    }

    @Test
    public void givenRidgewoodDetails_WhenAdded_ShouldPassTheDeatils() {
        Hotel hotel = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
        boolean result = (hotel.getWeekdayRateForRegular() == 220) && (hotel.getWeekendRateForRegular() == 150)
                && (hotel.getWeekdayRateForReward() == 100) && (hotel.getWeekendRateForReward() == 40)
                && (hotel.getRating() == 5);
        Assert.assertTrue(result);
    }
}
