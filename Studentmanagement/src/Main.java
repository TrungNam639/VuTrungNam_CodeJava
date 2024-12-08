import java.util.*;

class Student {
    private String fullName;
    private int age;
    private String course;

    // Constructor
    public Student(String fullName, int age, String course) {
        this.fullName = fullName;
        this.age = age;
        this.course = course;
    }

    // Getter and Setter methods
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Full Name: " + fullName + ", Age: " + age + ", Course: " + course;
    }
}

public class Main {
    private static final Map<String, Student> studentMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    searchByFullName();
                    break;
                case 3:
                    editStudentInfo();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Student Management System ---");
        System.out.println("1. Add Student");
        System.out.println("2. Search Student by Full Name");
        System.out.println("3. Edit Student Information");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // Clear invalid input
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        return choice;
    }

    private static void addStudent() {
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine().trim();
        System.out.print("Enter age: ");
        int age = getValidAge();
        System.out.print("Enter course: ");
        String course = scanner.nextLine().trim();

        // Add student to map using full name as key
        Student student = new Student(fullName, age, course);
        studentMap.put(fullName, student);
        System.out.println("Student added: " + student);
    }

    private static void searchByFullName() {
        System.out.print("Enter full name to search: ");
        String fullName = scanner.nextLine().trim();

        Student student = studentMap.get(fullName);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("No student found with the name " + fullName);
        }
    }

    private static void editStudentInfo() {
        System.out.print("Enter full name of the student to edit: ");
        String fullName = scanner.nextLine().trim();

        Student student = studentMap.get(fullName);
        if (student != null) {
            System.out.println("Student found: " + student);
            updateStudentDetails(student);
        } else {
            System.out.println("No student found with the name " + fullName);
        }
    }

    private static void updateStudentDetails(Student student) {
        System.out.println("\nEdit Options:");
        System.out.println("1. Edit Name");
        System.out.println("2. Edit Age");
        System.out.println("3. Edit Course");
        System.out.print("Choose an option to edit: ");

        int editChoice = getUserChoice();
        switch (editChoice) {
            case 1:
                System.out.print("Enter new full name: ");
                String newName = scanner.nextLine().trim();
                student.setFullName(newName);
                studentMap.put(newName, student); // Update map with new name as key
                studentMap.remove(student.getFullName()); // Remove old key
                break;
            case 2:
                System.out.print("Enter new age: ");
                int newAge = getValidAge();
                student.setAge(newAge);
                break;
            case 3:
                System.out.print("Enter new course: ");
                String newCourse = scanner.nextLine().trim();
                student.setCourse(newCourse);
                break;
            default:
                System.out.println("Invalid option. No changes made.");
        }
        System.out.println("Updated student: " + student);
    }

    private static int getValidAge() {
        int age;
        while (true) {
            age = getUserChoice();
            if (age > 0) {
                break;
            } else {
                System.out.print("Age must be a positive number. Please enter again: ");
            }
        }
        return age;
    }
}
