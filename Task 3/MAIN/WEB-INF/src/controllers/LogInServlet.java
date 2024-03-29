package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ATM;

@WebServlet("/log_in.do")
public class LogInServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String pin = request.getParameter("pin");

        ATM atm = new ATM(name, pin);

        if(atm.login()) {
            session.setAttribute("atm", atm);
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("old_customer.jsp").forward(request, response);
        }
    }
}
