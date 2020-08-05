package mbdlabs.playground.functions;

import io.vavr.Function1;
import io.vavr.Function3;
import org.junit.jupiter.api.Test;

import static io.vavr.API.println;

public class CurryingTest {

    @Test
    public void test() {

        // Arity: 3
        Function3<Integer, Integer, Integer, Integer> sum =
                (a, b, c) -> a + b + c;

        // Curried
        Function1<Integer, Function1<Integer, Function1<Integer, Integer>>> curried
                = sum.curried();

        // 6
        println(curried.apply(1).apply(2).apply(3));
    }
}
