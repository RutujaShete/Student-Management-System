import java.util.List;
import java.util.Scanner;

public class StudentManagement {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
StudentService service = new StudentService();
boolean running = true;

    while (running) {
        System.out.println("\nStudent Management System");
        System.out.println("1. Add Student");
        System.out.println("2. View Student");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. View All Students");
        System.out.println("6. Exit");
        System.out.print("Choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> {
                Student s = new Student();
                sc.nextLine();
                System.out.print("Name: ");
                s.setName(sc.nextLine());
                System.out.print("Age: ");
                s.setAge(sc.nextInt());
                sc.nextLine();
                System.out.print("Gender: ");
                s.setGender(sc.nextLine());
                System.out.print("Major: ");
                s.setMajor(sc.nextLine());
                service.addStudent(s);
            }
            case 2 -> {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                Student s = service.getStudent(id);
                if (s != null) {
                    System.out.println("ID: " + s.getId());
                    System.out.println("Name: " + s.getName());
                    System.out.println("Age: " + s.getAge());
                    System.out.println("Gender: " + s.getGender());
                    System.out.println("Major: " + s.getMajor());
                } else {
                    System.out.println("Student not found.");
                }
            }
            case 3 -> {
                Student s = new Student();
                System.out.print("Enter ID: ");
                s.setId(sc.nextInt());
                sc.nextLine();
                System.out.print("New Name: ");
                s.setName(sc.nextLine());
                System.out.print("New Age: ");
                s.setAge(sc.nextInt());
                sc.nextLine();
                System.out.print("New Gender: ");
                s.setGender(sc.nextLine());
                System.out.print("New Major: ");
                s.setMajor(sc.nextLine());
                service.updateStudent(s);
            }
            case 4 -> {
                System.out.print("Enter ID: ");
                service.deleteStudent(sc.nextInt());
            }
            case 5 -> {
                List<Student> list = service.getAllStudents();
                for (Student s : list) {
                    System.out.printf("%d | %s | %d | %s | %s%n", s.getId(), s.getName(), s.getAge(), s.getGender(), s.getMajor());
                }
            }
            case 6 -> running = false;
            default -> System.out.println("Invalid choice.");
        }
    }

    sc.close();
}
}
