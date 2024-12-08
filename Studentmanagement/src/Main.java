import java.util.*;

class Student {
    private String fullName;
    private String lastName;
    private int age;
    private String course;

    // Constructor
    public Student(String fullName, int age, String course) {
        setFullName(fullName);
        this.age = age;
        this.course = course;
    }

    // Getter and Setter methods
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
        String[] names = fullName.trim().split("\\s+");
        this.lastName = names[names.length - 1];
    }

    public String getLastName() {
        return lastName;
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
    private static final List<Student> studentList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    enterStudentList();
                    break;
                case 2:
                    findStudentsByLastName();
                    break;
                case 3:
                    findAndEditStudentByFullName();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nStudent Management System");
        System.out.println("1. Enter student list");
        System.out.println("2. Find students by last name");
        System.out.println("3. Find and edit students by full name");
        System.out.println("4. End");
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

    private static void enterStudentList() {
        System.out.print("Enter the number of students: ");
        int numStudents = getUserChoice();

        for (int i = 0; i < numStudents; i++) {
            System.out.println("\nEntering details for student " + (i + 1));
            System.out.print("Enter full name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = getUserChoice();
            System.out.print("Enter course: ");
            String course = scanner.nextLine();

            Student student = new Student(fullName, age, course);
            studentList.add(student);
            System.out.println("Student added: " + student);
        }
    }

    private static void findStudentsByLastName() {
        System.out.print("Enter last name to search: ");
        String lastName = scanner.nextLine();

        List<Student> matchedStudents = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getLastName().equalsIgnoreCase(lastName)) {
                matchedStudents.add(student);
            }
        }

        if (matchedStudents.isEmpty()) {
            System.out.println("No students found with the last name " + lastName);
        } else {
            System.out.println("Students found:");
            matchedStudents.forEach(System.out::println);
        }
    }

    private static void findAndEditStudentByFullName() {
        System.out.print("Enter full name to search and edit: ");
        String fullName = scanner.nextLine();

        Student studentToEdit = null;
        for (Student student : studentList) {
            if (student.getFullName().equalsIgnoreCase(fullName)) {
                studentToEdit = student;
                break;
            }
        }

        if (studentToEdit != null) {
            System.out.println("Student found: " + studentToEdit);
            editStudent(studentToEdit);
        } else {
            System.out.println("Student not found with full name " + fullName);
        }
    }

    private static void editStudent(Student student) {
        System.out.println("\nEdit Options:");
        System.out.println("1. Edit name");
        System.out.println("2. Edit age");
        System.out.println("3. Edit course");
        System.out.print("Choose option to edit: ");

        int editChoice = getUserChoice();
        switch (editChoice) {
            case 1:
                System.out.print("Enter new full name: ");
                String newName = scanner.nextLine();
                student.setFullName(newName);
                break;
            case 2:
                System.out.print("Enter new age: ");
                int newAge = getUserChoice();
                student.setAge(newAge);
                break;
            case 3:
                System.out.print("Enter new course: ");
                String newCourse = scanner.nextLine();
                student.setCourse(newCourse);
                break;
            default:
                System.out.println("Invalid option. No changes made.");
        }
        System.out.println("Updated student: " + student);
    }
}
