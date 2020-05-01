package mbdlabs.playground.vavr.values;

import io.vavr.collection.List;
import io.vavr.control.Option;
import org.junit.Test;

import static io.vavr.API.List;

public class OptionTest {

    @Test
    public void testCreate() {

        // Some(foo)
        Option<String> someFoo = Option.of("foo");

        // Some(FOO)
        someFoo.map(String::toUpperCase)
                .filter(s -> s.equals("FOO"));

        // None
        Option<String> noneFoo = Option.of(null);

        // None
        noneFoo = noneFoo.map(String::toUpperCase)
                .filter(s -> s.equals("FOO"));

        //Sorry no FOO
        String out = noneFoo.getOrElse("Sorry no FOO");
    }

    @Test
    public void testFlatmap() {

        List<Option<?>> list = List(
                Option.of("a"), // Some
                Option.of(null), // None
                Option.of("b"),
                Option.of(null),
                Option.of("c"),
                Option.of(null)
        );

        // List(a, b, c)
        List unpacked1 = list
                .filter(Option::isDefined)
                .map(Option::get);

        // List(a, b, c)
        List unpacked2 = list.flatMap(o -> o);
    }
}
