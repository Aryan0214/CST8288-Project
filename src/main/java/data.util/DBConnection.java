/*
File Header
Student Name: Aryan
Student ID: 041141042
Course and Section- CST8288 031
Purpose: The BooksDataSource class implements the Singleton Design Pattern to 
        manage database connections.
*/
package data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class to manage the database connection.
 * @author Aryan and Stanley Pieda
 */
public class DBConnection {
    private static DBConnection instance = null;
    private String url;
    private String username;
    private String password;

    private DBConnection() throws IOException, ClassNotFoundException, SQLException {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Load properties file
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new IOException("Unable to find database.properties");
            }
            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("url");
            username = prop.getProperty("userid");
            password = prop.getProperty("password");
        }

        try (Connection ignored = getConnection()) {
            // Test passed
        }
    }

    /**
     * Returns the singleton instance of the BooksDataSource using database.properties.
     * @return the singleton instance of {@link BooksDataSource}
     */
    public static synchronized DBConnection getInstance() throws IOException, ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    /**
     * Returns the singleton instance of the BooksDataSource using provided credentials.
     * @param user Database username
     * @param pass Database password
     * @return the singleton instance of {@link BooksDataSource}
     */
    public static synchronized DBConnection getInstance(String user, String pass)
            throws IOException, ClassNotFoundException, SQLException {
        DBConnection tempInstance = new DBConnection();

        tempInstance.username = user;
        tempInstance.password = pass;

        try (Connection ignored = DriverManager.getConnection(tempInstance.url, user, pass)) {
            // Connection success — save this instance
            instance = tempInstance;
        }

        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


    public static void clearInstance() {
        instance = null;
    }
}