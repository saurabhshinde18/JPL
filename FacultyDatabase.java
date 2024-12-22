class Employee {
    int empId;
    String name;
    double salary;

    public Employee(int empId, String name, double salary) {
        this.empId = empId;
        this.name = name;
        this.salary = salary;
    }

    public void displayDetails() {
        System.out.println("\nEmployee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Salary: $" + salary);
    }
}

class Professor extends Employee {
    String department, researchInterests;

    public Professor(int empId, String name, double salary, String department, String researchInterests) {
        super(empId, name, salary);
        this.department = department;
        this.researchInterests = researchInterests;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Department: " + department);
        System.out.println("Research Interests: " + researchInterests);
    }
}

class Staff extends Employee {
    String designation;
    int yearsOfService;

    public Staff(int empId, String name, double salary, String designation, int yearsOfService) {
        super(empId, name, salary);
        this.designation = designation;
        this.yearsOfService = yearsOfService;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Designation: " + designation);
        System.out.println("Years of Service: " + yearsOfService);
    }
}

public class FacultyDatabase {
    public static void main(String[] args) {
        Employee prof = new Professor(1, "raj shinde", 90000, "Computer Science", "AI and ML");
        Employee staff = new Staff(2, "saurabh shinde", 90000, "Lab Assistant", 10);

        prof.displayDetails();
        staff.displayDetails();
    }
}
