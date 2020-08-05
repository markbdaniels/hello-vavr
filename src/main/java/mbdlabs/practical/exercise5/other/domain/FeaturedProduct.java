package mbdlabs.practical.exercise5.other.domain;

import io.vavr.Tuple2;

import java.math.BigDecimal;

public class FeaturedProduct {

    private Product product;
    private BigDecimal discount;

    public FeaturedProduct(Product product, BigDecimal discount) {
        this.product = product;
        this.discount = discount;
    }

    public FeaturedProduct(Tuple2<Product, UserDiscountProfile> productDiscountTuple) {
        this(productDiscountTuple._1, productDiscountTuple._2.getApplicableDiscount());
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "FeaturedProduct{" +
                "product=" + product +
                ", discount=" + discount +
                '}';
    }
}
