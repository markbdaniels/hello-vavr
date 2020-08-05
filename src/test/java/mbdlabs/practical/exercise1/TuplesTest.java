package mbdlabs.practical.exercise1;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Exercise 1: Tuples
 */
public class TuplesTest {


    /**
     * Create a tuple using the static factory on 'Tuple'
     */
    @Test
    void test_tuple_create() {
        Tuple3<String, String, Integer> user = null;

        // TODO
        user = Tuple.of("Sade", "Clayton", 26);

        assertThat(user._1).isEqualTo("Sade");
        assertThat(user._2).isEqualTo("Clayton");
        assertThat(user._3).isEqualTo(26);
    }

    /**
     * Using map function, switch first name and last name around
     */
    @Test
    void test_tuple_map() {
        Tuple3<String, String, Integer> user = Tuple.of("Fatema", "Lynn", 32);

        // TODO
        user = user.map(
                (f, l, a) -> Tuple.of(l, f, a)
        );

        assertThat(user._1).isEqualTo("Lynn");
        assertThat(user._2).isEqualTo("Fatema");
        assertThat(user._3).isEqualTo(32);
    }


    /**
     * Transform (apply) the user tuple into a String "Jae Diaz age: 28".
     */
    @Test
    void test_tuple_transform() {

        Tuple3<String, String, Integer> user = Tuple.of("Jae", "Diaz", 28);

        String actual = null;
        // TODO
        actual = user.apply(
                (f, l, a) -> String.format("%s %s age: %d", f, l, a)
        );

        assertThat(actual).isEqualTo("Jae Diaz age: 28");
    }

    /**
     * Update the age of the user from 19 to 26
     */
    @Test
    void test_tuple_update() {

        Tuple3<String, String, Integer> user = Tuple.of("Fahad", "Copeland", 19);

        // TODO
        user = user.update3(26);

        assertThat(user._3).isEqualTo(26);
    }


    /**
     * Append an age to the tuple
     */
    @Test
    void test_tuple_append() {

        Tuple2<String, String> user = Tuple.of("Samera", "Hurst");

        Tuple3<String, String, Integer> actual = null;
        // TODO
        actual = user.append(30);

        assertThat(actual).isEqualTo(Tuple.of("Samera", "Hurst", 30));
    }

    /**
     * Concat an age to the tuple
     */
    @Test
    void test_tuple_concat() {

        Tuple2<String, String> user = Tuple.of("Samera", "Hurst");

        Tuple3<String, String, Integer> actual = null;
        // TODO
        actual = user.concat(Tuple.of(30));

        assertThat(actual).isEqualTo(Tuple.of("Samera", "Hurst", 30));
    }

}
