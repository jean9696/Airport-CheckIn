package model.collection;

public class BookingList {
    private static BookingList ourInstance = new BookingList();

    public static BookingList getInstance() {
        return ourInstance;
    }

    private BookingList() {
    }
}
