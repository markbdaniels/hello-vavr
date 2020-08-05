package mbdlabs.practical.homework;

import io.vavr.Function1;
import io.vavr.collection.Stream;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number
 * is the sum of the two preceding ones, starting from 0 and 1. That is,
 * <p>
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), for N > 1.
 * Given N, calculate F(N).\
 * <p>
 * Example 1:
 * Input: 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * <p>
 * Example 2:
 * Input: 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * <p>
 * Example 3:
 * Input: 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 * <p>
 * <p>
 * https://leetcode.com/problems/fibonacci-number/
 * <p>
 * Write a fib function in functional programming style.
 */
public class FibonacciTest {


    /**
     * Imperative recursive solution example
     */
    private static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib(n - 2) + fib(n - 1);
    }


    /**
     * Test the imperative solution example
     */
    @Test
    void test_fib() {
        assertThat(fib(0)).isEqualTo(0);
        assertThat(fib(1)).isEqualTo(1);
        assertThat(fib(2)).isEqualTo(1);
        assertThat(fib(3)).isEqualTo(2);
        assertThat(fib(4)).isEqualTo(3);
        assertThat(fib(5)).isEqualTo(5);
        assertThat(fib(6)).isEqualTo(8);
        assertThat(fib(7)).isEqualTo(13);
        assertThat(fib(8)).isEqualTo(21);
        assertThat(fib(9)).isEqualTo(34);
        assertThat(fib(10)).isEqualTo(55);
    }


    /**
     * TODO Implement fib in functional style.
     * <p>
     * Hints -
     * * dont use recursion
     * Look at:
     * ** Stream
     * ** .appendSelf
     * <p>
     */
    private static int fibFunctional(int n) {
        return Stream.of(1, 1)
                .appendSelf(self -> self.zip(self.tail()).map(t -> t._1 + t._2))
                .take(n)
                .lastOption()
                .getOrElse(0);
    }

    /**
     * TODO - make test pass
     */
    @Test
    void test_fibFunctional() {
        assertThat(fibFunctional(0)).isEqualTo(0);
        assertThat(fibFunctional(1)).isEqualTo(1);
        assertThat(fibFunctional(2)).isEqualTo(1);
        assertThat(fibFunctional(3)).isEqualTo(2);
        assertThat(fibFunctional(4)).isEqualTo(3);
        assertThat(fibFunctional(5)).isEqualTo(5);
        assertThat(fibFunctional(6)).isEqualTo(8);
        assertThat(fibFunctional(7)).isEqualTo(13);
        assertThat(fibFunctional(8)).isEqualTo(21);
        assertThat(fibFunctional(9)).isEqualTo(34);
        assertThat(fibFunctional(10)).isEqualTo(55);
    }


    /**
     * If you get that working, try to implement it with Functions and recursion - hint:
     * https://pysaumont.github.io/2014/09/01/Recursive-lambdas-in-Java-8.html
     * <p>
     * Bonus points for making use of memoization
     */
    private static int fibFunctionalRecursive(int n) {
        return fibFunctionalRecursiveMemoized.apply(n);
    }

    private final static Function1<Integer, Integer> fibFunctionalRecursive = (n) -> {
        if (n == 0 || n == 1) {
            return n;
        }
        return FibonacciTest.fibFunctionalRecursiveMemoized.apply(n - 2) + FibonacciTest.fibFunctionalRecursiveMemoized.apply(n - 1);
    };
    private final static Function1<Integer, Integer> fibFunctionalRecursiveMemoized = fibFunctionalRecursive.memoized();

    /**
     * TODO - make test pass
     */
    @Test
    void test_fibFunctionalRecursive() {
        assertThat(fibFunctionalRecursive(0)).isEqualTo(0);
        assertThat(fibFunctionalRecursive(1)).isEqualTo(1);
        assertThat(fibFunctionalRecursive(2)).isEqualTo(1);
        assertThat(fibFunctionalRecursive(3)).isEqualTo(2);
        assertThat(fibFunctionalRecursive(4)).isEqualTo(3);
        assertThat(fibFunctionalRecursive(5)).isEqualTo(5);
        assertThat(fibFunctionalRecursive(6)).isEqualTo(8);
        assertThat(fibFunctionalRecursive(7)).isEqualTo(13);
        assertThat(fibFunctionalRecursive(8)).isEqualTo(21);
        assertThat(fibFunctionalRecursive(9)).isEqualTo(34);
        assertThat(fibFunctionalRecursive(10)).isEqualTo(55);
    }

}
