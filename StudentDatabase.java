import java.util.Scanner;

public class StudentDatabase {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Create Student");
            System.out.println("2. Read Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: 
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter marks for Subject 1: ");
                    int subject1 = scanner.nextInt();
                    System.out.print("Enter marks for Subject 2: ");
                    int subject2 = scanner.nextInt();
                    System.out.print("Enter marks for Subject 3: ");
                    int subject3 = scanner.nextInt();
                    System.out.print("Enter marks for Subject 4: ");
                    int subject4 = scanner.nextInt();
                    System.out.print("Enter marks for Subject 5: ");
                    int subject5 = scanner.nextInt();

                    Student newStudent = new Student(name, age, subject1, subject2, subject3, subject4, subject5);
                    StudentOperations.createStudent(newStudent);
                    break;

                case 2:  
                    System.out.print("Enter student name to read: ");
                    String Read = scanner.next();
                    StudentOperations.readStudent(Read);
                    break;

                case 3:  
                    System.out.print("Enter student name to update: ");
                    String Update = scanner.next();
                    StudentOperations.updateStudent(Update);
                    break;

                case 4:  
                    System.out.print("Enter student name to delete: ");
                    String Delete = scanner.next();
                    StudentOperations.deleteStudent(Delete);
                    break;

                case 5:  
                    System.out.println("\nDisplaying all students:");
                    StudentOperations.readAllStudents();
                    break;

                case 6:  
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
