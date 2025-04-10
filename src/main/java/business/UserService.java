package business;

import data.UserDAO;
import java.io.IOException;
import java.sql.SQLException;
/**
 * Service class for user-related operations.
 * @author Salman
 */
public class UserService {
    private final UserDAO userDAO;

    /**
     * Constructs a UserService with a UserDAO instance.
     */
    public UserService() throws IOException, ClassNotFoundException, SQLException {
        this.userDAO = new UserDAO();
    }

    public void registerUser(String name, String email, String password, String role) throws SQLException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        userDAO.registerUser(user);
    }

    /**
     * Authenticates a user based on email and password.
     * @param email the user's email
     * @param password the user's password
     * @return the User object if authenticated, null otherwise
     * @throws SQLException if a database error occurs
     */
    public User authenticate(String email, String password) throws SQLException {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}