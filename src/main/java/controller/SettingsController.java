package controller;

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

    public void setInitialQueueLength(Integer initialQueueLength) throws Exception {
        if (initialQueueLength < 0 || initialQueueLength > 20) {
            throw new Exception("Invalid queue length");
        }
        this.initialQueueLength = initialQueueLength;
    }

    public Integer getCheckInDeskNumber() {
        return checkInDeskNumber;
    }

    public void setCheckInDeskNumber(Integer checkInDeskNumber) throws Exception {
        if (checkInDeskNumber < 0 || checkInDeskNumber > 5) {
            throw new Exception("Invalid checkIn desk number");
        }
        this.checkInDeskNumber = checkInDeskNumber;
    }

    public Integer getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Integer openTime) throws Exception {
        if (openTime < 0 || openTime > 600) {
            throw new Exception("Invalid open time");
        }
        this.openTime = openTime;
    }
}
