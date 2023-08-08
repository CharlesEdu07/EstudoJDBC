package br.com.charlesedu.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.charlesedu.db.DB;
import br.com.charlesedu.db.DbException;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            int rows1 = stmt.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            int propositalError = 1;

            if (propositalError == 1) {
                throw new SQLException("Fake error");
            }

            int rows2 = stmt.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            conn.commit();

            System.out.println("Done! Rows affected: " + rows1 + " " + rows2);
            System.out.println("Done! Rows affected: " + rows1 + " " + rows2);
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        } finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
