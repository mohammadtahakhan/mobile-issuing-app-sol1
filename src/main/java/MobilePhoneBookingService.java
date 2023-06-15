import java.util.List;
import java.util.UUID;

// MobilePhoneBooking class
class MobilePhoneBookingService {
    private MobilePhoneInventory inventory;

    public MobilePhoneBookingService() {
        inventory = MobilePhoneInventory.getInstance();
        inventory.initializePhones();
    }

    public String bookPhone(String modelName, BookedBy bookedBy) {

        String bookedPhoneId=null;
        MobilePhone availablePhoneByModel = getAvailablePhoneByModel(modelName);
        if (null!=availablePhoneByModel){
            bookSelectedMobile(bookedBy, availablePhoneByModel);
            bookedPhoneId=availablePhoneByModel.getId().toString();

        }
        else {
            System.out.println("Phone not available for booking: " + modelName);
        }

        return bookedPhoneId;

    }

    private void bookSelectedMobile(BookedBy bookedBy, MobilePhone availablePhoneByModel) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getId()==availablePhoneByModel.getId()) {
                phone.book(bookedBy);
                System.out.println(phone.getModel().getName() + " is booked by " + bookedBy.getName()+" on "+phone.getBookedAt());
                return;
            }
        }
    }

    public void returnPhone(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && !phone.isAvailable()) {
                phone.returned();
                System.out.println(modelName + " is returned");
                return;
            }
        }
        System.out.println("Phone not found for return: " + modelName);
    }

    public MobilePhone getPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName)) {
                return phone;
            }
        }
        return null;
    }

    public MobilePhone getAvailablePhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && phone.isAvailable()) {
                return phone;
            }
        }
        return null;
    }

    public MobilePhone getBookedPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && !phone.isAvailable()) {
                return phone;
            }
        }
        return null;
    }

    public MobilePhone getAvailablePhoneById(UUID mobilePhoneId) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getId().equals(mobilePhoneId)) {
                return phone;
            }
        }
        return null;
    }
}