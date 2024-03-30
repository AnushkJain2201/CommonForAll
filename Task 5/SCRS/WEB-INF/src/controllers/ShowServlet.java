package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import models.Student;

import java.io.IOException;

@WebServlet("/show.do")
public class ShowServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String email = request.getParameter("email");
        System.out.println(email);
        ArrayList<Student> students = new ArrayList<Student>();
        students = Student.getRecords(email);
        request.setAttribute("students", students);
        request.getRequestDispatcher("show.jsp").forward(request, response);
    }
}
