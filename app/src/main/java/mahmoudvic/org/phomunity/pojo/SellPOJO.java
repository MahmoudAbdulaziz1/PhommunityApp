package mahmoudvic.org.phomunity.pojo;

import java.util.List;

public class SellPOJO {


    private List<SellProductPOJO> products;
    private List<SellSignedProductsPOJO> signedProducts;

    public SellPOJO(List<SellProductPOJO> products, List<SellSignedProductsPOJO> signedProducts) {
        this.products = products;
        this.signedProducts = signedProducts;
    }

    public SellPOJO() {
    }

    public List<SellProductPOJO> getProducts() {
        return products;
    }

    public void setProducts(List<SellProductPOJO> products) {
        this.products = products;
    }

    public List<SellSignedProductsPOJO> getSignedProducts() {
        return signedProducts;
    }

    public void setSignedProducts(List<SellSignedProductsPOJO> signedProducts) {
        this.signedProducts = signedProducts;
    }
}
