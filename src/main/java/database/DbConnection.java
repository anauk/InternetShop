package database;

import commodity.Commodity;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbConnection {
    private String path = "jdbc:postgresql://localhost:5432/fs7";
    private String name = "postgres";
    private String password = "secret";
    private Connection connection;

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(path, name, password);
    }

    public Connection connection() {
        if (connection == null) {
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new IllegalStateException("DbConnection went wrong ", e);
            }
        }
        return this.connection;
    }
}


