import java.util.*;

class SmartRide {

    // Classes for Riders, Drivers, and Rides
    static class Rider {
        String name;
        String location;

        Rider(String name, String location) {
            this.name = name;
            this.location = location;
        }
    }

    static class Driver {
        String name;
        String location;
        boolean isAvailable;

        Driver(String name, String location) {
            this.name = name;
            this.location = location;
            this.isAvailable = true;
        }
    }

    static class Ride {
        Rider rider;
        Driver driver;
        String destination;

        Ride(Rider rider, Driver driver, String destination) {
            this.rider = rider;
            this.driver = driver;
            this.destination = destination;
        }
    }

    // Lists to store data
    static List<Rider> riders = new ArrayList<>();
    static List<Driver> drivers = new ArrayList<>();
    static List<Ride> rides = new ArrayList<>();

    // Method to register a rider
    static void registerRider(String name, String location) {
        riders.add(new Rider(name, location));
        System.out.println("Rider " + name + " registered successfully.");
    }

    // Method to register a driver
    static void registerDriver(String name, String location) {
        drivers.add(new Driver(name, location));
        System.out.println("Driver " + name + " registered successfully.");
    }

    // Method to request a ride
    static void requestRide(String riderName, String destination) {
        Rider rider = null;
        for (Rider r : riders) {
            if (r.name.equalsIgnoreCase(riderName)) {
                rider = r;
                break;
            }
        }
        if (rider == null) {
            System.out.println("Rider not found!");
            return;
        }

        // Find nearest available driver
        Driver nearestDriver = null;
        for (Driver d : drivers) {
            if (d.isAvailable && d.location.equalsIgnoreCase(rider.location)) {
                nearestDriver = d;
                break;
            }
        }

        if (nearestDriver != null) {
            nearestDriver.isAvailable = false;
            Ride ride = new Ride(rider, nearestDriver, destination);
            rides.add(ride);
            System.out.println("Ride confirmed! Driver " + nearestDriver.name + " will pick up " + rider.name);
        } else {
            System.out.println("No drivers available at the moment.");
        }
    }

    // Method to end a ride
    static void endRide(String driverName) {
        Ride completedRide = null;
        for (Ride r : rides) {
            if (r.driver.name.equalsIgnoreCase(driverName)) {
                completedRide = r;
                break;
            }
        }
        if (completedRide != null) {
            rides.remove(completedRide);
            completedRide.driver.isAvailable = true;
            System.out.println("Ride completed by " + completedRide.driver.name + ". Thank you!");
        } else {
            System.out.println("No ongoing rides found for driver " + driverName);
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSmartRide Platform");
            System.out.println("1. Register Rider");
            System.out.println("2. Register Driver");
            System.out.println("3. Request Ride");
            System.out.println("4. End Ride");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Rider Name: ");
                    String riderName = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String riderLocation = scanner.nextLine();
                    registerRider(riderName, riderLocation);
                    break;
                case 2:
                    System.out.print("Enter Driver Name: ");
                    String driverName = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String driverLocation = scanner.nextLine();
                    registerDriver(driverName, driverLocation);
                    break;
                case 3:
                    System.out.print("Enter Rider Name: ");
                    String requestingRider = scanner.nextLine();
                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();
                    requestRide(requestingRider, destination);
                    break;
                case 4:
                    System.out.print("Enter Driver Name: ");
                    String endingDriver = scanner.nextLine();
                    endRide(endingDriver);
                    break;
                case 5:
                    System.out.println("Exiting SmartRide. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}