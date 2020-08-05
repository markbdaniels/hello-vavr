package mbdlabs.playground.values;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static io.vavr.API.List;
import static io.vavr.API.printf;

public class TryTest {

    @Test
    public void test() {

        // Success(https://vavr.io)
        Try<URL> trySuccess = Try.of(() -> new URL("https://vavr.io"));

        // Failure(java.net.MalformedURLException: no protocol: ://vavr.io)
        Try<URL> tryFailure = Try.of(() -> new URL("://vavr.io"));

        List(trySuccess, tryFailure).forEach(t ->
                t.map(Object::toString)
                        .onFailure(err -> printf("ERROR: %s\n", err))
                        .onSuccess(url -> printf("INFO: connecting to %s\n", url))
        );

        // INFO: connecting to https://vavr.io
        // ERROR: java.net.MalformedURLException: no protocol: ://vavr.io

    }
}























