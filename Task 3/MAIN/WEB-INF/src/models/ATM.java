package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class ATM {
    static StrongPasswordEncryptor spe = new StrongPasswordEncryptor();

    private Integer ATM_id;
    private String name;
    private String address;
    private String pin;

    public ATM(String name, String address, String pin) {
        this.name = name;
        this.address = address;
        this.pin = pin;
    }

    public ATM(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

    public boolean SaveCustomer() {
        boolean flag = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmdb?user=root&password=1234");

            String query = "insert into atm (name, address, pin) values (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, spe.encryptPassword(pin));

            int result = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                String query2 = "insert into bank_account (ATM_id) values (" + rs.getInt(1) + ")";
                PreparedStatement ps2 = con.prepareStatement(query2);
                int result2 = ps2.executeUpdate();

                if (result2 == 1 && result == 1) {
                    flag = true;
                }
            }

        } catch (ClassNotFoundException | SQLException x) {
            x.printStackTrace();
        }
        return flag;
    }

    public boolean login() {
        boolean flag = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmdb?user=root&password=1234");

            String query = "select ATM_id, pin from atm where name = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                if (spe.checkPassword(pin, rs.getString("pin"))) {
                    flag = true;
                    this.setATM_id(rs.getInt(1));
                    this.pin = null;

                }
            }

        } catch (ClassNotFoundException | SQLException x) {
            x.printStackTrace();
        }

        return flag;
    }

    public Integer getATM_id() {
        return ATM_id;
    }

    public void setATM_id(Integer aTM_id) {
        ATM_id = aTM_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

}
