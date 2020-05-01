package mbdlabs.playground.vavr.tuples;

import io.vavr.*;
import org.junit.Test;

import static io.vavr.API.println;

public class TuplesTest {


    public void creation() {
        Tuple1<Integer> tuple1 = Tuple.of(1);
        Tuple2<Integer, Integer> tuple2 = Tuple.of(1, 2);
        // ...
        Tuple8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple8 =
                Tuple.of(1, 2, 3, 4, 5, 6, 7, 8);
    }


    @Test
    public void introduction() {

        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        // "Java"
        String name = java8._1;

        // 8
        Integer version = java8._2;

        // map
        // "(vavr, 1)"
        Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );

        // transform:
        // "vavr 1"
        String transform = java8.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );

    }


    @Test
    public void mapComponentWise() {

        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        // map component wise
        Tuple2<String, Integer> that = java8.map(
                s -> s.substring(2) + "vr",
                i -> i / 8
        );

        // (vavr, 1)
        println(that);
    }

    @Test
    public void map() {

        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        // map
        Tuple2<String, Integer> that = java8.map(
                (s, i) -> Tuple.of(s.substring(2) + "vr", i / 8)
        );

        // (vavr, 1)
        println(that);
    }

    @Test
    public void transform() {

        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        String that = java8.apply(
                (s, i) -> s.substring(2) + "vr " + i / 8
        );

        // "vavr 1"
        println(that);
    }

    @Test
    public void transform2() {

        // (Java, 8)
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);

        Tuple3<String, String, String> that = java8.apply(
                (s, i) -> Tuple.of(s.substring(2) + "vr ", "is", "cool")
        );

        // "(vavr , is, cool)"
        println(that);
    }


    @Test
    public void printLocation() {

        Tuple3<String, Double, Double> location = calculateLocation();

        String message = location.apply(
                (name, lat, lon) ->
                        String.format("Location: %s [%.7f, %.7f]", name, lat, lon)
        );

        // Presentation is located at Discovery Head Office [-26.1130349, 28.0508356]
        println(message);
    }

    private Tuple3<String, Double, Double> calculateLocation() {
        String name = "Discovery Head Office";
        Double latitude = -26.1130349D;
        Double longitude = 28.0508356D;
        return Tuple.of(name, latitude, longitude);
    }


}