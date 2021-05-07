package com.hotelreservation;

import org.junit.jupiter.api.Test;

public class HotelTest {

//    @Test
//    public void givenLakewoodRateDetailWhenAddedShouldReturnTrue() {
//        Hotel hotelName = new Hotel("Lakewood", 110);
//        boolean result = hotelName.getRate() == 110 && hotelName.getHotelName().equals("Lakewood");
//        hotelName.toString();
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    public void givenBridgewoodRateDetailWhenAddedShouldReturnTrue() {
//        Hotel hotelName = new Hotel("Bridgewood", 160);
//        boolean result = hotelName.getRate() == 160 && hotelName.getHotelName().equals("Bridgewood");
//        hotelName.toString();
//        Assertions.assertTrue(result);
//    }
//
//    @Test
//    public void givenRidgewoodRateDetailWhenAddedShouldReturnTrue() {
//        Hotel hotelName = new Hotel("Ridgewood", 220);
//        boolean result = hotelName.getRate() == 220 && hotelName.getHotelName().equals("Ridgewood");
//        hotelName.display();
//        Assertions.assertTrue(result);
//    }

    @Test
    public void givenHotelNameAndTheirRate_ShouldReturnTrue() {
        Hotel hotel1=new Hotel("Lakewood", 110);
        hotel1.display();
        Hotel hotel2=new Hotel("Bridgewood",160);
        hotel2.display();
        Hotel hotel3=new Hotel("Ridgewood",220);
        hotel3.display();

        boolean hotel1Result=hotel1.getRate()==110 && hotel1.getHotelName().equals("Lakewood");
        boolean hotel2Result=hotel2.getRate()==160 && hotel2.getHotelName().equals("Bridgewood");
        boolean hotel3Result=hotel3.getRate()==220 && hotel3.getHotelName().equals("Ridgewood");
    }
}
