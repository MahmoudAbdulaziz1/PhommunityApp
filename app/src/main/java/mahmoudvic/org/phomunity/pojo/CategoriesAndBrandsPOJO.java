package mahmoudvic.org.phomunity.pojo;

import java.util.List;

public class CategoriesAndBrandsPOJO {

    List<CategoriesPOJO> categories;
    List<BrandsPOJO> brands;

    public CategoriesAndBrandsPOJO(List<CategoriesPOJO> categories, List<BrandsPOJO> brands) {
        this.categories = categories;
        this.brands = brands;
    }

    public CategoriesAndBrandsPOJO() {
    }

    public List<CategoriesPOJO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesPOJO> categories) {
        this.categories = categories;
    }

    public List<BrandsPOJO> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandsPOJO> brands) {
        this.brands = brands;
    }
}
