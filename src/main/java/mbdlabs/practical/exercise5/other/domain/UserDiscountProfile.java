package mbdlabs.practical.exercise5.other.domain;

import java.math.BigDecimal;

public class UserDiscountProfile {

    private boolean isDiscountable;
    private BigDecimal applicableDiscount;

    public UserDiscountProfile(boolean isDiscountable, BigDecimal applicableDiscount) {
        this.isDiscountable = isDiscountable;
        this.applicableDiscount = applicableDiscount;
    }

    public boolean isDiscountable() {
        return isDiscountable;
    }

    public void setDiscountable(boolean discountable) {
        isDiscountable = discountable;
    }

    public BigDecimal getApplicableDiscount() {
        return applicableDiscount;
    }

    public void setApplicableDiscount(BigDecimal applicableDiscount) {
        this.applicableDiscount = applicableDiscount;
    }
}
