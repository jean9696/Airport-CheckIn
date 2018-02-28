package model.entity;

/**
 * Represents a queuing passenger
 */
public class Passenger extends PassengerInformation {
    private BaggageSize baggage;
    private Integer bookingId;


    public Passenger(PassengerInformation passenger, Integer bookingId, BaggageSize baggage) throws Exception {
        super(passenger.surname, passenger.lastname, passenger.age);
        if (!baggage.isValidSize() || bookingId < 1) {
            throw new Exception("Invalid queuing passenger constructor inputs");
        }
        this.baggage = baggage;
        this.bookingId = bookingId;
    }

    public Passenger(PassengerInformation passenger, Integer bookingId) throws Exception {
        super(passenger.surname, passenger.lastname, passenger.age);
        if (bookingId < 1) {
            throw new Exception("Invalid queuing passenger constructor inputs");
        }
        this.baggage = BaggageSize.createRandomBaggage();
        this.bookingId = bookingId;
    }

    public BaggageSize getBaggage() {
        return baggage;
    }

    public void setBaggage(BaggageSize baggage) {
        this.baggage = baggage;
    }

    public Integer getBookingId() {
        return bookingId;
    }

    public void setBookingId(Integer bookingId) {
        this.bookingId = bookingId;
    }
}
