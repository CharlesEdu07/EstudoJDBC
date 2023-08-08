package br.com.charlesedu.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.charlesedu.db.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();

            stmt = conn.prepareStatement("UPDATE seller SET BaseSalary = BaseSalary + ? WHERE DepartmentId = ?");

            stmt.setDouble(1, 200.0);
            stmt.setInt(2, 2);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
