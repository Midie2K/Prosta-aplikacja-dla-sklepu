package pl.edu.wszib.sklep.core;

import pl.edu.wszib.sklep.database.UserDB;
import pl.edu.wszib.sklep.model.User;

public class Authenticator {
    private static final Authenticator instance = new Authenticator();
    public User loggedUser = null;
    public final String seed = "@123LubiePlacki123@";

    private Authenticator(){}
    public void authenticate(User user) {
        UserDB userDB = UserDB.getInstance();
        User userFromDB = userDB.findByLogin(user.getLogin());

        if(userFromDB != null &&
                userFromDB.getPasswd().equals(
                        (user.getPasswd()))) {
            loggedUser = userFromDB;
        }
    }

    public static Authenticator getIstance() {
        return instance;
    }
}
