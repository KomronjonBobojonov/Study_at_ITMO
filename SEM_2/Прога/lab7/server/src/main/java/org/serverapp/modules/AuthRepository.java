package org.serverapp.modules;

import org.serverapp.managers.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository {

    public AuthRepository(DatabaseManager databaseManager) {
    }

    public String login(DatabaseManager databaseManager, String username, String password) {
        try {
            ResultSet rs = databaseManager.getStatement().executeQuery("SELECT * FROM Users WHERE login = '" + username + "' LIMIT 1;");
            String sel_pass = null;
            while (rs.next()) {
                sel_pass = rs.getString("password");
            }
            rs.close();
            if(sel_pass == null) return insert(databaseManager, username, password);
            return (sel_pass.equals(password)) ? "ok" : "error_password";
        } catch (Exception e) {
            System.out.println(e);
            return "error";
        }
    }
    private String insert(DatabaseManager databaseManager, String login, String password) {
        try (PreparedStatement stmt = databaseManager.getPreparedStatement("INSERT INTO Users(login, password) VALUES (?, ?)"); ) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            if (stmt.executeUpdate() == 0) {
                return "error insert";
            }
            return "ok";
        } catch (SQLException e) {
            System.out.println(e);
            return "error insert";
        }
    }
}

