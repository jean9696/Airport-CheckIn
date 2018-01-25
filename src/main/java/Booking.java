public class Booking {
    private Integer bookId;
    private Boolean checkedIn;
    private Flight flight;
    private Baggage baggage;
    private Passenger passenger;

    public Booking(Integer bookId, Boolean checkedIn, Flight flight, Baggage baggage, Passenger passenger) {
        this.bookId = bookId;
        this.checkedIn = checkedIn;
        this.flight = flight;
        this.baggage = baggage;
        this.passenger = passenger;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Boolean getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(Boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Baggage getBaggage() {
        return baggage;
    }

    public void setBaggage(Baggage baggage) {
        this.baggage = baggage;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }
}
