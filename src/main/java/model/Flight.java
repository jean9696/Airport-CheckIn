package model;

/**
 * Represents a flight
 * Is not static so also contains baggage and passenger registered so it is easier to print the report
 */
public class Flight {
    private String flightCode;
    private String destinationAirport;
    private String carrier;
    private Integer passengerCapacity;
    private BaggageSize baggageCapacity;
    private BaggageSize baggageRegistered;
    private int nbPassengerRegistered; // stores the number of passengers who have checked-in for this flight
    private int extraFeesCollected;

    /**
     * @param flightCode
     * @param destinationAirport
     * @param carrier
     * @param passengerCapacity
     * @param baggageCapacity
     */
    public Flight(String flightCode, String destinationAirport, String carrier, Integer passengerCapacity, BaggageSize baggageCapacity) {
        this.flightCode = flightCode;
        this.destinationAirport = destinationAirport;
        this.carrier = carrier;
        this.passengerCapacity = passengerCapacity;
        this.baggageCapacity = baggageCapacity;
        this.baggageRegistered = new BaggageSize(0, 0);
    }


    /**
     * @return flight code
     */
    public String getFlightCode() {
        return flightCode;
    }

    /**
     * @param flightCode
     */
    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    /**
     * @return destination airport name
     */
    public String getDestinationAirport() {
        return destinationAirport;
    }

    /**
     * @param destinationAirport
     */
    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    /**
     * @return carrier name
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * @param carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * @return passenger capacity
     */
    public Integer getPassengerCapacity() {
        return passengerCapacity;
    }

    /**
     * @param passengerCapacity
     */
    public void setPassengerCapacity(Integer passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    /**
     * @return baggage capacity
     */
    public BaggageSize getBaggageCapacity() {
        return baggageCapacity;
    }

    /**
     * @param baggageCapacity
     */
    public void setBaggageCapacity(BaggageSize baggageCapacity) {
        this.baggageCapacity = baggageCapacity;
    }

    /**
     * @return how much baggage is registered for this flight
     */
    public BaggageSize getBaggageRegistered() {
        return baggageRegistered;
    }

    /**
     * @param baggageRegistered
     */
    public void setBaggageRegistered(BaggageSize baggageRegistered) {
        this.baggageRegistered = baggageRegistered;
    }

    /**
     * Add baggage to current baggage size in the flight
     * @param baggageRegistered
     */
    public void addBaggageRegistered(BaggageSize baggageRegistered) {
        this.baggageRegistered.addBaggage(baggageRegistered);
    }

    /**
     * Method to increment the number of passengers who have checked-in for the flight.
     * 
     */
    public void addOnePassenger() {
    	nbPassengerRegistered++;
    }

    /**
     * Method that counts the extra fees collected for the flight
     */
    public void addExtraFees(int fees) {
        extraFeesCollected += fees;
    }

    /**
     * Get the extra fees collected
     * @return Extra fees collected in £
     */
    public int getExtraFees() {
        return extraFeesCollected;
    }
    
    /**
     * Method to get the number of passengers who have checked-in for the flight. Useful for the report
     * @return The number of passengers who have checked-in for the flight
     */
    public int getNbPassengersRegistered() {
    	return nbPassengerRegistered;
    }

    /**
     * Check if the flight is overloaded (weight of baggage registered greater than the flight weight capacity)
     * @return true if the flight is overloaded
     */
    public String isFlightOverweighted() {
        if (this.getBaggageRegistered().getWeight() > this.baggageCapacity.getWeight()) {
            return "Y";
        }
        else {return "N";}
    }

    /**
     * Check whether the volume of the registered baggage is greater than the flight volume capacity
     * @return true if the flight volume capacity is exceeded
     */
    public String isBaggageRegisteredTooLarge() {
        if (this.getBaggageRegistered().getVolume() > this.baggageCapacity.getVolume()) {
            return "Y";
        }
        else {return "N";}
    }

}
