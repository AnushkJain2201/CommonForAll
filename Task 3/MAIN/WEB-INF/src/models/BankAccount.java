package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankAccount {
    private Integer BankAccount;
    private ATM atm;
    private Integer balance;

    public BankAccount(ATM atm, Integer balance) {
        this.atm = atm;
        this.balance = balance;
    }

    public boolean depositMoney(int deposit) {
        boolean flag = false;

        int total = balance + deposit;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmdb?user=root&password=1234");

            String query = "update bank_account set balance = ? where ATM_id= ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, total);
            ps.setInt(2,  atm.getATM_id());

            int result = ps.executeUpdate();

            if (result == 1) {
                flag = true;
            }

        } catch (ClassNotFoundException | SQLException x) {
            x.printStackTrace();
        }

        return flag;
    }

    public boolean withdrawMoney(int withdraw) {
        boolean flag = false;
        int total = 0;
        if(withdraw <= balance) {
            total = balance - withdraw;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmdb?user=root&password=1234");

            String query = "update bank_account set balance = ? where ATM_id= ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, total);
            ps.setInt(2,  atm.getATM_id());

            int result = ps.executeUpdate();

            if (result == 1) {
                flag = true;
            }

        } catch (ClassNotFoundException | SQLException x) {
            x.printStackTrace();
        }

        return flag;
    }

    public static int fetchBalance(ATM atm) {

        int balance = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmdb?user=root&password=1234");

            String query = "select balance from bank_account where ATM_id = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, atm.getATM_id());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                balance = rs.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException x) {
            x.printStackTrace();
        }

        return balance;
    }

    public Integer getBankAccount() {
        return BankAccount;
    }

    public void setBankAccount(Integer bankAccount) {
        BankAccount = bankAccount;
    }

    public ATM getAtm() {
        return atm;
    }

    public void setAtm(ATM atm) {
        this.atm = atm;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
