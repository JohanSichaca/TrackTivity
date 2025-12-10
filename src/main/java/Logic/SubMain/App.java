package Logic.SubMain;

import Logic.Models.User;
import java.util.List;

public abstract class App {
    protected List<User> users;

    public abstract void loadUsers();
    public abstract void saveUsers();
}
