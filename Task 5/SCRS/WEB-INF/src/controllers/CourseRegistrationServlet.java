package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import models.Student;

@WebServlet("/courseRegistration.do")
public class CourseRegistrationServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println(name);
        String email = request.getParameter("email");

        Integer courseId = Integer.parseInt(request.getParameter("course"));
        System.out.println(courseId);

        String nextURL = "index.jsp";
        if (Student.saveStudent(name, courseId, email)) {
            ArrayList<Student> students =  Student.getRecords(email);
            System.out.println(students);
            for(Student student : students) {
                System.out.println(student.getName());
                System.out.println(student.getCourse().getCourseId());
                // System.out.println(student.);
            }
            request.getSession().setAttribute("students", students);
            nextURL = "next.jsp";

        }
        request.getRequestDispatcher(nextURL).forward(request, response);
    }
}
