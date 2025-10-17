package profiles;

/**
 * Represents the user's profile information.
 * Stores personal details and avatar settings.
 */
public class Profile {
    private String name;
    private String photo;

    /** Constructor of class Profile */
    public Profile(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    /**
     * Will update profile information such as name and photo.
     */
    public void updateInfo(String newName, String newPhoto) {}
}
