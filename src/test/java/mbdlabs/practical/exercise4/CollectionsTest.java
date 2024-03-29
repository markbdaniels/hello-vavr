package mbdlabs.practical.exercise4;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.CharSeq;
import io.vavr.collection.IndexedSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;

import static io.vavr.API.CharSeq;
import static io.vavr.API.Some;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 4: Collections
 */
public class CollectionsTest {

    /**
     * create a string ranging from 'a' to 'z'
     */
    @Test
    void test_CharSeq_range() {
        CharSeq rangeClosed = null;
        // TODO

        assertThat(rangeClosed.toString()).isEqualTo("abcdefghijklmnopqrstuvwxyz");
    }

    /**
     * create a string repeating 'a' 3 times
     */
    @Test
    void test_CharSeq_repeat_char() {
        CharSeq repeat = null;
        // TODO

        assertThat(repeat.toString()).isEqualTo("aaa");
    }

    /**
     * repeat vavr 3 times
     */
    @Test
    void test_CharSeq_repeat() {
        CharSeq vavr = CharSeq.of("Vavr");

        CharSeq repeat2 = null;
        // TODO

        assertThat(repeat2.toString()).isEqualTo("VavrVavrVavr");
    }

    /**
     * append character '!' to Vavr
     */
    @Test
    void test_CharSeq_append() {
        CharSeq vavr = CharSeq.of("Vavr");

        CharSeq vavrExclamation = null;
        // TODO

        assertThat(vavrExclamation.toString()).isEqualTo("Vavr!");
    }

    /**
     * append String ' is cool' to vavr
     */
    @Test
    void test_CharSeq_append2() {
        CharSeq vavr = CharSeq.of("Vavr");

        CharSeq vavrIsCool = null;
        // TODO

        assertThat(vavrIsCool.toString()).isEqualTo("Vavr is cool");
    }

    /**
     * get all combinations of oneTwoThree
     */
    @Test
    void test_CharSeq_combinations() {
        CharSeq oneTwoThree = CharSeq.of("123");

        IndexedSeq<CharSeq> combinations = null;
        // TODO

        assertThat(combinations).contains(
                CharSeq('1'),
                CharSeq('2'),
                CharSeq('3'),
                CharSeq("12"),
                CharSeq("13"),
                CharSeq("23"),
                CharSeq("123"));
    }

    /**
     * get all permutations of oneTwoThree
     */
    @Test
    void test_CharSeq_permutations() {
        CharSeq oneTwoThree = CharSeq.of("123");

        IndexedSeq<CharSeq> permutations = null;
        // TODO

        assertThat(permutations).contains(
                CharSeq("123"),
                CharSeq("132"),
                CharSeq("213"),
                CharSeq("231"),
                CharSeq("312"),
                CharSeq("321"));
    }

    /**
     * remove 'a' from vavr
     */
    @Test
    void test_CharSeq_reject() {
        CharSeq vavr = CharSeq.of("Vavr");

        CharSeq filter = null;
        // TODO

        assertThat(filter.toString()).isEqualTo("Vvr");
    }

    /**
     * Pad with ' ' to '      Vavr'
     */
    @Test
    void test_CharSeq_pad() {
        CharSeq vavr = CharSeq.of("Vavr");

        CharSeq pad = null;
        // TODO

        assertThat(pad.toString()).isEqualTo("      Vavr");
    }

    /**
     * Partition the characters into a Tuple2 containing uppercase and lowercase characters
     */
    @Test
    void test_CharSeq_partition() {
        CharSeq vavr = CharSeq.of("Vavr");

        Tuple2<CharSeq, CharSeq> partition = null;
        // TODO

        assertThat(partition).isEqualTo(Tuple.of(CharSeq("V"), CharSeq("avr")));
    }

    /**
     * Reverse oneTwoThree
     */
    @Test
    void test_CharSeq_reverse() {
        CharSeq oneTwoThree = CharSeq.of("123");

        CharSeq reverse = null;
        // TODO

        assertThat(reverse.mkString()).isEqualTo("321");
    }

    /**
     * Rotate oneTwoThree left once
     */
    @Test
    void test_CharSeq_rotate() {
        CharSeq oneTwoThree = CharSeq.of("123");

        CharSeq rotate = null;
        // TODO

        assertThat(rotate.mkString()).isEqualTo("231");
    }

    /**
     * Shuffle oneTwoThree
     */
    @Test
    void test_CharSeq_shuffle() {
        CharSeq oneTwoThree = CharSeq.of("123456789");

        CharSeq shuffle = null;
        // TODO

        assertThat(shuffle.mkString()).isNotEqualTo("123456789");
        assertThat(shuffle.containsAll(CharSeq("123456789"))).isTrue();
    }


    /**
     * Create a list with a range
     */
    @Test
    void test_List_range() {
        List<Integer> list = null;

        // TODO

        assertThat(list).contains(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    /**
     * Map each element by multiplying by 10
     */
    @Test
    void test_List_map() {
        List<Integer> list = List.of(1, 2, 3);

        // TODO

        assertThat(list).contains(10, 20, 30);
    }

    /**
     * Filter foo and create a new list with only negative elements
     */
    @Test
    void test_List_filter() {
        List<Integer> list = List.rangeClosed(-5, 5);

        // TODO

        assertThat(list).isEqualTo(List.of(-5, -4, -3, -2, -1));
    }

    /**
     * Map each element by multiplying by 10
     */
    @Test
    void test_List_sum() {
        List<Integer> list = List.of(1, 2, 3);

        Number sum = null;
        // TODO

        assertThat(sum).isEqualTo(6L);
    }

    /**
     * Extract the smallest integer from list
     */
    @Test
    void test_List_min() {
        List<Integer> list = List.of(1, 2, 3);

        Option<Integer> min = null;
        // TODO

        assertThat(min).isEqualTo(Some(1));
    }

    /**
     * Use a flatmap to expand the String by words and characters
     */
    @Test
    void test_List_flatmap() {
        List<CharSeq> list = List.of(CharSeq("Hello world"));

        List<CharSeq> splitByWord = null;
        List<CharSeq> splitByCharacter = null;

        // TODO

        assertThat(splitByWord).contains(
                CharSeq("Hello"),
                CharSeq("world")
        );

        assertThat(splitByCharacter).contains(
                CharSeq("H"),
                CharSeq("e"),
                CharSeq("l"),
                CharSeq("l"),
                CharSeq("o"),
                CharSeq("w"),
                CharSeq("o"),
                CharSeq("r"),
                CharSeq("l"),
                CharSeq("d")
        );
    }

    /**
     * Prepend 0 to list.
     * Append 4 to list.
     */
    @Test
    void test_List_prepend_append() {
        List<Integer> list = List.of(1, 2, 3);

        // TODO

        assertThat(list).isEqualTo(List.rangeClosed(0, 4));
    }

    /**
     * Remove duplicates
     */
    @Test
    void test_List_distinct() {
        List<Integer> list = List.of(1, 2, 3, 1, 2, 3);

        // TODO

        assertThat(list).isEqualTo(List.rangeClosed(1, 3));
    }

    /**
     * Sum the list by using a fold
     */
    @Test
    void test_List_fold() {
        List<Integer> list = List.rangeClosed(1, 10);

        Integer sum = null;
        // TODO

        assertThat(sum).isEqualTo(55);
    }


    /**
     * Create a Stream 5 elements using range
     */
    @Test
    void test_Stream_range() {

        Stream<Integer> actual = null;

        //TODO

        assertThat(actual).isEqualTo(Stream.of(1, 2, 3, 4, 5));
    }

    /**
     * Create a Stream 5 elements using from
     */
    @Test
    void test_Stream_from() {

        Stream<Integer> actual = null;

        //TODO

        assertThat(actual).isEqualTo(Stream.of(1, 2, 3, 4, 5));
    }

    /**
     * Create a Stream of 5 elements using continually
     */
    @Test
    void test_Stream_continually() {

        Stream<Integer> actual = null;

        // TODO

        assertThat(actual).isEqualTo(Stream.of(1, 1, 1, 1, 1));
    }

    /**
     * Repeat stream 3 times
     */
    @Test
    void test_Stream_cycle() {

        Stream<Integer> stream = Stream.of(1, 2, 3);
        Stream<Integer> actual = null;

        //TODO

        assertThat(actual).isEqualTo(Stream.of(1, 2, 3, 1, 2, 3, 1, 2, 3));
    }
}
