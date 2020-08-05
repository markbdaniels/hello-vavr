package mbdlabs.playground.values;

import io.vavr.API;
import io.vavr.Function1;
import io.vavr.concurrent.Future;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static io.vavr.API.List;
import static io.vavr.API.printf;

public class FutureTest {

    @Test
    public void test() {

        // create
        Future<String> future = Future.of(
                () -> Thread.currentThread().getName());

        Future<String> future2 = Future.of(
                Executors.newSingleThreadExecutor(),
                () -> Thread.currentThread().getName());

        // add callback
        future.andThen(API::println);

        // await for computation to complete
        future.await()
                .map(t -> String.format("thread is: %s", t))
                .forEach(API::println);

        // convert to completableFuture
        CompletableFuture<String> completableFuture = future2.toCompletableFuture();

    }
}























