public class AutoSave implements Runnable {

    private final java.util.ArrayList<Student> students;

    public AutoSave(java.util.ArrayList<Student> students) {
        this.students = students;
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()) {

            try {
                Thread.sleep(30000);

                synchronized (students) {
                    FileManager.saveStudents(students);
                }

                System.out.println(
                        "\n[AutoSave] Student data saved automatically."
                );

            } catch (InterruptedException e) {
                System.out.println("[AutoSave] Auto-save stopped.");
                break;
            }
        }
    }
}