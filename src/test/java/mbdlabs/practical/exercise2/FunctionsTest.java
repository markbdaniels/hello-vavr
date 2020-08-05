package mbdlabs.practical.exercise2;

import io.vavr.*;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.LongStream;

import static io.vavr.API.*;
import static io.vavr.API.List;
import static io.vavr.API.println;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 2: Functions
 */
public class FunctionsTest {

    /**
     * Checked functions:
     * Refactor code to replace the urlMapper function (which contains a try/catch) with
     * a Vavr CheckedFunction and then convert the checked function into an unchecked function (sneaky throw)
     * in order to use it as a mapper function on the list
     */
    @Test
    void test_checked_functions() throws MalformedURLException {

        List<String> endpointList = List("https://vavr.io", "https://www.java.com/");


        // TODO refactor this function to remove the try/catch
        Function<String, URL> urlMapper = (s) -> {
            try {
                return new URL(s);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        };

        CheckedFunction1<String, URL> urlMapper2 = URL::new;
        urlMapper = urlMapper2.unchecked();

        List<URL> urlList = endpointList.map(urlMapper);

        assertThat(urlList).contains(new URL("https://vavr.io"));
        assertThat(urlList).contains(new URL("https://www.java.com/"));
    }

    /**
     * Composition:
     * Implement the reverseString and uppercase functions.
     * Then compose the 2 functions into one new one called reverseAndUppercase
     * <p>
     * Hint: look at CharSeq to reverse string
     */
    @Test
    void test_function_composition() {

        Function1<String, String> reverseString = null;
        Function1<String, String> uppercase = null;
        Function1<String, String> reverseThenUppercase = null;

        // TODO
        reverseString = s -> CharSeq.of(s).reverse().toString();
        uppercase = String::toUpperCase;
        reverseThenUppercase = reverseString.andThen(uppercase);

        assertThat(reverseThenUppercase.apply("rvav")).isEqualTo("VAVR");
    }

    /**
     * Lifting ᕦ(ò_óˇ):
     * upper function is a partial function because if we pass null into it we get an exception.
     * Create a total function called upperSafe by 'lifting' upper into a total function.
     * Hint look at Function1
     */
    @Test
    void test_function_lifting() {

        Function1<String, String> upper = String::toUpperCase;
        Function1<String, Option<String>> upperSafe = null;

        // TODO
        upperSafe = Function1.lift(upper);

        assertThat(upperSafe.apply("vavr")).isEqualTo(Option.some("VAVR"));
        assertThat(upperSafe.apply(null)).isEqualTo(Option.none());

    }


    /**
     * Using memoization, implement cachedShuffle function
     * which is cached version of shuffle
     */
    @Test
    void test_memoization1() {

        Function1<String, String> shuffle = s -> CharSeq.of(s)
                .permutations()
                .shuffle()
                .get(0).toString();

        Function1<String, String> cachedShuffle = null;
        // TODO
        cachedShuffle = shuffle.memoized();

        String shuffleABC1 = cachedShuffle.apply("ABC");
        String shuffleABC2 = cachedShuffle.apply("ABC");
        assertThat(shuffleABC1).isEqualTo(shuffleABC2);

        String shuffleXYZ1 = cachedShuffle.apply("XYZ");
        String shuffleXYZ2 = cachedShuffle.apply("XYZ");
        assertThat(shuffleXYZ1).isEqualTo(shuffleXYZ2);
    }


    /**
     * Partial application:
     * Use partial application to create a new function called buildHttpsUri so that you can use
     * method reference instead of lambda expression as a mapper function to build endpointListB
     */
    @Test
    void test_partial_application() {

        List<String> domainList = List("vavr.io", "www.java.com");

        Function2<String, String, String> buildUri = (scheme, domain) -> String.format("%s://%s", scheme, domain);
        List<String> endpointListA = domainList
                .map(domain -> buildUri.apply("https", domain));

        Function1<String, String> buildHttpsUri = null;
        // TODO
        buildHttpsUri = buildUri.apply("https");
        List<String> endpointListB = domainList.map(buildHttpsUri);

        assertThat(endpointListA).contains("https://vavr.io");
        assertThat(endpointListA).contains("https://www.java.com");

        assertThat(endpointListB).contains("https://vavr.io");
        assertThat(endpointListB).contains("https://www.java.com");
    }


    /**
     * Given function isPrime that returns true if the argument is a prime, refactor the code
     * to use memoization in order to cache the isPrime lookups
     */
    @Test
    void test_memoization2() {

        // Invocation counter only here for test purposes in order to count how many times isPrime executed for a particular integer
        Supplier<HashMap<Integer, Integer>> counterSupplier = Function(() -> new HashMap<Integer, Integer>()).memoized();
        Function1<Integer, Integer> invocationCounter = Function((Supplier<HashMap<Integer, Integer>> m, Integer i) -> {
            m.get().merge(i, 1, Integer::sum);
            return i;
        }).apply(counterSupplier);

        // Returns true if argument is prime
        Function1<Integer, Boolean> isPrime = n -> n > 1 &&
                LongStream.rangeClosed(2, (long) Math.sqrt(n)).noneMatch(div -> n % div == 0);

        // compose isPrime function with the counter for testing purposes
        isPrime = invocationCounter.andThen(isPrime);


        // TODO memoize
        isPrime = isPrime.memoized();


        List<Integer> randomIntegers = Stream.continually(() -> (int) (Math.random() * 100))
                .take(1000).toList();

        // print primes
        println(randomIntegers.groupBy(isPrime)
                .getOrElse(true, List())
                .sorted());

        // If counter is greater than 1 for any int then memoization is not working
        assertThat(counterSupplier.get().entrySet().stream()
                .mapToInt(Map.Entry::getValue)
                .filter(c -> c > 1)
                .findFirst().isPresent()
        ).isEqualTo(false);
    }
}
