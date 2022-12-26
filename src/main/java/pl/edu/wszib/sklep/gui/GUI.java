package pl.edu.wszib.sklep.gui;

import org.apache.commons.codec.digest.DigestUtils;
import pl.edu.wszib.sklep.core.Authenticator;
import pl.edu.wszib.sklep.database.ProductDB;
import pl.edu.wszib.sklep.database.UserDB;
import pl.edu.wszib.sklep.model.Product;
import pl.edu.wszib.sklep.model.Role;
import pl.edu.wszib.sklep.model.User;

import java.util.Scanner;

public class GUI {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GUI instance = new GUI();

    private GUI(){}

    public static GUI getInstance(){
        return instance;
    }

    public static String login(){
        System.out.println("1. Register");
        System.out.println("2. Login");
        return scanner.nextLine();
    }
    public static String showMenu() {
        Authenticator authenticator = Authenticator.getIstance();
        System.out.println("1. List products");
        System.out.println("2. Buy product");
        System.out.println("3. Logout");
        System.out.println("4. Exit");
        if (authenticator.loggedUser != null &&
                authenticator.loggedUser.getRole().equals(Role.ADMIN)) {
            System.out.println("5. Add quantity");
            System.out.println("6. List users");
            System.out.println("7. Grant user");
        }
        return scanner.nextLine();
    }

    public static void listProducts() {
        ProductDB productDB = ProductDB.getInstance();
        System.out.println("Name\tPrice\tQuantity");
        productDB.getProducts();
        System.out.println("\n");
    }

    public static void listUsers(){
        UserDB userDB = UserDB.getInstance();
        System.out.println("Login\tRole");
        userDB.getUsers();
        System.out.println("\n");
    }

    public static String readName() {
        System.out.println("Product name:");
        return scanner.nextLine();
    }
    public static String readQunatity() {
        System.out.println("Product quantity:");
        return scanner.nextLine();
    }
    public static String readUser(){
        System.out.println("User name:");
        return scanner.nextLine();
    }

    public static void showBuyResult(boolean result) {
        if (result) {
            System.out.println("Products successful bought\n");
        } else {
            System.out.println("Product does not exist or bad quantity\n");
        }
    }

    public static void showAddResult(boolean result) {
        if (result) {
            System.out.println("Quantity successful added\n");
        } else {
            System.out.println("Product does not exist or bad quantity\n");
        }
    }

    public static void showGrantResult(boolean result) {
        if (result) {
            System.out.println("User Granted successful\n");
        } else {
            System.out.println("User does not exist or is an ADMIN\n");
        }
    }

    public static User readLoginAndPasswd() {
        Authenticator authenticator = Authenticator.getIstance();
        User user = new User();
        System.out.println("Login:");
        user.setLogin(scanner.nextLine());
        System.out.println("Password:");
        user.setPasswd(DigestUtils.md5Hex(scanner.nextLine() + authenticator.seed));
        return user;
    }
    public static void showRegisterResult(boolean result) {
        if (result) {
            System.out.println("Registered successful\n");
        } else {
            System.out.println("Login is taken, try new login\n");
        }
    }
}
