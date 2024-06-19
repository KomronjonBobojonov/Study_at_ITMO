package org.serverapp.managers;

import java.sql.*;

public class DatabaseManager {
    private final String DB_URL;
    private final String USER;
    private final String PASS;
    private Connection connection = null;

    public DatabaseManager(String DB_URL, String USER, String PASS) {
        this.DB_URL = DB_URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    public boolean initialize() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            return false;
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            getStatement().executeQuery("SELECT pg_catalog.set_config('search_path', 's408281', false);");
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

        if (connection == null) {
            return false;
        }

        return true;
    }

    public boolean initializeTables() {
        try {
            if (!initialize()) {
                System.out.println("Database not connected!");
                System.exit(1);
            }
//
//            var stmt = getStatement();
//            String createUserTable = "CREATE TABLE IF NOT EXISTS Users (" +
//                    "id serial PRIMARY KEY, " +
//                    "login text NOT NULL UNIQUE, " +
//                    "password text NOT NULL);";
//            stmt.execute(createUserTable);

            /*String createTicketType = "CREATE TYPE ticket_type AS ENUM ('VIP', 'USUAL', 'BUDGETARY', 'CHEAP')";
            stmt.execute(createTicketType);
            String createVenueType = "CREATE TYPE venue_type AS ENUM ('PUB', 'OPEN_AREA', 'STADIUM')";
            stmt.execute(createVenueType);*/

//            String createVenueTable = "CREATE TABLE IF NOT EXISTS Venue(" +
//                    "id serial PRIMARY KEY, " +
//                    "name text NOT NULL, " +
//                    "capacity BIGINT not null, " +
//                    "typeVenue venue_type NOT NULL);";
//            stmt.execute(createVenueTable);
//            String createTicketTable = "CREATE TABLE IF NOT EXISTS Ticket(" +
//                    "id serial PRIMARY KEY, " +
//                    "name text NOT NULL, " +
//                    "cor_x BIGINT not null, " +
//                    "cor_y int not null, " +
//                    "creationDate text not null, " +
//                    "price FLOAT not null, " +
//                    "discount INTEGER not null, " +
//                    "refundable BOOLEAN NOT NULL, " +
//                    "typeTicket ticket_type NOT NULL, " +
//                    "venueId INTEGER REFERENCES Venue(id), " +
//                    "ownerId INTEGER REFERENCES Users(id));";
//            stmt.execute(createTicketTable);
//            stmt.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement getPreparedStatement(String s) throws SQLException {
        return connection.prepareStatement(s);
    }

    public PreparedStatement getPreparedStatementRGK(String s) throws SQLException {
        return connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
    }
}
