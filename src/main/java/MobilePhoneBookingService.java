import java.util.List;
import java.util.UUID;

// MobilePhoneBooking class
class MobilePhoneBookingService {
    private MobilePhoneInventory inventory;

    public MobilePhoneBookingService() {
        inventory = MobilePhoneInventory.getInstance();
        inventory.initializePhones();
    }

    /**
     * Service method to book a phone
     * @param modelName
     * @param bookedBy
     * @return
     */
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

    /**
     * Service method to return a phone
     * @param modelName
     */
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

    /**
     * Get a phone by model name
     * @param modelName
     * @return
     */
    public MobilePhone getPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName)) {
                return phone;
            }
        }
        return null;
    }

    /**
     * Get available phone by model name
     * @param modelName
     * @return
     */
    public MobilePhone getAvailablePhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && phone.isAvailable()) {
                return phone;
            }
        }
        return null;
    }

    /*Get booked phone by model name*
     *
     * @param modelName
     * @return
     */
    public MobilePhone getBookedPhoneByModel(String modelName) {
        List<MobilePhone> phones = inventory.getPhones();
        for (MobilePhone phone : phones) {
            if (phone.getModel().getName().equals(modelName) && !phone.isAvailable()) {
                return phone;
            }
        }
        return null;
    }

    /**
     * Get available phone by Id
     * @param mobilePhoneId
     * @return
     */
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