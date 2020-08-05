package mbdlabs.practical.exercise5.other.domain;

public class ProductCategory {

    private Long id;

    public ProductCategory(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                '}';
    }
}
