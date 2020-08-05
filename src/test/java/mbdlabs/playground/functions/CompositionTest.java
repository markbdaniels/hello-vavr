package mbdlabs.playground.functions;

import io.vavr.Function1;
import org.junit.jupiter.api.Test;

import static io.vavr.API.printf;
import static io.vavr.API.println;

public class CompositionTest {


    @Test
    public void composition() {

        Function1<Integer, Integer> plusOne = a -> a + 1;
        Function1<Integer, Integer> multiplyByTwo = a -> a * 2;
        Function1<Integer, Integer> add1AndMultiplyBy2 = plusOne.andThen(multiplyByTwo);

        // add1AndMultiplyBy2.apply(2) = 6
        println(add1AndMultiplyBy2.apply(2));
    }


    /**
     * calculates the cost of a trip based on distance
     */
    @Test
    public void realWorld_tripCost() {

        Function1<Double, Double> calcLitres = kms -> (kms / 100) * 7.5;
        Function1<Double, Double> calcFuelPrice = litres -> litres * 16.45D;
        // compose
        Function1<Double, Double> calcTripCost = calcLitres.andThen(calcFuelPrice);

        Double tripDistance = 1250D;
        // Cost of 1,250.00KM trip is R1,542.19
        printf("Cost of %,.2fKM trip is R%,.2f", tripDistance, calcTripCost.apply(1250D));
    }

}