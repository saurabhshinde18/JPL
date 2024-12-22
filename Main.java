import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

class Flight {
    private int flightID;
    private String source;
    private String destination;
    private Date departureDate;
    private int totalSeats;
    private int availableSeats;

    public Flight(int flightID, String source, String destination, Date departureDate, int totalSeats) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return "Flight ID: " + flightID + ", Source: " + source + ", Destination: " + destination +
               ", Date: " + departureDate + ", Available Seats: " + availableSeats;
    }
}

class Booking {
    private int bookingID;
    private int flightID;
    private int passengerID;

    public Booking(int bookingID, int flightID, int passengerID) {
        this.bookingID = bookingID;
        this.flightID = flightID;
        this.passengerID = passengerID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getFlightID() {
        return flightID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingID + ", Flight ID: " + flightID + ", Passenger ID: " + passengerID;
    }
}

class Passenger {
    private int passengerID;
    private String name;

    public Passenger(int passengerID, String name) {
        this.passengerID = passengerID;
        this.name = name;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Passenger ID: " + passengerID + ", Name: " + name;
    }
}

class AirlineManagementSystem {
    private ArrayList<Flight> flights = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private int bookingCounter = 1;

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
    }

    public void searchFlights(String source, String destination, Date date) {
        System.out.println("Available Flights:");
        for (Flight flight : flights) {
            if (flight.getSource().equalsIgnoreCase(source) &&
                flight.getDestination().equalsIgnoreCase(destination) &&
                flight.getDepartureDate().equals(date)) {
                System.out.println(flight);
            }
        }
    }

    public void bookFlight(int flightID, int passengerID) {
        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightID() == flightID) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null || selectedFlight.getAvailableSeats() <= 0) {
            System.out.println("Flight not found or no seats available.");
            return;
        }

        Booking booking = new Booking(bookingCounter++, flightID, passengerID);
        bookings.add(booking);
        selectedFlight.bookSeat();
        System.out.println("Booking successful: " + booking);
    }

    public void cancelBooking(int bookingID) {
        Booking selectedBooking = null;
        for (Booking booking : bookings) {
            if (booking.getBookingID() == bookingID) {
                selectedBooking = booking;
                break;
            }
        }

        if (selectedBooking == null) {
            System.out.println("Booking not found.");
            return;
        }

        bookings.remove(selectedBooking);

        for (Flight flight : flights) {
            if (flight.getFlightID() == selectedBooking.getFlightID()) {
                flight.cancelSeat();
                break;
            }
        }

        System.out.println("Booking cancelled: " + selectedBooking);
    }

    public void displayBookings() {
        System.out.println("All Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        AirlineManagementSystem system = new AirlineManagementSystem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        system.addFlight(new Flight(1, "Mumbai", "Delhi", sdf.parse("2024-12-25"), 100));
        system.addFlight(new Flight(2, "Mumbai", "Bangalore", sdf.parse("2024-12-25"), 50));
        system.addPassenger(new Passenger(1, "John Doe"));
        system.addPassenger(new Passenger(2, "Jane Smith"));

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nAirline Management System:");
            System.out.println("1. Search Flights");
            System.out.println("2. Book Flight");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Display All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter source: ");
                    String source = sc.next();
                    System.out.print("Enter destination: ");
                    String destination = sc.next();
                    System.out.print("Enter date (yyyy-MM-dd): ");
                    Date date = sdf.parse(sc.next());
                    system.searchFlights(source, destination, date);
                    break;
                case 2:
                    System.out.print("Enter Flight ID: ");
                    int flightID = sc.nextInt();
                    System.out.print("Enter Passenger ID: ");
                    int passengerID = sc.nextInt();
                    system.bookFlight(flightID, passengerID);
                    break;
                case 3:
                    System.out.print("Enter Booking ID: ");
                    int bookingID = sc.nextInt();
                    system.cancelBooking(bookingID);
                    break;
                case 4:
                    system.displayBookings();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}

