package model.entity;

/**
 * model.entity.Booking of a flight with passenger and passenger's baggage size information
 * Can only have one passenger
 * Defined by its Id (bookId)
 */
public class Booking {
    private Integer bookId;
    private Boolean checkedIn;
    private Flight flight;
    private BaggageSize baggageSize;
    private PassengerInformation passenger;

    /**
     * @param bookId
     * @param checkedIn
     * @param flight
     * @param baggageSize
     * @param passenger
     */
    public Booking(Integer bookId, Boolean checkedIn, Flight flight, BaggageSize baggageSize, PassengerInformation passenger) {
        this.bookId = bookId;
        this.checkedIn = checkedIn;
        this.flight = flight;
        this.baggageSize = baggageSize;
        this.passenger = passenger;
    }

    /**
     * @return the id of this booking
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * @param bookId
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * @return if this booking has been already used to check in
     */
    public Boolean getCheckedIn() {
        return checkedIn;
    }

    /**
     * Register the passenger as checkedIn
     * @param checkedIn
     */
    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    /**
     * @return flight associated to this booking that the passenger will take
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @param flight
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @return baggage size that the passenger has declared and that is associated to this booking
     */
    public BaggageSize getBaggageSize() {
        return baggageSize;
    }

    /**
     * @param baggageSize
     */
    public void setBaggageSize(BaggageSize baggageSize) {
        this.baggageSize = baggageSize;
    }

    /**
     * @return passenger associated to this booking
     */
    public PassengerInformation getPassenger() {
        return passenger;
    }

    /**
     * @param passenger
     */
    public void setPassenger(PassengerInformation passenger) {
        this.passenger = passenger;
    }

    /**
     * @param surname
     * @param lastname
     * @return Boolean: True if the passenger details correspond to the booking, else False
     */
    public Boolean canPassengerAccess(String surname, String lastname) {
        return surname.toLowerCase().equals(passenger.getSurname().toLowerCase()) && lastname.toLowerCase().equals(passenger.getLastname().toLowerCase());
    }
}
