import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CheckIn {
    private HashMap<String, Flight> flights;

    private HashMap<String, Booking> bookings;

    private Integer checkInPassenger;

    public CheckIn(HashMap<String, Flight> flights, HashMap<String, Booking> bookings) {
        this.flights = flights;
        this.bookings = bookings;
        this.checkInPassenger = 0;
    }

    public HashMap<String, Flight> getFlights() {
        return flights;
    }

    public HashMap<String, Booking> getBookings() {
        return bookings;
    }

    public Flight getFlight(String key) {
        return flights.get(key);
    }

    public Booking getBooking(String key) {
        return bookings.get(key);
    }

    public Integer getCheckInPassenger() {
        return checkInPassenger;
    }

    public void checkInPassenger() {
        // TODO: set the booking to checkedIn
        checkInPassenger++;
    }


    public static HashMap<String, Flight> readFlightsFromInputFiles() {
        return new HashMap<String, Flight>();
    }

    public static HashMap<String, Booking> readBookingsFromInputFiles() {
        return new HashMap<String, Booking>();
    }

    public static void main (String[] args) {

        // should come from files
        HashMap<String, Flight> flights = readFlightsFromInputFiles();
        HashMap<String, Booking> bookings = readBookingsFromInputFiles();

        final Integer bookingNumber = bookings.size();
        final CheckIn checkIn = new CheckIn(flights, bookings);
        final GUI GUI = new GUI();
        ActionListener handleConfirm = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Booking booking = checkIn.getBooking(GUI.getBookingReferenceInput());
                if (booking != null && booking.canPassengerAccess(GUI.getSurnameInput(), GUI.getLastNameInput())) {
                    checkIn.checkInPassenger();
                    // TODO: Check baggage sizes and print an error message if necessary
                    if (bookingNumber.equals(checkIn.getCheckInPassenger())) {
                        GUI.close();
                        checkIn.writeToFile("Report.txt",checkIn.makeReport());
                    }
                    GUI.clear();
                } else {
                    GUI.setMessage("Something went wrong");
                }
            }
        };
        GUI.setOnConfirm(handleConfirm);
        
    }
    
    /**
	 * Write the informations in the file provided in parameters.
	 * --- > This method comes from F21SF course lab and re-used in F21SF coursework <-----
	 * @param filename the file in which to write
	 * @param text the text to write in the file
	 */
    public void writeToFile(String filename, String text) {
		 FileWriter fw;
		 try {
		    fw = new FileWriter(filename);
		    fw.write(text);
		 	fw.close();
		 }
		 //message and stop if file not found
		 catch (FileNotFoundException fnf){
			 System.out.println(filename + " not found ");
			 System.exit(0);
		 }
		 //stack trace here because we don't expect to come here
		 catch (IOException ioe){
		    ioe.printStackTrace();
		    System.exit(1);
		 }
	}

    /**
     * Method to make the report that summarizes all the flights informations
     * @return The report
     */
	public String makeReport() {
		String report = "List of flight's statistics after check-in\nFlight code       Nb passengers   Total weight   Total volume  "
				+ "Excess baggage fees\n";
		report += "---------------------------------------------------------------------------------------------";
		for (Flight flight : flights.values()) {
			
			report += String.format("%-8s", flight.getFlightCode());
			report += String.format("%-3d", flight.getNbPassengersRegistered());
			report += String.format("%-5d", flight.getBaggageRegistered().getWeight());
			report += String.format("%-5d", flight.getBaggageRegistered().getVolume());
			report += "\n";
			
		}
	return report;
    }

	
}
