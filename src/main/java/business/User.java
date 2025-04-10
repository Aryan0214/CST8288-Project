package business;

/**
 * Represents a user in the transit system.
 * @author Anjalika
 */
public class User {
    private String name;
    private String email;
    private String password;
    private String role;

    /**
     * Gets the name
     * @return the name
     */
    public String getName() { return name; }
    /**
     * Sets the name
     * @param name
     */
    public void setName(String name) { this.name = name; }
    /**
     * Gets the email
     * @return the email
     */
    public String getEmail() { return email; }
    /**
     * Sets the email
     * @param email
     */
    public void setEmail(String email) { this.email = email; }
    /**
     * Gets the password
     * @return the email
     */
    public String getPassword() { return password; }
    /**
     * Sets the password
     * @param password
     */
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    /**
     * Sets the role
     * @param role
     */
    public void setRole(String role) { this.role = role; }
}
