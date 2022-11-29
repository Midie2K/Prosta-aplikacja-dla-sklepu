package pl.edu.wszib.sklep.model;

public class User {
    private String login;
    private String passwd;
    private Role role;

    public User() {}

    public User(String login, String passwd, Role role) {
        this.login = login;
        this.passwd = passwd;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.login)
                .append("\t")
                .append(this.role)
                .toString();
    }
}
