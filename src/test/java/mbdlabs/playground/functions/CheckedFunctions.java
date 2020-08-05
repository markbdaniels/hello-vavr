package mbdlabs.playground.functions;

import io.vavr.CheckedFunction0;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CheckedFunctions {

    @Test
    public void checkedExample() {


        // Standard Java
        Supplier<Stream<String>> supplier1 = null;
//        supplier1 =
//                () -> Files.lines(Paths.get("data.txt"));

        // Vavr
        CheckedFunction0<Stream<String>> supplier2 =
                () -> Files.lines(Paths.get("data.txt"));

        // Can convert it to a unchecked function
        // (which does a sneaky throw)
        // and then assign to a supplier if you wish
        Supplier<Stream<String>> supplier3 =
                supplier2.unchecked();

    }
}
