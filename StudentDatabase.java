import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name, dob, bloodGroup, contactAddress;

    public Student(String name, String dob, String bloodGroup, String contactAddress) {
        this.name = name;
        this.dob = dob;
        this.bloodGroup = bloodGroup;
        this.contactAddress = contactAddress;
    }
}

class PhysicalAttributes {
    double height, weight;

    public PhysicalAttributes(double height, double weight) {
        this.height = height;
        this.weight = weight;
    }
}

class InsuranceInfo {
    String policyNumber;

    public InsuranceInfo(String policyNumber) {
        this.policyNumber = policyNumber;
    }
}

class StudentRecord extends Student {
    String telephoneNumber, drivingLicense;
    PhysicalAttributes physicalAttributes;
    InsuranceInfo insuranceInfo;

    public StudentRecord(String name, String dob, String bloodGroup, String contactAddress, String telephoneNumber,
                         String drivingLicense, double height, double weight, String policyNumber) {
        super(name, dob, bloodGroup, contactAddress);
        this.telephoneNumber = telephoneNumber;
        this.drivingLicense = drivingLicense;
        this.physicalAttributes = new PhysicalAttributes(height, weight);
        this.insuranceInfo = new InsuranceInfo(policyNumber);
    }

    public void displayRecord() {
        System.out.println("\nName: " + name);
        System.out.println("DOB: " + dob);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Address: " + contactAddress);
        System.out.println("Phone: " + telephoneNumber);
        System.out.println("Driving License: " + drivingLicense);
        System.out.println("Height: " + physicalAttributes.height + " cm");
        System.out.println("Weight: " + physicalAttributes.weight + " kg");
        System.out.println("Insurance Policy: " + insuranceInfo.policyNumber);
    }
}

public class StudentDatabase {
    public static void main(String[] args) {
        ArrayList<StudentRecord> records = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Record\n2. Display Records\n3. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Name: ");
                sc.nextLine(); // Clear buffer
                String name = sc.nextLine();
                System.out.print("Enter DOB: ");
                String dob = sc.nextLine();
                System.out.print("Enter Blood Group: ");
                String bloodGroup = sc.nextLine();
                System.out.print("Enter Contact Address: ");
                String address = sc.nextLine();
                System.out.print("Enter Telephone Number: ");
                String phone = sc.nextLine();
                System.out.print("Enter Driving License: ");
                String license = sc.nextLine();
                System.out.print("Enter Height (cm): ");
                double height = sc.nextDouble();
                System.out.print("Enter Weight (kg): ");
                double weight = sc.nextDouble();
                System.out.print("Enter Insurance Policy Number: ");
                sc.nextLine(); // Clear buffer
                String policy = sc.nextLine();

                records.add(new StudentRecord(name, dob, bloodGroup, address, phone, license, height, weight, policy));
                System.out.println("Record added successfully!");
            } else if (choice == 2) {
                if (records.isEmpty()) {
                    System.out.println("No records to display.");
                } else {
                    for (StudentRecord record : records) {
                        record.displayRecord();
                    }
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}

