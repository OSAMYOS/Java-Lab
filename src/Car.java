import java.util.Scanner;

class Car {
    private String type;
    private int modelYear;
    private int regNumber;
    private boolean underMaintenance = false;
    private RentingInfo rentingInfo = null;
    private int totalRentedDays = 0;

    public Car() {
        // Read type, modelYear, and regNumber from the user
        System.out.print("Enter car type: ");
        type = new Scanner(System.in).nextLine();

        System.out.print("Enter car model year: ");
        modelYear = new Scanner(System.in).nextInt();

        System.out.print("Enter car registration number: ");
        regNumber = new Scanner(System.in).nextInt();
    }

    public Car(String type, int modelYear, int regNumber) {
        this.type = type;
        this.modelYear = modelYear;
        this.regNumber = regNumber;
        this.underMaintenance = false;
        this.rentingInfo = null;
        this.totalRentedDays = 0;
    }

    public String getType() {
        return type;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int getRegNumber() {
        return regNumber;
    }

    public boolean isUnderMaintenance() {
        return underMaintenance;
    }

    public RentingInfo getRentingInfo() {
        return rentingInfo;
    }

    public int getTotalRentedDays() {
        return totalRentedDays;
    }

    public void setUnderMaintenance(boolean underMaintenance) {
        this.underMaintenance = underMaintenance;
    }

    public boolean setRentingInfo(RentingInfo rentingInfo) {
        if (this.rentingInfo == null) {
            this.rentingInfo = new RentingInfo();
            return true;
        }
        return false;
    }

    public boolean resetRentingInfo() {
        if (this.rentingInfo != null) {
            totalRentedDays += rentingInfo.getNumOfRentingDays();
            rentingInfo = null;
            return true;
        }
        return false;
    }
}