import java.util.Scanner;

class RentingInfo {
    private String customerName;
    private int numOfRentingDays;
    private String customerMobileNum;
    private int maxRentingDays = 15;

    public RentingInfo() {

    }

    public String getCustomerName() {
        return customerName;
    }

    public int getNumOfRentingDays() {
        return numOfRentingDays;
    }

    public String getCustomerMobileNum() {
        return customerMobileNum;
    }

    public int getMaxRentingDays() {
        return maxRentingDays;
    }

    public void setMaxRentingDays(int newMaxRentingDays) {
        // Modify the maxRentingDays only if the new value is greater than 3
        if (maxRentingDays > 3) {
            this.maxRentingDays = maxRentingDays;
        } else {
            System.out.println("Could not modify maximum renting days");
        }
    }

    public boolean extendRenting(int additionalDays) {
        if (numOfRentingDays + additionalDays <= maxRentingDays) {
            numOfRentingDays += additionalDays;
            return true;
        }
        return false;
    }

    public void setNumOfRentingDays(int numOfRentingDays) {
        if (numOfRentingDays > 0 && numOfRentingDays <= maxRentingDays) {
            this.numOfRentingDays = numOfRentingDays;
        } else {
            System.out.println("Number of renting days must be between 0 and " + maxRentingDays);
        }
    }
}