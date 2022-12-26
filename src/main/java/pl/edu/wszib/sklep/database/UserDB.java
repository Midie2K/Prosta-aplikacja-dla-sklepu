package pl.edu.wszib.sklep.database;

import pl.edu.wszib.sklep.model.Role;
import pl.edu.wszib.sklep.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private List<User> users = new ArrayList<User>();
    private static final UserDB instance = new UserDB();


    private UserDB(){
        users.add(new User("admin","9a58915b0de3f2525c5cf2193903b9fd", Role.ADMIN));
    }
    public static UserDB getInstance(){
        return instance;
    }

    public void register(User user){
        users.add(user);
    }
    public User findByLogin(String login) {
        for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public void getUsers() {
        for(User user:users){
            System.out.println(user);
        }
    }

    public boolean ifUserExist(String login) {
        for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public boolean grantUser(String name) {
        for(User user : this.users) {
            if(user.getLogin().equals(name) && user.getRole().equals(Role.USER)) {
                user.setRole(Role.ADMIN);
                return true;
            }
        }
        return false;
    }
}
