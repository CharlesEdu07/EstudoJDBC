package br.com.charlesedu.app;

import java.sql.Connection;

import br.com.charlesedu.db.DB;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = DB.getConnection();

        DB.closeConnection();
    }
}
