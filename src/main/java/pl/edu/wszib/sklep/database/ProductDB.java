package pl.edu.wszib.sklep.database;

import pl.edu.wszib.sklep.model.Product;

public class ProductDB {
    private final Product[] products=new Product[5];
    private static final ProductDB instance = new ProductDB();

    private ProductDB(){
        this.products[0] = new Product("gumka",1.29,40);
        this.products[1] = new Product("pioro",2.19,50);
        this.products[2] = new Product("zeszyt",4.59,20);
        this.products[3] = new Product("piurnik",12.99,10);
        this.products[4] = new Product("plecak",49.99,5);
    }
    public static ProductDB getInstance(){
       return instance;
    }

    public Product[] getProducts() {
        return products;
    }

    public boolean buyProduct(String name, int quantity) {
        for(Product product : this.products) {
            if(product.getName().equals(name) && (quantity>=0 && quantity <= product.getQuantity())) {
                product.setQuantity(product.getQuantity()-quantity);
                return true;
            }
        }
        return false;
    }

    public boolean addQuantity(String name, int quantity) {
        for(Product product : this.products) {
            if(product.getName().equals(name) && quantity>0 ) {
                product.setQuantity(product.getQuantity()+quantity);
                return true;
            }
        }
        return false;
    }
}
