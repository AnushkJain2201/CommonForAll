package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ATM;
import models.BankAccount;

@WebServlet("/deposit.do")
public class DepositMoneyServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Integer balance = (Integer)session.getAttribute("balance");
        int deposit = Integer.parseInt(request.getParameter("deposit"));

        ATM atm = (ATM)session.getAttribute("atm");

        BankAccount ba = new BankAccount(atm, balance);

        if(ba.depositMoney(deposit)) {
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("old_customer.jsp").forward(request, response);
        }

    }
}
