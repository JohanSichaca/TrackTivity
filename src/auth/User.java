package auth;

/**
 * Represents a user in the system.
 * Manages authentication and personal information.
 */
public class User {
    private String username;
    private String email;
    private String password;

    /** Constructor of class User */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Will verify user credentials in future versions.
     * @return true if credentials are valid.
     */
    public boolean login(String enteredEmail, String enteredPassword) {
        return true;
    }

    /**
     * Will return the user’s email stored in the database.
     * @return user email.
     */
    public String getEmail() {
        return email;
    }
}
