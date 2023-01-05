package pl.edu.wszib.sklep.database;

import pl.edu.wszib.sklep.model.Role;
import pl.edu.wszib.sklep.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private List<User> users = new ArrayList<>();
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
        User user = users.stream()
                .filter(user1 -> login.equals(user1.getLogin()))
                .findAny()
                .orElse(null);
        return user;
    }

    public void getUsers() {
        System.out.println(users.stream().toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));;
    }

    public boolean ifUserExist(String login) {
        long count = users.stream().
                filter(user -> user.getLogin().equals(login))
                .count();

        if(count == 1){
            return true;
        }
        else{
            return false;
        }

    }

    public boolean grantUser(String name) {
             User user = users.stream()
                     .filter(user1 -> name.equals(user1.getLogin()))
                     .findAny()
                     .orElse(null);

             if (user != null && user.getRole().equals(Role.USER)){
                 user.setRole(Role.ADMIN);
                 return true;
             }
             else{
                 return false;
             }
    }
}
