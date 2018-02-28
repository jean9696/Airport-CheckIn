package view;

import model.entity.CheckInDesk;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Desk extends JPanel implements Observer {

    private CheckInDesk checkInDesk;

    public Desk(CheckInDesk checkInDesk) {
        super();
        this.checkInDesk = checkInDesk;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
