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

@WebServlet("/withdraw.do")
public class WihdrawMoneyServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Integer balance = (Integer)session.getAttribute("balance");
        int withdraw = Integer.parseInt(request.getParameter("withdraw"));

        ATM atm = (ATM)session.getAttribute("atm");

        BankAccount ba = new BankAccount(atm, balance);

        if(ba.withdrawMoney(withdraw)) {
            request.getRequestDispatcher("main.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("old_customer.jsp").forward(request, response);
        }
    }
}
