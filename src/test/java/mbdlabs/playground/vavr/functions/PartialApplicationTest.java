package mbdlabs.playground.vavr.functions;

import io.vavr.Function1;
import io.vavr.Function2;
import org.junit.Test;

import static io.vavr.API.println;

public class PartialApplicationTest {

    @Test
    public void test() {

        // Arity: 2
        Function2<Integer, Integer, Integer> sum = (a, b) -> a + b;

        // partial application of 3
        Function1<Integer, Integer> add3 = sum.apply(3);

        // 9
        println(add3.apply(6));
    }
}
