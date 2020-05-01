package mbdlabs.playground.vavr.values;

import io.vavr.Function2;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.junit.Test;

import static io.vavr.API.List;

public class EitherTest {

    @Test
    public void testEither() {

        // Divide function which returns Either
        Function2<Integer, Integer, Either<String, Integer>> divide =
                (a, b) -> Try.of(() -> a / b)
                        .toEither()
                        .mapLeft(Throwable::getMessage);


        // Right(2)
        Either<String, Integer> either1 = divide.apply(10, 5);

        // Left(/ by zero)
        Either<String, Integer> either2 = divide.apply(10, 0);

        // Left(null)
        Either<String, Integer> either3 = divide.apply(10, null);

        List(either1, either2, either3).forEach(e -> {
            e.left().map(s -> String.format("ERROR: %s", s)).forEach(System.out::println);
            e.right().map(r -> String.format("INFO: %s", r)).forEach(System.out::println);
        });

    }
}
