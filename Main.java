import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Comparator;

public class Main {

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Thread autoSaveThread;

    public static void main(String[] args) {

        students = FileManager.loadStudents();

        AutoSave autoSave = new AutoSave(students);
        autoSaveThread = new Thread(autoSave);
        autoSaveThread.setDaemon(true);
        autoSaveThread.start();

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("   STUDENT GRADE MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. View Student Result");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students by Percentage");
            System.out.println("7. Class Statistics");
            System.out.println("8. Exit");
            System.out.println("=================================");

            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAllStudents();
                case 3 -> searchStudent();
                case 4 -> viewResult();
                case 5 -> deleteStudent();
                case 6 -> sortStudents();
                case 7 -> showStatistics();
                case 8 -> {
                    FileManager.saveStudents(students);

                    if (autoSaveThread != null) {
                        autoSaveThread.interrupt();
                    }

                    System.out.println("Thank you for using the system!");
                }
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        scanner.close();
    }

    static void addStudent() {

        scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        if (studentIdExists(id)) {
            System.out.println("Error: Student ID already exists.");
            return;
        }

        try {
            double javaMarks = getMarks("Java");
            double mathsMarks = getMarks("Mathematics");
            double digitalMarks = getMarks("Digital Logic");

            Student student = new Student(
                    id, name, javaMarks, mathsMarks, digitalMarks
            );

            synchronized (students) {
                students.add(student);
                FileManager.saveStudents(students);
            }

            System.out.println("Student added successfully!");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static boolean studentIdExists(int id) {

        synchronized (students) {
            for (Student student : students) {
                if (student.getId() == id) {
                    return true;
                }
            }
        }

        return false;
    }

    static double getMarks(String subject)
            throws InvalidMarksException {

        System.out.print("Enter marks in " + subject + ": ");
        double marks = scanner.nextDouble();

        if (marks < 0 || marks > 100) {
            throw new InvalidMarksException(
                    subject + " marks must be between 0 and 100."
            );
        }

        return marks;
    }

    static void viewAllStudents() {

        synchronized (students) {

            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            System.out.println("\n========== ALL STUDENTS ==========");

            for (Student student : students) {
                System.out.printf(
                        "ID: %d | Name: %s | Percentage: %.2f%% | Grade: %s%n",
                        student.getId(),
                        student.getName(),
                        student.getPercentage(),
                        student.getGrade()
                );
            }
        }
    }

    static void searchStudent() {

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        synchronized (students) {

            for (Student student : students) {

                if (student.getId() == id) {
                    System.out.println("Student found!");
                    student.displayResult();
                    return;
                }
            }
        }

        System.out.println("Student not found.");
    }

    static void viewResult() {

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        synchronized (students) {

            for (Student student : students) {

                if (student.getId() == id) {
                    student.displayResult();
                    return;
                }
            }
        }

        System.out.println("Student not found.");
    }

    static void deleteStudent() {

        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        synchronized (students) {

            Iterator<Student> iterator = students.iterator();

            while (iterator.hasNext()) {

                Student student = iterator.next();

                if (student.getId() == id) {

                    iterator.remove();

                    FileManager.saveStudents(students);

                    System.out.println(
                            "Student deleted successfully!"
                    );

                    return;
                }
            }
        }

        System.out.println("Student not found.");
    }

    static void sortStudents() {

        synchronized (students) {

            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            students.sort(
                    Comparator.comparingDouble(Student::getPercentage)
                            .reversed()
            );

            System.out.println("\n===== STUDENTS SORTED BY PERCENTAGE =====");

            for (Student student : students) {
                System.out.printf(
                        "ID: %d | Name: %s | Percentage: %.2f%% | Grade: %s%n",
                        student.getId(),
                        student.getName(),
                        student.getPercentage(),
                        student.getGrade()
                );
            }
        }
    }

    static void showStatistics() {

        synchronized (students) {

            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            double total = 0;
            double highest = students.get(0).getPercentage();
            double lowest = students.get(0).getPercentage();

            String highestStudent = students.get(0).getName();
            String lowestStudent = students.get(0).getName();

            for (Student student : students) {

                double percentage = student.getPercentage();

                total += percentage;

                if (percentage > highest) {
                    highest = percentage;
                    highestStudent = student.getName();
                }

                if (percentage < lowest) {
                    lowest = percentage;
                    lowestStudent = student.getName();
                }
            }

            double average = total / students.size();

            System.out.println("\n========== CLASS STATISTICS ==========");
            System.out.println("Total Students : " + students.size());
            System.out.printf("Class Average  : %.2f%%%n", average);
            System.out.printf(
                    "Highest Score  : %.2f%% (%s)%n",
                    highest,
                    highestStudent
            );
            System.out.printf(
                    "Lowest Score   : %.2f%% (%s)%n",
                    lowest,
                    lowestStudent
            );
        }
    }
}