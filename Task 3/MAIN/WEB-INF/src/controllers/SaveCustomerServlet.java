package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ATM;

@WebServlet("/save_customer.do")
public class SaveCustomerServlet extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String fullName = request.getParameter("fullname");
        String address = request.getParameter("address");
        String pin = request.getParameter("pin");

        ATM atm = new ATM(fullName, address, pin);

        boolean flag = atm.SaveCustomer();

        if(flag) {
            request.getRequestDispatcher("old_customer.jsp").forward(request, response);
        }


    }
}
