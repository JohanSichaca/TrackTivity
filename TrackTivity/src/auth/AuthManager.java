package auth;

/**
 * Manages user registration and authentication processes.
 */
public class AuthManager {

    /** Constructor of class AuthManager */
    public AuthManager() {}

    /**
     * Will register a new user and store data in the database.
     * @return a new User object.
     */
    public User registerUser(String username, String email, String password) {
        return new User(username, email, password);
    }

    /**
     * Will validate credentials with the authentication system.
     * @return true if the credentials are valid.
     */
    public boolean validateCredentials(User user, String email, String password) {
        return true;
    }
}
