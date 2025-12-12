package Logic.Models;

/**
 * Represents a simple user with email and password.
 */
public class User {
    private String email;
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public String getPassword() { return password; }

    /**
     * Checks if the provided credentials match this user.
     */
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
}
