import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

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

    @Test
    public void testBookPhone() {
        String bookedPhoneId = booking.bookPhone("Samsung Galaxy S8", user1);
        MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());
    }

    @Test
    public void testBookAlreadyBookedPhone() {
        String bookedPhoneId = booking.bookPhone("Apple iPhone 11", user1);
        MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());

        String bookedPhoneId1 = booking.bookPhone("Apple iPhone 11", user2);
        phone = booking.getPhoneByModel("Apple iPhone 11");
        Assertions.assertNull(bookedPhoneId1);
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());
    }

    @Test
    public void testBookNonExistentPhone() {
        String bookedPhoneId = booking.bookPhone("Google Pixel 5", user1);
        MobilePhone phone = booking.getPhoneByModel("Google Pixel 5");
        Assertions.assertNull(bookedPhoneId);
        Assertions.assertNull(phone);
    }

    @Test
    public void testReturnPhone() {
//        booking = new MobilePhoneBookingService();
//        user1 = new BookedBy("User 1");
//        user2 = new BookedBy("User 2");
        String bookedPhoneId = booking.bookPhone("Samsung Galaxy S8", user1);
        MobilePhone phone = booking.getAvailablePhoneById(UUID.fromString(bookedPhoneId));
        Assertions.assertNotNull(bookedPhoneId);
        Assertions.assertNotNull(phone);
        Assertions.assertFalse(phone.isAvailable());
        Assertions.assertEquals(user1, phone.getBookedBy());

        booking.returnPhone("Samsung Galaxy S8");
        phone = booking.getPhoneByModel("Samsung Galaxy S8");
        Assertions.assertNotNull(phone);
        Assertions.assertTrue(phone.isAvailable());
        Assertions.assertNull(phone.getBookedBy());
    }

    @Test
    public void testReturnAlreadyReturnedPhone() {
        booking.returnPhone("Samsung Galaxy S8");
        MobilePhone phone = booking.getBookedPhoneByModel("Samsung Galaxy S8");
        Assertions.assertNull(phone);
    }
}
