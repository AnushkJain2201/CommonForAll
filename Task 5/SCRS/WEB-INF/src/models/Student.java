package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student {
    private Integer studentId;
    private String name;
    private String email;
    private Course course;

    public Student(Integer studentId, String name, Course course) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
    }

    public static ArrayList<Student> getRecords(String email){
        ArrayList<Student> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrsdb?user=root&password=1234");
            String query = "  select * from students as s inner join courses as c where s.course_id = c.course_id and email=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);

           ResultSet rs = ps.executeQuery();
           while(rs.next()){
            students.add(new Student(rs.getInt("student_id"),rs.getString("name"),new Course(rs.getInt("course_id"),rs.getString("name"),rs.getString("description"),rs.getInt("capacity"),rs.getInt("schedule"))));
           }
            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return students;
    }
    public static boolean saveStudent(String name, Integer courseId, String email) {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrsdb?user=root&password=1234");
            String query = " insert into students (name,course_id,email) value (?,?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setInt(2, courseId);
            ps.setString(3, email);

           int res= ps.executeUpdate();
            if(res==1){
                flag = true;
            }
            con.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();

        }
        return flag;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
