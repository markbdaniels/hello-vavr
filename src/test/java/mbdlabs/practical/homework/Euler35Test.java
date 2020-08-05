package mbdlabs.practical.homework;

import io.vavr.Function1;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Euler Problem 35: Circular primes - https://projecteuler.net/problem=35
 * <p>
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 * How many circular primes are there below one million?
 */
public class Euler35Test {


    /**
     * TODO - implement circularPrimes
     * <p>
     * Hint - use:
     * * rotations function defined below
     * * isPrime function defined below
     * * Stream
     * * memoization
     */
    public static int circularPrimes(int n) {
        final Predicate<Integer> memoizedIsPrime = Function1.of(Euler35Test::isPrime).memoized()::apply;
        return Stream.rangeClosed(2, n)
                .filter(memoizedIsPrime)
                .map(Euler35Test::rotations)
                .filter(list -> list.forAll(memoizedIsPrime))
                .length();
    }

    /**
     * TODO - make test pass
     */
    @Test
    void shouldSolveProblem35() {
        assertThat(rotations(197)).isEqualTo(List.of(197, 971, 719));

        assertThat(circularPrimes(100)).isEqualTo(13);
        assertThat(circularPrimes(1000)).isEqualTo(25);
        assertThat(circularPrimes(10000)).isEqualTo(33);
        assertThat(circularPrimes(100000)).isEqualTo(43);
    }

    /**
     * Returns true if n is a prime
     */
    private static boolean isPrime(int n) {
        return n == 2 || n % 2 != 0 &&
                Stream.rangeClosedBy(3, (int) Math.sqrt(n), 2)
                        .find(x -> n % x == 0)
                        .isEmpty();
    }

    /**
     * Returns all possible rotation combinations
     */
    private static List<Integer> rotations(int n) {
        final CharSeq seq = CharSeq.of(String.valueOf(n));
        return Stream.range(0, seq.length())
                .map(i -> seq.drop(i).appendAll(seq.take(i)))
                .map(s -> Integer.valueOf(s.toString()))
                .toList();
    }


}
