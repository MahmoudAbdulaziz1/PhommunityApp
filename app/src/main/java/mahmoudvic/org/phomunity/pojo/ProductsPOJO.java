package mahmoudvic.org.phomunity.pojo;

import java.util.List;

public class ProductsPOJO {


    private int id;
    private String type;
    private String name;
    private String description;
    private String video_url;
    private double price;
    private List<String> phone_numbers;
    private String sign;
    private List<String> branches;
    private int category_id;
    private int brand_id;
    private int verified;
    private int available;
    private String created_at;
    private String updated_at;
    private CategoryPOJO category;
    private BrandPOJO brand;
    private List<SellImagesPOJO> images;

    public ProductsPOJO(int id, String type, String name, String description, String video_url, double price,
                              List<String> phone_numbers, String sign, List<String> branches,
                              int category_id, int brand_id, int verified, int available, String created_at, String updated_at,
                              CategoryPOJO category, BrandPOJO brand, List<SellImagesPOJO> images) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.video_url = video_url;
        this.price = price;
        this.phone_numbers = phone_numbers;
        this.sign = sign;
        this.branches = branches;
        this.category_id = category_id;
        this.brand_id = brand_id;
        this.verified = verified;
        this.available = available;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.category = category;
        this.brand = brand;
        this.images = images;
    }

    public ProductsPOJO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(List<String> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public CategoryPOJO getCategory() {
        return category;
    }

    public void setCategory(CategoryPOJO category) {
        this.category = category;
    }

    public BrandPOJO getBrand() {
        return brand;
    }

    public void setBrand(BrandPOJO brand) {
        this.brand = brand;
    }

    public List<SellImagesPOJO> getImages() {
        return images;
    }

    public void setImages(List<SellImagesPOJO> images) {
        this.images = images;
    }
}
