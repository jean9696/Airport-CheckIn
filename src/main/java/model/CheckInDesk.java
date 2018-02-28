package model;

import java.util.Observable;

public class CheckInDesk extends Observable {
    private Integer id;
    private Passenger currentPassenger;
    private BaggageSize currentBaggage;


    public CheckInDesk(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Passenger getCurrentPassenger() {
        return currentPassenger;
    }

    public void setCurrentPassenger(Passenger currentPassenger) {
        this.currentPassenger = currentPassenger;
        notifyObservers(currentPassenger);
    }

    public BaggageSize getCurrentBaggage() {
        return currentBaggage;
    }

    public void setCurrentBaggage(BaggageSize currentBaggage) {
        this.currentBaggage = currentBaggage;
        notifyObservers(currentBaggage);
    }
}
