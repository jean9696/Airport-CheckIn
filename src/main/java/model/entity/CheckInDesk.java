package model.entity;

import java.util.Observable;

public class CheckInDesk extends Observable {
    private Integer id;
    private Passenger currentPassenger;
    private Boolean open;


    public CheckInDesk(Integer id) {
        this.id = id;
        this.open = true;
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

    public void close() {
        this.open = false;
    }

    public Boolean isOpen() {
        return open;
    }

    public void open() { this.open = true; }
}
