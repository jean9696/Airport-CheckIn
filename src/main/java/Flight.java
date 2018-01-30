public class Flight {
    private String flightCode;
    private String destinationAirport;
    private String carrier;
    private Integer passengerCapacity;
    private BaggageSize baggageCapacity;
    private BaggageSize baggageRegistered;

    public Flight(String flightCode, String destinationAirport, String carrier, Integer passengerCapacity, BaggageSize baggageCapacity) {
        this.flightCode = flightCode;
        this.destinationAirport = destinationAirport;
        this.carrier = carrier;
        this.passengerCapacity = passengerCapacity;
        this.baggageCapacity = baggageCapacity;
        this.baggageRegistered = new BaggageSize(0, 0);
    }


    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public BaggageSize getBaggageCapacity() {
        return baggageCapacity;
    }

    public void setBaggageCapacity(BaggageSize baggageCapacity) {
        this.baggageCapacity = baggageCapacity;
    }

    public BaggageSize getBaggageRegistered() {
        return baggageRegistered;
    }

    public void setBaggageRegistered(BaggageSize baggageRegistered) {
        this.baggageRegistered = baggageRegistered;
    }
}
