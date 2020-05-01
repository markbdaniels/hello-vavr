package mbdlabs.playground.vavr.functions;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.Test;

import java.util.stream.LongStream;

import static io.vavr.API.println;

public class MemoizationTest {

    @Test
    public void testMemoization() {
        Function0<Double> hashCache = Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        // 0.2662638489408381
        println(randomValue1);
        // 0.2662638489408381
        println(randomValue2);
    }


    @Test
    public void testMemoization2() {

        Function1<Integer, Boolean> isPrime = n -> n > 1 &&
                LongStream.rangeClosed(2, (long) Math.sqrt(n)).noneMatch(div -> n % div == 0);

        // memoize
        Function1<Integer, Boolean> isPrimeMemoized = isPrime.memoized();


        List<Integer> randomIntegers = Stream.continually(() -> (int) (Math.random() * 100))
                .take(100)
                .toList();

        println(randomIntegers.groupBy(isPrimeMemoized::apply));
    }
}
