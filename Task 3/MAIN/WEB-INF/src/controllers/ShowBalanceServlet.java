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

@WebServlet("/show_balance.do")
public class ShowBalanceServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        ATM atm = (ATM)session.getAttribute("atm");

        int balance = BankAccount.fetchBalance(atm);

        session.setAttribute("balance", balance);

        request.getRequestDispatcher("main.jsp").forward(request, response);
    }
}
