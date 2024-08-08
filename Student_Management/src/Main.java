import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Student {
    private String name;
    private int age;
    private String email;
    private String phone;
    private String code;
    private int gender;
    private float grade;

    public Student(String name, int age, String email, String phone, String code, int gender, float grade) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.code = code;
        this.gender = gender;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public float getGrade() {
        return grade;
    }

    public void display() {
        System.out.println("Name: " + name + ", Age: " + age + ", Email: " + email + ", Phone: " + phone +
                ", Code: " + code + ", Gender: " + (gender == 0 ? "Female" : "Male") + ", Grade: " + grade);
    }
}

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Remove Student by Code");
            System.out.println("4. Sort Students by Grade Descending");
            System.out.println("5. Search Student by Code or Name");
            System.out.println("6. Search Students with Grade >= X");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayAllStudents();
                case 3 -> removeStudentByCode();
                case 4 -> sortStudentsByGradeDescending();
                case 5 -> searchStudentByCodeOrName();
                case 6 -> searchStudentsByGrade();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice, try again.");
            }
        } while (choice != 0);
    }

    private static void addStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter code: ");
        String code = scanner.nextLine();
        System.out.print("Enter gender (0 for Female, 1 for Male): ");
        int gender = scanner.nextInt();
        System.out.print("Enter grade: ");
        float grade = scanner.nextFloat();
        scanner.nextLine(); // Consume newline

        students.add(new Student(name, age, email, phone, code, gender, grade));
        System.out.println("Student added successfully.");
    }

    private static void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student student : students) {
            student.display();
        }
    }

    private static void removeStudentByCode() {
        System.out.print("Enter student code to remove: ");
        String code = scanner.nextLine();
        students.removeIf(student -> student.getCode().equals(code));
        System.out.println("Student removed if it existed.");
    }

    private static void sortStudentsByGradeDescending() {
        students.sort(Comparator.comparing(Student::getGrade).reversed());
        System.out.println("Students sorted by grade in descending order.");
    }

    private static void searchStudentByCodeOrName() {
        System.out.print("Enter student code or name to search: ");
        String input = scanner.nextLine();
        for (Student student : students) {
            if (student.getCode().equals(input) || student.getName().equalsIgnoreCase(input)) {
                student.display();
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private static void searchStudentsByGrade() {
        System.out.print("Enter minimum grade to search: ");
        float x = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        boolean found = false;
        for (Student student : students) {
            if (student.getGrade() >= x) {
                student.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No students found with grade >= " + x);
        }
    }
}