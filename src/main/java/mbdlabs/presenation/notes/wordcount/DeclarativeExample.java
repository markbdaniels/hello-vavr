package mbdlabs.presenation.notes.wordcount;

import io.vavr.API;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * Found at http://norvig.com/ngrams/shakespeare.txt
 */
public class DeclarativeExample {

    public static void main(String[] args) throws IOException {

        // count
        Map<String, Long> wordCount =
                Files.lines(Paths.get("./src/main/resources/shakespeare.txt"))
                .flatMap(line -> Arrays.stream(line.split("\\W")))
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));

        // print
        wordCount.entrySet().stream()
                .sorted((Map.Entry.<String, Long>comparingByValue().reversed()))
                .limit(10)
                .forEach(API::println);
    }

}
