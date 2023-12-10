import java.util.InputMismatchException;
import java.util.Scanner;

public class CarRentalService {

    public static void printCar(Car car) {
        System.out.println("Car type: " + car.getType());
        System.out.println("Car registration number: " + car.getRegNumber());
        System.out.println("Car model year: " + car.getModelYear());

        if (car.isUnderMaintenance()) {
            System.out.println("Car is under maintenance.");
        } else {
            System.out.println("Car is not under maintenance.");
        }

        RentingInfo rentingInfo = car.getRentingInfo();
        if (rentingInfo == null) {
            System.out.println("The car is not rented.");
        } else {
            System.out.println("The car is rented to " + rentingInfo.getCustomerName() + " for " + rentingInfo.getNumOfRentingDays() + " days.");
        }
    }



    public static void main(String[] args) {
        RentalSystem rentalSystem = new RentalSystem();

        while (true) {
            System.out.println("\nWhat to do:");
            System.out.println("1. Rent a car");
            System.out.println("2. Return a car");
            System.out.println("3. Print most rented car");
            System.out.println("4. Print all available cars");
            System.out.print("Enter your choice: ");

            int choice = new Scanner(System.in).nextInt();
            RentingInfo RI = new RentingInfo();

            switch (choice) {
                case 1:
                    // Rent a car
                    System.out.print("Enter model year: ");
                    int modelYear = new Scanner(System.in).nextInt();

                    Car pickedCar = rentalSystem.pickACar(modelYear);
                    if (pickedCar != null) {
                        // Print car details
                        printCar(pickedCar);

                        System.out.print("Enter the renter's name: ");
                        String customerName = new Scanner(System.in).nextLine();

                        System.out.print("Enter the renter's contact number: ");
                        String customerMobileNum = new Scanner(System.in).nextLine();

                        System.out.print("Enter the number of renting days (0-" + RI.getMaxRentingDays() + "): ");
                        int numOfRentingDays = 0;
                        do {
                            try {
                                numOfRentingDays = new Scanner(System.in).nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid input. Please enter a valid number of days.");
                                continue;
                            }
                            if (numOfRentingDays < 1 || numOfRentingDays > RI.getMaxRentingDays()) {
                                System.out.println("Number of renting days must be between (1 and " + RI.getMaxRentingDays() +")");
                            }
                        } while (numOfRentingDays < 1 || numOfRentingDays > RI.getMaxRentingDays());

                        RentingInfo rentingInfo = new RentingInfo();
                        rentingInfo.setNumOfRentingDays(numOfRentingDays);
                        pickedCar.setRentingInfo(rentingInfo);

                        System.out.println("\nCar rented successfully!");
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;

                case 2:
                    // Return a car
                    System.out.print("Enter registration number of car to return: ");
                    int regNumber = new Scanner(System.in).nextInt();

                    Car carToReturn = null;
                    for (Car car : rentalSystem.getCars()) {
                        if (car.getRegNumber() == regNumber) {
                            carToReturn = car;
                            break;
                        }
                    }

                    if (carToReturn != null) {
                        if (carToReturn.resetRentingInfo()) {
                            System.out.println("\nCar returned successfully!");
                        } else {
                            System.out.println("Car is not rented.");
                        }
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;

                case 3:
                    // Print most rented car
                    Car mostRentedCar = rentalSystem.getMostRentedCar();
                    if (mostRentedCar != null) {
                        System.out.println("\nMost rented car:");
                        printCar(mostRentedCar);
                    } else {
                        System.out.println("No car has been rented yet.");
                    }
                    break;

                case 4:
                    // Print all available cars
                    Car[] availableCars = rentalSystem.getAvailableCars();

                    if (availableCars.length > 0) {
                        System.out.println("\nAvailable Cars:");
                        System.out.println("-------------------------------------------------------------------");
                        System.out.format("%-20s | %-15s | %-15s | %-15s\n", "Car Registration Number", "Car Type", "Car Model Year", "Total Rented Days");
                        System.out.println("-------------------------------------------------------------------");
                        for (Car car : availableCars) {
                            System.out.format("%-20d | %-15s | %-15d | %-15d\n", car.getRegNumber(), car.getType(), car.getModelYear(), car.getTotalRentedDays());
                        }
                    } else {
                        System.out.println("No available cars at the moment.");
                    }
                    break;
            }
        }
    }
}