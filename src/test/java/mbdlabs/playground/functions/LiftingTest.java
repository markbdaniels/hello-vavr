package mbdlabs.playground.functions;

import io.vavr.API;
import io.vavr.Function2;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static io.vavr.API.println;

public class LiftingTest {

    @Test
    public void testPartialFunctions() {

        Assertions.assertThrows(ArithmeticException.class, () -> {

            Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;

            // 5
            println(divide.apply(10, 2));

            // boom - ArithmeticException
            println(divide.apply(10, 0));


        });

    }

    @Test
    public void testLifting() {


        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide =
                Function2.lift(divide);

        // Some(5)
        println(safeDivide.apply(10, 2));

        // None
        println(safeDivide.apply(10, 0));

    }
}
