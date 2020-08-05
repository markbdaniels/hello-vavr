package mbdlabs.playground.collections;

import io.vavr.API;
import io.vavr.collection.Array;
import io.vavr.collection.CharSeq;
import io.vavr.collection.List;
import io.vavr.collection.Stream;
import io.vavr.control.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static io.vavr.API.println;

public class CollectionTest {

    @Test
    public void demo1() {
        Assertions.assertThrows(RuntimeException.class, () -> {

            java.util.List<String> list =
                    Collections.unmodifiableList(new ArrayList<>());

            // Boom!
            list.add("why not?");


        });
    }

    @Test
    public void demo2() {

        // 10 random numbers
        for (double random : Stream.continually(Math::random).take(10)) {
            println(random);
        }

        // Nice
        for (String message : Option.of("Nice")) {
            println(message);
        }

    }

    @Test
    public void demo3() {

        // = ["1", "2", "3"] in Java 8
        java.util.List<String> list1 =
                Arrays.asList(1, 2, 3)
                        .stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());

        // = ["1", "2", "3"] in Vavr
        io.vavr.collection.List<String> list2 =
                List.of(1, 2, 3)
                        .map(Object::toString);

    }

    @Test
    public void demo4() {
        List.of(1).append(2);

        Array.of(1).append(1);

        CharSeq.of("");

    }

    @Test
    public void demo5() {
        API.println(List.rangeClosed(1, 5).take(10));
    }
}
