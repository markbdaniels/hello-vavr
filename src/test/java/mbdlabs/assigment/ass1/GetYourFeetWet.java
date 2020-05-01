package mbdlabs.assigment.ass1;

import io.vavr.Function1;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import static io.vavr.API.*;
import static io.vavr.Predicates.is;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Assignment 1:
 * <p>
 * A few simple challenges to 'get your feet wet'
 */
public class GetYourFeetWet {


    /**
     * Tuple - transformation
     * <p>
     * Transform the java8 tuple into a String "vavr 1"
     */
    @Test
    void test_tuple_1() {

        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        String actual = null;
        // TODO
        // actual = ...

        assertThat(actual).isEqualTo("vavr 1");
    }


    /**
     * Function - composition
     * <p>
     * Implement the reverseString and uppercase functions.
     * Then compose the 2 functions into one new one called reverseAndUppercase
     */
    @Test
    void test_function_composition() {

        Function1<String, String> reverseString = null;
        Function1<String, String> uppercase = null;
        Function1<String, String> reverseAndUppercase = null;

        // TODO
        // reverseString = ...
        // uppercase = ...
        // reverseAndUppercase = ...

        assertThat(reverseAndUppercase.apply("RVAV")).isEqualTo("VAVR");
    }

    /**
     * Function - memoization
     * <p>
     * Implement cachedShuffle function
     */
    @Test
    void test_function_memoization() {

        Function1<String, String> shuffle = s -> CharSeq.of(s)
                .permutations()
                .shuffle()
                .get(0).toString();

        Function1<String, String> cachedShuffle = null;
        // TODO
        // cachedShuffle = ...

        String shuffleABC1 = cachedShuffle.apply("ABC");
        String shuffleABC2 = cachedShuffle.apply("ABC");
        assertThat(shuffleABC1).isEqualTo(shuffleABC2);

        String shuffleXYZ1 = cachedShuffle.apply("XYZ");
        String shuffleXYZ2 = cachedShuffle.apply("XYZ");
        assertThat(shuffleXYZ1).isEqualTo(shuffleXYZ2);
    }

    /**
     * Option - mapping
     * <p>
     * Map "vavr" to "VAVR"
     */
    @Test
    void test_option_1() {

        Option<String> foo = Option.of("vavr");

        Option<String> actual = null;
        // TODO
        // actual = ...

        assertThat(actual.get()).isEqualTo("VAVR");
    }

    /**
     * Option - filtering
     * <p>
     * Filter from Some to None
     */
    @Test
    void test_option_2() {
        Option<String> foo = Option.of("Some");

        Option<String> actual = null;
        // TODO
        // actual = ...


        assertThat(actual.getOrElse("None")).isEqualTo("None");
    }

    /**
     * Option - flatMap
     * <p>
     * Transform input list into String: "Hello Vavr"
     * <p>
     * (do not use isDefined)
     */
    @Test
    void test_option_3() {

        List<Option<?>> input = List.of(
                Option.of("Hello"),
                Option.of(null),
                Option.of("Vavr"),
                Option.of(null),
                Option.of("World")
        );

        String actual = null;
        // TODO
        // actual = ...

        assertThat(actual).isEqualTo("Hello Vavr");
    }

    /**
     * Try
     * <p>
     * Wrap dodgyComputation with a Try in order to map a stream of integers to their equivalent word.
     * If dodgyComputation cannot compute the word, then just print an error out to stdout.
     * Otherwise collect the words in a list
     */
    @Test
    void test_try_1() {

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
                // a few steps
                .map(e -> "")// remove me - only here to compile
                .toList();

        List<String> expected = List.of("one", "two", "three", "four", "five", "six");
        assertThat(actual).isEqualTo(expected);
    }


    /**
     * Create a Stream 5 elements using range
     */
    @Test
    void test_Stream_1() {

        Stream<Integer> actual = null;
        //TODO
        // actual = ...

        Stream expected = Stream.of(1, 2, 3, 4, 5);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Create a Stream 5 elements using from
     */
    @Test
    void test_Stream_2() {

        Stream<Integer> actual = null;
        //TODO
        // actual = ...

        Stream expected = Stream.of(1, 2, 3, 4, 5);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Create a Stream of 5 elements using continually
     */
    @Test
    void test_Stream_3() {

        Stream<Integer> actual = null;
        // TODO
        // actual = ...

        Stream expected = Stream.of(1, 1, 1, 1, 1);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Repeat stream 3 times
     */
    @Test
    void test_Stream_4() {

        Stream<Integer> stream = Stream.of(1, 2, 3);

        Stream<Integer> actual = null;
        //TODO
        // actual = ...


        Stream expected = Stream.of(1, 2, 3, 1, 2, 3, 1, 2, 3);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Create a new list with elements uppercase
     */
    @Test
    void test_List_1() {

        List<String> foo = List.of("one", "two", "three");

        List<String> actual = null;
        // TODO
        // actual = ...

        List<String> expected = List.of("ONE", "TWO", "THREE");
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Filter foo and create a new list with only negative elements
     */
    @Test
    void test_List_2() {

        List<Integer> foo = Stream.rangeClosed(-5, 5).toList();

        List<Integer> actual = null;
        // TODO
        // actual = ...

        List<Integer> expected = List.of(-5, -4, -3, -2, -1);
        assertThat(actual).isEqualTo(expected);
    }

    /**
     * Sum the elements in the list
     */
    @Test
    void test_List_3() {

        List<Integer> foo = Stream.rangeClosed(0, 5).toList();

        Number actual = null;
        // TODO
        // actual = ...

        Integer expected = 15;
        assertThat(actual.intValue()).isEqualTo(expected.intValue());
    }

    /**
     * Add 6 to end of list
     */
    @Test
    void test_List_4() {

        List<Integer> actual = List.of(1, 2, 3, 4, 5);

        // TODO
        // actual = ...

        assertThat(actual).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    /**
     * Add 0 to begging of list
     */
    @Test
    void test_List_5() {

        List<Integer> actual = List.of(1, 2, 3, 4, 5);

        // TODO
        // actual = ...

        assertThat(actual).isEqualTo(List.of(0, 1, 2, 3, 4, 5));
    }

    /**
     * Reverse list
     */
    @Test
    void test_List_6() {

        List<Integer> actual = List.of(1, 2, 3, 4, 5);

        // TODO
        // actual = ...

        assertThat(actual).isEqualTo(List.of(5, 4, 3, 2, 1));
    }

}
