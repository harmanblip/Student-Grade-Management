public class Student {

    private int id;
    private String name;
    private double javaMarks;
    private double mathsMarks;
    private double digitalMarks;

    public Student(int id, String name, double javaMarks,
                   double mathsMarks, double digitalMarks) {

        this.id = id;
        this.name = name;
        this.javaMarks = javaMarks;
        this.mathsMarks = mathsMarks;
        this.digitalMarks = digitalMarks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getJavaMarks() {
        return javaMarks;
    }

    public double getMathsMarks() {
        return mathsMarks;
    }

    public double getDigitalMarks() {
        return digitalMarks;
    }

    public double getTotal() {
        return javaMarks + mathsMarks + digitalMarks;
    }

    public double getPercentage() {
        return getTotal() / 3;
    }

    public String getGrade() {

        double percentage = getPercentage();

        if (percentage >= 90)
            return "A+";
        else if (percentage >= 80)
            return "A";
        else if (percentage >= 70)
            return "B";
        else if (percentage >= 60)
            return "C";
        else if (percentage >= 50)
            return "D";
        else
            return "F";
    }

    public void displayResult() {

        System.out.println("\n=================================");
        System.out.println("          STUDENT RESULT");
        System.out.println("=================================");
        System.out.println("ID             : " + id);
        System.out.println("Name           : " + name);
        System.out.println("Java           : " + javaMarks);
        System.out.println("Mathematics    : " + mathsMarks);
        System.out.println("Digital Logic  : " + digitalMarks);
        System.out.println("---------------------------------");
        System.out.println("Total          : " + getTotal() + "/300");
        System.out.printf("Percentage     : %.2f%%%n", getPercentage());
        System.out.println("Grade          : " + getGrade());
        System.out.println("=================================");
    }
}