package data;

import business.User;
import java.io.IOException;
import java.sql.*;

/**
 * Data Access Object for user operations.
 * @author Salman
 */
public class UserDAO {
    private final DBConnection dbConnection;

    public UserDAO() throws IOException, ClassNotFoundException, SQLException {
        dbConnection = DBConnection.getInstance();
    }

    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (name, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        }
    }

    /**
     * Gets a user by email and password for authentication.
     * @param email the user's email
     * @return the User object if found, null otherwise
     * @throws SQLException if a database error occurs
     */
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }
            return null;
        }
    }
}