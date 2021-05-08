package com.hotelreservation;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


public class HotelReservationTest {
    private static Hotel firstHotel, secondHotel, thirdHotel;

    @BeforeClass
    public static void initializeHotels() {
        firstHotel = new Hotel("Lakewood", 110, 90, 80, 80, 3);
        secondHotel = new Hotel("Bridgewood", 150, 50, 110, 50, 4);
        thirdHotel = new Hotel("Ridgewood", 220, 150, 100, 40, 5);
    }

    @Test
    public void givenCustomerType_WhenValid_ShouldReturnTrue() throws CustomerDetailsException {
        HotelReservation firstHotelReservation = new HotelReservation("Regular", "11Sep2020", "12Sep2020");
        HotelReservation secondHotelReservationForReward = new HotelReservation("Reward", "11Sep2020", "12Sep2020");
        String firstCustomerType = firstHotelReservation.getCustomerType();
        String secondCustomerType = secondHotelReservationForReward.getCustomerType();
        boolean result = firstHotelReservation.isCustomerTypeValid(firstCustomerType)
                && secondHotelReservationForReward.isCustomerTypeValid(secondCustomerType);
        Assert.assertEquals(true, result);
    }

    @Test
    public void givenCustomerType_WhenInvalid_ShouldReturnEnterValidCustomerType() {
        HotelReservation hotelReservation = new HotelReservation("Reg", "11Sep2020", "12Sep2020");
        String customerType = hotelReservation.getCustomerType();
        try {
            hotelReservation.isCustomerTypeValid(customerType);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_INVALID, e.exceptionType);
            Assert.assertEquals("Enter valid customer type", e.getMessage());
        }
    }

    @Test
    public void givenCustomerType_WhenEmpty_ShouldReturnDataCannotBeEmpty() {
        HotelReservation hotelReservation = new HotelReservation("", "11Sep2020", "12Sep2020");
        String customerType = hotelReservation.getCustomerType();
        try {
            hotelReservation.isCustomerTypeValid(customerType);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_EMPTY, e.exceptionType);
            Assert.assertEquals("Data can't be empty", e.getMessage());
        }
    }

    @Test
    public void givenCustomerType_WhenNull_ShouldReturnDataCannotBeNull() {
        HotelReservation hotelReservation = new HotelReservation(null, "11Sep2020", "12Sep2020");
        String customerType = hotelReservation.getCustomerType();
        try {
            hotelReservation.isCustomerTypeValid(customerType);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_NULL, e.exceptionType);
            Assert.assertEquals("Data can't be null", e.getMessage());
        }
    }

    @Test
    public void givenStartDate_WhenValid_ShouldReturnTrue() throws CustomerDetailsException {
        HotelReservation hotelReservation = new HotelReservation("Reward", "11Sep2020", "12Sep2020");
        String date = hotelReservation.getStartDate();
        boolean result = hotelReservation.isDateValid(date);
        Assert.assertEquals(true, result);
    }

    @Test
    public void givenStartDate_WhenInvalid_ShouldReturnEnterValidDate() {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11-09-2020", "12Sep2020");
        String date = hotelReservation.getStartDate();
        try {
            hotelReservation.isDateValid(date);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_INVALID, e.exceptionType);
            Assert.assertEquals("Enter valid date", e.getMessage());
        }
    }

    @Test
    public void givendStartDate_WhenEmpty_ShouldReturnDataCannotBeEmpty() {
        HotelReservation hotelReservation = new HotelReservation("Regular", "", "12Sep2020");
        String date = hotelReservation.getStartDate();
        try {
            hotelReservation.isDateValid(date);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_EMPTY, e.exceptionType);
            Assert.assertEquals("Data can't be empty", e.getMessage());
        }
    }

    @Test
    public void givendStartDate_WhenNull_ShouldReturnDataCannotBeNull() {
        HotelReservation hotelReservation = new HotelReservation("Reward", null, "12Sep2020");
        String date = hotelReservation.getStartDate();
        try {
            hotelReservation.isDateValid(date);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_NULL, e.exceptionType);
            Assert.assertEquals("Data can't be null", e.getMessage());
        }
    }

    @Test
    public void givenEndDate_WhenValid_ShouldReturnTrue() throws CustomerDetailsException {
        HotelReservation hotelReservation = new HotelReservation("Reward", "11Sep2020", "12Sep2025");
        String date = hotelReservation.getEndDate();
        boolean result = hotelReservation.isDateValid(date);
        Assert.assertEquals(true, result);
    }

    @Test
    public void givenEndDate_WhenInvalid_ShouldReturnEnterValidDate() {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11Sep2020", "2020-12-31");
        String date = hotelReservation.getStartDate();
        try {
            hotelReservation.isDateValid(date);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_INVALID, e.exceptionType);
            Assert.assertEquals("Enter valid date", e.getMessage());
        }
    }

    @Test
    public void givendEndDate_WhenEmpty_ShouldReturnDataCannotBeEmpty() {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11Sep2020", "");
        String date = hotelReservation.getEndDate();
        try {
            hotelReservation.isDateValid(date);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_EMPTY, e.exceptionType);
            Assert.assertEquals("Data can't be empty", e.getMessage());
        }
    }

    @Test
    public void givendEndDate_WhenNull_ShouldReturnDataCannotBeNull() {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11Sep2020", null);
        String date = hotelReservation.getEndDate();
        try {
            hotelReservation.isDateValid(date);
        } catch (CustomerDetailsException e) {
            Assert.assertEquals(CustomerDetailsException.ExceptionType.ENTERED_NULL, e.exceptionType);
            Assert.assertEquals("Data can't be null", e.getMessage());
        }
    }

    @Test
    public void givenHotels_WhenAdded_ShouldReturnTheCheapestHotel() throws CustomerDetailsException {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11Sep2020", "12Sep2020");
        hotelReservation.addHotelToHotelReservation(firstHotel);
        hotelReservation.addHotelToHotelReservation(secondHotel);
        hotelReservation.addHotelToHotelReservation(thirdHotel);
        String hotelName = hotelReservation.findTheCheapestHotel();
        Assert.assertEquals("Lakewood Bridgewood ", hotelName);
    }

    @Test
    public void givenHotels_WhenAddedForRegularCustomer_ShouldReturnTheCheapestBestRatedHotel() throws CustomerDetailsException {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11Sep2020", "12Sep2020");
        hotelReservation.addHotelToHotelReservation(firstHotel);
        hotelReservation.addHotelToHotelReservation(secondHotel);
        hotelReservation.addHotelToHotelReservation(thirdHotel);
        String hotelName = hotelReservation.findTheCheapestBestRatedHotel();
        Assert.assertEquals("Bridgewood", hotelName);
    }

    @Test
    public void givenHotels_WhenAddedForRegularCustomer_ShouldReturnTheBestRatedHotel() {
        HotelReservation hotelReservation = new HotelReservation("Regular", "11Sep2020", "12Sep2020");
        hotelReservation.addHotelToHotelReservation(firstHotel);
        hotelReservation.addHotelToHotelReservation(secondHotel);
        hotelReservation.addHotelToHotelReservation(thirdHotel);
        String hotelName = hotelReservation.findBestRatedHotel();
        Assert.assertEquals("Ridgewood", hotelName);
    }

    @Test
    public void givenHotels_WhenAddedForRewardCustomer_ShouldReturnTheCheapestBestRatedHotel() throws CustomerDetailsException {
        HotelReservation hotelReservation = new HotelReservation("Reward", "11Sep2020", "12Sep2020");
        hotelReservation.addHotelToHotelReservation(firstHotel);
        hotelReservation.addHotelToHotelReservation(secondHotel);
        hotelReservation.addHotelToHotelReservation(thirdHotel);
        String hotelName = hotelReservation.findTheCheapestBestRatedHotel();
        Assert.assertEquals("Ridgewood", hotelName);
    }
}