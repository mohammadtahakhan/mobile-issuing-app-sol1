import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test class for testing multiple scenarios
 */
public class MobilePhoneBookingServiceTest {
    private MobilePhoneBookingService booking;
    private BookedBy user1;
    private BookedBy user2;

    @BeforeEach
    public void setUp() {
        booking = new MobilePhoneBookingService();
        user1 = new BookedBy("User 1");
        user2 = new BookedBy("User 2");
    }

    /**
     * Test case for testing phone book
     */
    @Test
    public void testBookPhone() {
        //Book a phone
        String bookedPhoneId = booking.bookPhone("Samsung Galaxy S8", user1);
        //get booked phone
        MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));
        //assertions
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());
    }

    /**
     * Test case for book an already booked phone
     */
    @Test
    public void testBookAlreadyBookedPhone() {
        //book a phone
        String bookedPhoneId = booking.bookPhone("Apple iPhone 11", user1);
        //get booked phone
        MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));

        //assertions for booked phone
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());

        //Again book the same phone which booked above
        String bookedPhoneId1 = booking.bookPhone("Apple iPhone 11", user2);
        phone = booking.getPhoneByModel("Apple iPhone 11");
        //Assertions
        Assertions.assertNull(bookedPhoneId1);
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());
    }

    /**
     * Test non existing phone
     */
    @Test
    public void testBookNonExistentPhone() {
        //Try to book a non exiting phone
        String bookedPhoneId = booking.bookPhone("Google Pixel 5", user1);
        MobilePhone phone = booking.getPhoneByModel("Google Pixel 5");
        //Assertion
        Assertions.assertNull(bookedPhoneId);
        Assertions.assertNull(phone);
    }

    /**
     * Test case for returning a phone
     */
    @Test
    public void testReturnPhone() {

        //Book a phone
        String bookedPhoneId = booking.bookPhone("Samsung Galaxy S8", user1);
        MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));
        Assertions.assertNotNull(bookedPhoneId);
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());

        //Return the same phone
        booking.returnPhone("Samsung Galaxy S8");
        phone = booking.getPhoneByModel("Samsung Galaxy S8");
        Assertions.assertNotNull(phone);
        Assertions.assertTrue(phone.isAvailable());
        Assertions.assertNull(phone.getBookedBy());
    }

    /**
     * Test case for returning already returned phone
     */
    @Test
    public void testReturnAlreadyReturnedPhone() {
        booking.returnPhone("Samsung Galaxy S8");
        MobilePhone phone = booking.getBookedPhoneByModel("Samsung Galaxy S8");
        Assertions.assertNull(phone);
    }
}
