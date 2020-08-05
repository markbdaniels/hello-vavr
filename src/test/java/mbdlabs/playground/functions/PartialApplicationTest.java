package mbdlabs.playground.functions;

import io.vavr.API;
import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

import static io.vavr.API.List;
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

    @Test
    public void test2() {

        List<Integer> list = List(1, 2, 3);

        Function2<Integer, Integer, Integer> multiply = (a, b) -> a * b;

        // 2, 4, 6
        list.map(i -> multiply.apply(i, 2)).forEach(API::println);

        // Partially apply 2 and create new Function1
        Function1<Integer, Integer> multiplyByTwo = multiply.apply(2);

        // 2, 4, 6
        list.map(multiplyByTwo).forEach(API::println);

    }
}
