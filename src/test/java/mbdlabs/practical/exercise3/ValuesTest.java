package mbdlabs.practical.exercise3;

import io.vavr.Function1;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.API.printf;
import static io.vavr.Predicates.is;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 3: values
 */
public class ValuesTest {


    /**
     * Option - mapping
     * Map "vavr" to "VAVR"
     */
    @Test
    void test_option_1() {

        Option<String> foo = Option.of("vavr");

        Option<String> actual = null;
        // TODO

        assertThat(actual.get()).isEqualTo("VAVR");
    }

    /**
     * Option - filtering
     * <p>
     * Filter from Some to None
     */
    @Test
    void test_option_filter() {
        Option<String> foo = Option.of("Some");

        Option<String> actual = null;
        // TODO

        assertThat(actual.getOrElse("None")).isEqualTo("None");
    }

    /**
     * Option - flatMap
     * Transform input list into String: "Hello Vavr"
     * <p>
     * Hint: Option implements Iterable so do not use 'isDefined' - use flatmap instead.
     * Flatmap has an effect of applying a one-to-many transformation to the elements
     * In this case each element or Option has either zero of one elements
     * Also look at mkString for combining the strings.
     */
    @Test
    void test_option_flatmap() {

        List<Option<String>> input = List.of(
                Option.of("Hello"),
                Option.of(null),
                Option.of("Vavr")
        );

        String actual = null;
        // TODO

        assertThat(actual).isEqualTo("Hello Vavr");
    }


    /**
     * Try:
     * Wrap dodgyComputation with a Try in order to map a stream of integers to their equivalent word.
     * If dodgyComputation cannot compute the word, then just print an error out to stdout.
     * Otherwise collect the words in a list
     * <p>
     * Hint: As with the test test_option_flatmap - look at flatmap to pull out successful computations.
     * A Success has one element and a Failure has zero elements.
     */
    @Test
    void test_try() {

        Function1<Integer, String> dodgyComputation = (i) ->
                Match(i).of(
                        Case($(is(1)), "one"),
                        Case($(is(2)), "two"),
                        Case($(is(3)), "three"),
                        Case($(is(4)), "four"),
                        Case($(is(5)), "five"),
                        Case($(is(6)), "six"),
                        Case($(), o -> {
                            throw new IllegalArgumentException("" + i);
                        })
                );


        List<String> actual = Stream.rangeClosed(1, 10)
                // TODO
                .map(i -> "")
                .toList();

        List<String> expected = List.of("one", "two", "three", "four", "five", "six");
        assertThat(actual).isEqualTo(expected);
    }


    /**
     * Create a Right Either to represent a successful outcome.
     * Create a Left Either to represent an error outcome.
     */
    @Test
    void test_either_creation() {

        Either<String, Integer> right = null;
        Either<String, Integer> left = null;

        // TODO


        // right
        assertThat(right.getOrElse(-1)).isEqualTo(10);
        assertThat(right.swap().getOrElse("default")).isEqualTo("default");

        // left
        assertThat(left.getOrElse(-1)).isEqualTo(-1);
        assertThat(left.swap().getOrElse("default")).isEqualTo("Represents error");
    }


    /**
     * FizzBuzz
     * Prints numbers from 1 to 100
     * For multiples of 3 prints Fizz
     * For multiples of 5 prints Buzz
     * For multiples of 3 and 5 prints FizzBuzz
     * <p>
     * Given the 5 Functions that return Either<String, Integer> - combine them within a new function called fizzBuzz
     * which takes an int as an argument and returns the applicable String.
     * <p>
     * Hint: chain the Eithers together using either.flatmap().
     * This is known as the railroad pattern - as long as the Either is Right the intermediate steps will continue to to execute.
     * As soon as a solution is found and the Either becomes a Left the remaining intermediate steps will not execute.
     */
    @Test
    void test_either() {

        Function1<Integer, Either<String, Integer>> firstStep = (i) -> Either.right(i);
        Function1<Integer, Either<String, Integer>> checkFizzBuzz = i -> i % 15 == 0 ? Either.left("FizzBuzz") : Either.right(i);
        Function1<Integer, Either<String, Integer>> checkFizz = i -> i % 3 == 0 ? Either.left("Fizz") : Either.right(i);
        Function1<Integer, Either<String, Integer>> checkBuzz = i -> i % 5 == 0 ? Either.left("Buzz") : Either.right(i);
        Function1<Integer, Either<String, Integer>> lastStep = i -> Either.left(i.toString());

        Function1<Integer, String> fizzBuzz = null;
        // TODO


        List<String> out = Stream.rangeClosed(1, 100)
                .map(fizzBuzz)
                .toList();

        assertThat(out.get(0)).isEqualTo("1");
        assertThat(out.get(1)).isEqualTo("2");
        assertThat(out.get(2)).isEqualTo("Fizz");
        assertThat(out.get(3)).isEqualTo("4");
        assertThat(out.get(4)).isEqualTo("Buzz");
        assertThat(out.get(5)).isEqualTo("Fizz");
        assertThat(out.get(6)).isEqualTo("7");
        assertThat(out.get(7)).isEqualTo("8");
        assertThat(out.get(8)).isEqualTo("Fizz");
        assertThat(out.get(9)).isEqualTo("Buzz");
        assertThat(out.get(10)).isEqualTo("11");
        assertThat(out.get(11)).isEqualTo("Fizz");
        assertThat(out.get(12)).isEqualTo("13");
        assertThat(out.get(13)).isEqualTo("14");
        assertThat(out.get(14)).isEqualTo("FizzBuzz");
    }
}
