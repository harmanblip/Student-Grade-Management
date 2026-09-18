
import java.io.*;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "data/students.txt";

    // Save all student data to file
    public static synchronized void saveStudents(ArrayList<Student> students) {

        try {
            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdir();
            }

            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter(FILE_NAME))) {

                for (Student student : students) {

                    writer.write(
                            student.getId() + "," +
                            student.getName() + "," +
                            student.getJavaMarks() + "," +
                            student.getMathsMarks() + "," +
                            student.getDigitalMarks()
                    );

                    writer.newLine();
                }
            }

            System.out.println("Student data saved successfully.");

        } catch (IOException e) {
            System.out.println(
                    "Error saving student data: " + e.getMessage()
            );
        }
    }

    // Load student data from file
    public static ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                // Split into maximum 5 parts
                String[] data = line.split(",", 5);

                if (data.length != 5) {
                    System.out.println(
                            "Skipping invalid student record: " + line
                    );
                    continue;
                }

                try {

                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();

                    double javaMarks =
                            Double.parseDouble(data[2].trim());

                    double mathsMarks =
                            Double.parseDouble(data[3].trim());

                    double digitalMarks =
                            Double.parseDouble(data[4].trim());

                    students.add(new Student(
                            id,
                            name,
                            javaMarks,
                            mathsMarks,
                            digitalMarks
                    ));

                } catch (NumberFormatException e) {

                    System.out.println(
                            "Skipping invalid student record: " + line
                    );
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Error loading student data: " + e.getMessage()
            );
        }

        return students;
    }
}
