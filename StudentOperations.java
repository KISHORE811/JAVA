import java.sql.*;
import java.util.Scanner;

public class StudentOperations {

    
    public static void createStudent(Student student) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO Students (name, age, subject1, subject2, subject3, subject4, subject5, total_marks, average_marks) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, student.name);
                stmt.setInt(2, student.age);
                stmt.setInt(3, student.subject1);
                stmt.setInt(4, student.subject2);
                stmt.setInt(5, student.subject3);
                stmt.setInt(6, student.subject4);
                stmt.setInt(7, student.subject5);
                stmt.setInt(8, student.totalMarks);
                stmt.setDouble(9, student.averageMarks);
                stmt.executeUpdate();
                System.out.println("Student created successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static void readStudent(String studentName) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM Students WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, studentName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    displayStudentData(rs);
                } else {
                    System.out.println("No student found with the given name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    private static void displayStudentData(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        int age = rs.getInt("age");
        int subject1 = rs.getInt("subject1");
        int subject2 = rs.getInt("subject2");
        int subject3 = rs.getInt("subject3");
        int subject4 = rs.getInt("subject4");
        int subject5 = rs.getInt("subject5");
        int totalMarks = rs.getInt("total_marks");
        double averageMarks = rs.getDouble("average_marks");
        Student student = new Student(name, age, subject1, subject2, subject3, subject4, subject5);
        student.totalMarks = totalMarks;
        student.averageMarks = averageMarks;
        student.display();
    }

    public static void readAllStudents() {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM Students";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    displayStudentData(rs);
                    System.out.println("--------------------------------------");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(String studentName) {
        try (Connection conn = DatabaseConnection.connect()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nWhich field would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. Age");
            System.out.println("3. Subject 1");
            System.out.println("4. Subject 2");
            System.out.println("5. Subject 3");
            System.out.println("6. Subject 4");
            System.out.println("7. Subject 5");

            int fieldChoice = scanner.nextInt();
            String sql = "";
            switch (fieldChoice) {
                case 1:
                    sql = "UPDATE Students SET name = ? WHERE name = ?";
                    break;
                case 2:
                    sql = "UPDATE Students SET age = ? WHERE name = ?";
                    break;
                case 3:
                    sql = "UPDATE Students SET subject1 = ?, total_marks = ?, average_marks = ? WHERE name = ?";
                    break;
                case 4:
                    sql = "UPDATE Students SET subject2 = ?, total_marks = ?, average_marks = ? WHERE name = ?";
                    break;
                case 5:
                    sql = "UPDATE Students SET subject3 = ?, total_marks = ?, average_marks = ? WHERE name = ?";
                    break;
                case 6:
                    sql = "UPDATE Students SET subject4 = ?, total_marks = ?, average_marks = ? WHERE name = ?";
                    break;
                case 7:
                    sql = "UPDATE Students SET subject5 = ?, total_marks = ?, average_marks = ? WHERE name = ?";
                    break;
                default:
                    System.out.println("Invalid choice. No update performed.");
                    return;
            }

   
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.print("Enter new value: ");
                if (fieldChoice == 1) { 
                    String newName = scanner.next();
                    stmt.setString(1, newName);
                    stmt.setString(2, studentName);
                } else if (fieldChoice == 2) { 
                    int newAge = scanner.nextInt();
                    stmt.setInt(1, newAge);
                    stmt.setString(2, studentName);
                } else { 
                    int newMark = scanner.nextInt();
                    stmt.setInt(1, newMark);

                    
                    Student student = getStudentByName(conn, studentName);
                    if (student != null) {
                       
                        switch (fieldChoice) {
                            case 3:
                                student.subject1 = newMark;
                                break;
                            case 4:
                                student.subject2 = newMark;
                                break;
                            case 5:
                                student.subject3 = newMark;
                                break;
                            case 6:
                                student.subject4 = newMark;
                                break;
                            case 7:
                                student.subject5 = newMark;
                                break;
                        }

                   
                        student.totalMarks = student.subject1 + student.subject2 + student.subject3 + student.subject4 + student.subject5;
                        student.averageMarks = student.totalMarks / 5.0;

                        stmt.setInt(2, student.totalMarks);
                        stmt.setDouble(3, student.averageMarks);
                        stmt.setString(4, studentName);
                    }
                }

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Student updated successfully!");
                } else {
                    System.out.println("No student found with the given name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Student getStudentByName(Connection conn, String studentName) throws SQLException {
        String sql = "SELECT * FROM Students WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int subject1 = rs.getInt("subject1");
                int subject2 = rs.getInt("subject2");
                int subject3 = rs.getInt("subject3");
                int subject4 = rs.getInt("subject4");
                int subject5 = rs.getInt("subject5");
                return new Student(name, age, subject1, subject2, subject3, subject4, subject5);
            }
        }
        return null;
    }

    public static void deleteStudent(String studentName) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "DELETE FROM Students WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, studentName);
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("No student found with the given name.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}