package mbdlabs.practical.homework;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A shop has a coupon system in place. A customer can use more than one coupon for each purchase.
 * <p>
 * Write a function called applyCoupons which returns the total amount due after the discount from coupons are applied.
 * The order of application of the coupons matters - the shop wants to apply the coupons in an order whereby the shop will receive
 * the most money (i.e. smallest discount to customer).
 * <p>
 * What you might use from Vavr:
 * * Function composition
 */
public class CompositionTest {

    enum COUPON {
        AMT_R100_OFF(t -> t - 100),
        AMT_R50_OFF(t -> t - 50),
        PCT_30_OFF(t -> t * 0.70),
        PCT_10_OFF(t -> t * 0.90);

        private Function1<Double, Double> discountFunction;

        COUPON(Function1<Double, Double> discountFunction) {
            this.discountFunction = discountFunction;
        }

        public Function1<Double, Double> getDiscountFunction() {
            return discountFunction;
        }
    }


    /**
     * Implement applyCoupons utilizing function composition
     * <p>
     * Hint - look at permutations and fold to combine coupons
     * <p>
     * TODO implement function
     */
    public static double applyCoupons(double total, List<COUPON> coupons) {
        return 0.00;
    }


    /**
     * TODO - make test pass
     */
    @Test
    void test_applyCoupons() {

        // R100
        double actual = applyCoupons(500, List.of(COUPON.AMT_R100_OFF));
        double expected = 400;
        assertThat(actual).isEqualTo(expected);

        // R50
        actual = applyCoupons(500, List.of(COUPON.AMT_R50_OFF));
        expected = 450;
        assertThat(actual).isEqualTo(expected);

        // 30%
        actual = applyCoupons(500, List.of(COUPON.PCT_30_OFF));
        expected = 350;
        assertThat(actual).isEqualTo(expected);

        // 10%
        actual = applyCoupons(500, List.of(COUPON.PCT_10_OFF));
        expected = 450;
        assertThat(actual).isEqualTo(expected);

        // 30% + R100
        actual = applyCoupons(500, List.of(COUPON.PCT_30_OFF, COUPON.AMT_R100_OFF));
        expected = 280;
        assertThat(actual).isEqualTo(expected);

        // R100 + 30%
        actual = applyCoupons(500, List.of(COUPON.PCT_30_OFF, COUPON.AMT_R100_OFF));
        expected = 280;
        assertThat(actual).isEqualTo(expected);

        // R100 + 30% + 10%
        actual = applyCoupons(500, List.of(COUPON.PCT_30_OFF, COUPON.AMT_R100_OFF, COUPON.PCT_10_OFF));
        expected = 252;
        assertThat(actual).isEqualTo(expected);

        // 10% + 30% + R100
        actual = applyCoupons(500, List.of(COUPON.PCT_10_OFF, COUPON.PCT_30_OFF, COUPON.AMT_R100_OFF));
        expected = 252;
        assertThat(actual).isEqualTo(expected);

        // 10% + 30% + R100 + R50
        actual = applyCoupons(500, List.of(COUPON.PCT_10_OFF, COUPON.PCT_30_OFF, COUPON.AMT_R100_OFF, COUPON.AMT_R50_OFF));
        expected = 220.50;
        assertThat(actual).isEqualTo(expected);


        // R50 + 30% + R100 + 10%
        actual = applyCoupons(500, List.of(COUPON.AMT_R50_OFF, COUPON.PCT_30_OFF, COUPON.AMT_R100_OFF, COUPON.PCT_10_OFF));
        expected = 220.50;
        assertThat(actual).isEqualTo(expected);
    }
}

