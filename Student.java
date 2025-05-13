public class Student {
private int id;
private String name;
private int age;
private String gender;
private String major;

public Student() {}

public Student(int id, String name, int age, String gender, String major) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.major = major;
}

// Getters and Setters
public int getId() { return id; }
public void setId(int id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public int getAge() { return age; }
public void setAge(int age) { this.age = age; }
public String getGender() { return gender; }
public void setGender(String gender) { this.gender = gender; }
public String getMajor() { return major; }
public void setMajor(String major) { this.major = major; }
}

src/StudentService.java

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
public void addStudent(Student student) {
try (Connection conn = DBConnection.getConnection()) {
String sql = "INSERT INTO students (name, age, gender, major) VALUES (?, ?, ?, ?)";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, student.getName());
stmt.setInt(2, student.getAge());
stmt.setString(3, student.getGender());
stmt.setString(4, student.getMajor());
stmt.executeUpdate();
System.out.println("Student added successfully.");
} catch (SQLException e) {
e.printStackTrace();
}
}

public Student getStudent(int id) {
    Student student = null;
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            student = new Student(
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("major")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return student;
}

public void updateStudent(Student student) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "UPDATE students SET name=?, age=?, gender=?, major=? WHERE student_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, student.getName());
        stmt.setInt(2, student.getAge());
        stmt.setString(3, student.getGender());
        stmt.setString(4, student.getMajor());
        stmt.setInt(5, student.getId());
        stmt.executeUpdate();
        System.out.println("Student updated successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public void deleteStudent(int id) {
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "DELETE FROM students WHERE student_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        System.out.println("Student deleted successfully.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public List<Student> getAllStudents() {
    List<Student> list = new ArrayList<>();
    try (Connection conn = DBConnection.getConnection()) {
        String sql = "SELECT * FROM students";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Student student = new Student(
                rs.getInt("student_id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("gender"),
                rs.getString("major")
            );
            list.add(student);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
}
