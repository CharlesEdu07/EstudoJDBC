package br.com.charlesedu.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.charlesedu.db.DB;

public class App {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();

            stmt = conn.prepareStatement(
                    "INSERT INTO seller (Name, Email, Birthdate, BaseSalary, DepartmentId) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, "Hally Grey");
            stmt.setString(2, "hally@gmail.com");
            stmt.setDate(3, new java.sql.Date(sdf.parse("10/07/2002").getTime()));
            stmt.setDouble(4, 1000.0);
            stmt.setInt(5, 1);

            // EXAMPLE 2:
            // st = conn.prepareStatement(
            // "insert into department (Name) values ('D1'),('D2')",
            // Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id: " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
