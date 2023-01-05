package pl.edu.wszib.sklep.database;

import pl.edu.wszib.sklep.model.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private static final ProductDB instance = new ProductDB();
    private final List<Product> products= new ArrayList<>();

    private ProductDB(){
        products.add(new Gumka());
        products.add(new Pioro());
        products.add(new Zeszyt());
        products.add(new Piornik());
        products.add(new Plecak());
    }
    public static ProductDB getInstance(){
       return instance;
    }

    public void getProducts() {
        System.out.println(products.stream().toList().
                toString().replace("[","").
                replace("]","").
                replace(", ","\n"));
    }

    public boolean buyProduct(String name, String quantity) {
        int value = Integer.valueOf(quantity);

            Product product = products.stream()
                    .filter(product1 -> name.equals(product1.getName()))
                    .findAny().
                    orElse(null);

            if (product != null && (value>=0 && value <= product.getQuantity())){
                product.setQuantity(product.getQuantity() - value);
                return true;
            }
            else{
                return false;
            }
    }

    public boolean addQuantity(String name, String quantity) {
        int value = Integer.valueOf(quantity);

        Product product = products.stream()
                .filter(product1 -> name.equals(product1.getName()))
                .findAny().
                orElse(null);

        if (product != null && value > 0) {
            product.setQuantity(product.getQuantity() + value);
            return true;
        }
        else {
            return false;
        }
    }
}
