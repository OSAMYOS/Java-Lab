import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RentalSystem {
    private Car[] cars;

    public RentalSystem() {
        // Ask the user to enter the number of cars
        System.out.print("Enter the number of cars: ");
        int numberOfCars = new Scanner(System.in).nextInt();

        // Create the cars array
        cars = new Car[numberOfCars];

        // Create the car objects using the no-arg constructor of the Car class
        for (int i = 0; i < numberOfCars; i++) {
            cars[i] = new Car(); // Create a car object using the no-arg constructor
        }
    }

    public RentalSystem(Car[] cars) {
        // Create a new array and new object for each element
        Car[] newCars = new Car[cars.length];
        for (int i = 0; i < cars.length; i++) {
            newCars[i] = new Car(cars[i].getType(), cars[i].getModelYear(), cars[i].getRegNumber());
        }
        this.cars = newCars;
    }

    public Car[] getCars() {
        return cars;
    }

    public Car[] getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isUnderMaintenance() && car.getRentingInfo() == null) {
                availableCars.add(car);
            }
        }
        return availableCars.toArray(new Car[0]);
    }

    public Car getMostRentedCar() {
        if (cars.length == 0) {
            return null;
        }
        Car mostRentedCar = null;
        int maxTotalRentedDays = 0;
        for (Car car : cars) {
            int totalRentedDays = car.getTotalRentedDays();
            if (totalRentedDays > maxTotalRentedDays) {
                mostRentedCar = car;
                maxTotalRentedDays = totalRentedDays;
            }
        }
        return mostRentedCar;
    }

    public Car pickACar(int modelYear) {
        // Filter available cars with model year greater than the passed year
        List<Car> availableCars = new ArrayList<>();
        for (Car car : getAvailableCars()) {
            if (car.getModelYear() > modelYear) {
                availableCars.add(car);
            }
        }

        // Randomly pick a car from the available cars
        Random random = new Random();
        int randomIndex = random.nextInt(availableCars.size());
        return availableCars.get(randomIndex);
    }
}
