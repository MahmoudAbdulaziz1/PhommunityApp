package mahmoudvic.org.phomunity.pojo;

import java.util.List;

public class RentPojos {
    private List<ProductsPOJO> products;
    private List<SignedProductsPOJO> signedProducts;

    public RentPojos(List<ProductsPOJO> products, List<SignedProductsPOJO> signedProducts) {
        this.products = products;
        this.signedProducts = signedProducts;
    }

    public RentPojos() {
    }

    public List<ProductsPOJO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsPOJO> products) {
        this.products = products;
    }

    public List<SignedProductsPOJO> getSignedProducts() {
        return signedProducts;
    }

    public void setSignedProducts(List<SignedProductsPOJO> signedProducts) {
        this.signedProducts = signedProducts;
    }
}
