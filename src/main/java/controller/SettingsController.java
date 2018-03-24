package controller;

import exception.InvalidInputException;

public class SettingsController {

    private Integer initialQueueLength;
    private Integer checkInDeskNumber;
    private Integer openTime;


    public SettingsController(Integer initialQueueLength, Integer checkInDeskNumber, Integer openTime) {
        this.initialQueueLength = initialQueueLength;
        this.checkInDeskNumber = checkInDeskNumber;
        this.openTime = openTime;
    }

    public Integer getInitialQueueLength() {
        return initialQueueLength;
    }

    public void setInitialQueueLength(Integer initialQueueLength) throws InvalidInputException {
        if (initialQueueLength < 0 || initialQueueLength > 20) {
            throw new InvalidInputException("queue length");
        }
        this.initialQueueLength = initialQueueLength;
    }

    public Integer getCheckInDeskNumber() {
        return checkInDeskNumber;
    }

    public void setCheckInDeskNumber(Integer checkInDeskNumber) throws InvalidInputException {
        if (checkInDeskNumber < 0 || checkInDeskNumber > 5) {
            throw new InvalidInputException("checkIn desk number");
        }
        this.checkInDeskNumber = checkInDeskNumber;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) throws InvalidInputException {
        if (openTime < 0 || openTime > 600) {
            throw new InvalidInputException("open time");
        }
        this.openTime = openTime;
    }
}
