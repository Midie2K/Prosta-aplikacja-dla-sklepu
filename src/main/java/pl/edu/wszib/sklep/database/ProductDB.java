package pl.edu.wszib.sklep.database;

import pl.edu.wszib.sklep.model.*;

import java.util.ArrayList;
import java.util.List;

public class ProductDB {
    private final List<Product> ProductDB= new ArrayList<Product>();
    private static final ProductDB instance = new ProductDB();

    private ProductDB(){
        ProductDB.add(new Gumka());
        ProductDB.add(new Pioro());
        ProductDB.add(new Zeszyt());
        ProductDB.add(new Piornik());
        ProductDB.add(new Plecak());
    }
    public static ProductDB getInstance(){
       return instance;
    }

    public void getProducts() {
        for(Product products : ProductDB){
            System.out.println(products);
        }
    }

    public boolean buyProduct(String name, String quantity) {
        for(Product product : ProductDB) {
            int value = Integer.valueOf(quantity);
            if(product.getName().equals(name) && (value>=0 && value <= product.getQuantity())) {
                product.setQuantity(product.getQuantity()-value);
                return true;
            }
        }
        return false;
    }

    public boolean addQuantity(String name, String quantity) {
        for(Product product : ProductDB) {
            int value = Integer.valueOf(quantity);
            if(product.getName().equals(name) && value>0 ) {
                product.setQuantity(product.getQuantity()+value);
                return true;
            }
        }
        return false;
    }
}
