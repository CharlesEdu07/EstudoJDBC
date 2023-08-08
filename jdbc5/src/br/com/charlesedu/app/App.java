package br.com.charlesedu.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.charlesedu.db.DB;
import br.com.charlesedu.db.DbIntegrityException;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DB.getConnection();

            stmt = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

            stmt.setInt(1, 5);

            int rowsAffected = stmt.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(stmt);
            DB.closeConnection();
        }
    }
}
