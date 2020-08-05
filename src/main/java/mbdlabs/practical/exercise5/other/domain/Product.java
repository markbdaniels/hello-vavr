package mbdlabs.practical.exercise5.other.domain;

public class Product {

    private Long id;
    private ProductCategory productCategory;

    public Product(Long id, ProductCategory productCategory) {
        this.id = id;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productCategory=" + productCategory +
                '}';
    }
}
