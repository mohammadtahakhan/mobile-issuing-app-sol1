public class MobilePhoneBookingTestWithMain {
    public static void main(String[] args) {
        MobilePhoneBookingService booking = new MobilePhoneBookingService();
        BookedBy user1 = new BookedBy("User 1");
        booking.bookPhone("Samsung Galaxy S8",user1);
        booking.returnPhone("Samsung Galaxy S8");

    }
}
