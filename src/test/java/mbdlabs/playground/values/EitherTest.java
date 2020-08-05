package mbdlabs.playground.values;

import io.vavr.API;
import io.vavr.Function2;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

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

        // 2
        List(either1, either2, either3)
                .flatMap(Function.identity())
                .forEach(API::println);
    }
}
