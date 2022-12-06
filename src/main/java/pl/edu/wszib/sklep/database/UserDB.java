package pl.edu.wszib.sklep.database;

import pl.edu.wszib.sklep.model.Role;
import pl.edu.wszib.sklep.model.User;

public class UserDB {
    private  User[] users = new User[1];
    private static final UserDB instance = new UserDB();


    private UserDB(){
        users[0] = new User("admin","9a58915b0de3f2525c5cf2193903b9fd", Role.ADMIN);
    }
    public static UserDB getInstance(){
        return instance;
    }

    public void register(User user){
        User[] newUsers = new User[this.users.length + 1];
        for(int i = 0; i < this.users.length; i++) {
            newUsers[i] = this.users[i];
        }
        newUsers[newUsers.length - 1] = user;
        this.users = newUsers;
    }
    public User findByLogin(String login) {
        for (User user : this.users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    public User[] getUsers() {
        return users;
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
