package pl.edu.wszib.sklep.core;

import pl.edu.wszib.sklep.database.ProductDB;
import pl.edu.wszib.sklep.database.UserDB;
import pl.edu.wszib.sklep.gui.GUI;
import pl.edu.wszib.sklep.model.Role;
import pl.edu.wszib.sklep.model.User;

public class Core {
    public static void start(){
        final ProductDB productDB = ProductDB.getInstance();
        final UserDB userDB = UserDB.getInstance();
        final Authenticator authenticator = Authenticator.getIstance();
        boolean isRunning = false;
        boolean isLoged = false;
        boolean Exit = false;
        int counter = 0;

        while(!Exit) {
            while (!isLoged) {
                switch (GUI.login()) {
                    case "1":
                        User user = new User();
                        boolean registred = false;
                        do{
                            user=GUI.readLoginAndPasswd();
                            if(!userDB.ifUserExist(user.getLogin())){
                                registred = true;
                            }
                            GUI.showRegisterResult(registred);
                        }while(!registred);
                        userDB.register(user);
                        break;
                    case "2":
                        while (!isRunning && counter < 3) {
                            authenticator.authenticate(GUI.readLoginAndPasswd());
                            isRunning = authenticator.loggedUser != null;
                            isLoged = true;
                            if (!isRunning) {
                                System.out.println("Not authorized !!");
                            }
                            counter++;
                            if(counter==3){
                                Exit=true;
                            }
                        }
                        break;
                    default:
                        System.out.println("Wrong choose !!");
                        break;
                }
            }

            while (isRunning) {
                switch (GUI.showMenu()) {
                    case "1":
                        GUI.listProducts();
                        break;
                    case "2":
                        GUI.showBuyResult(productDB.buyProduct(GUI.readName(), GUI.readQunatity()));
                        break;
                    case "3":
                        isRunning = false;
                        isLoged = false;
                        break;
                    case "4":
                        isRunning = false;
                        Exit = true;
                        break;
                    case "5":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)) {
                            GUI.showAddResult(productDB.addQuantity(GUI.readName(), GUI.readQunatity()));
                        }
                        break;
                    case "6":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                            GUI.listUsers();
                        }
                        break;
                    case "7":
                        if (authenticator.loggedUser != null && authenticator.loggedUser.getRole().equals(Role.ADMIN)){
                            GUI.showGrantResult(userDB.grantUser(GUI.readUser()));
                        }
                        break;
                    default:
                        System.out.println("Wrong choose !!");
                        break;
                }
            }
        }
    }
}
