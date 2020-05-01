package mbdlabs.playground.vavr.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionTest {

    @Test
    public void test() {
        java.util.List<String> otherList = new ArrayList<>();

        java.util.List<String> list = Collections.unmodifiableList(otherList);

        // Boom!
        list.add("why not?");
    }
}
