package mbdlabs.presenation.notes.wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ImperativeExample {

    public static void main(String[] args) throws IOException {

        // count
        Map<String, Integer> wordCount = new HashMap<>();
        try (FileReader fr = new FileReader("./src/main/resources/shakespeare.txt");
             BufferedReader reader = new BufferedReader(fr)) {

            String line = null;
            while ((line = reader.readLine()) != null) {
                for (String word : line.split("\\W")) {
                    String wordUpperCase = word.toUpperCase();
                    Integer count = wordCount.get(wordUpperCase);
                    if (count == null) {
                        wordCount.put(wordUpperCase, 0);
                    } else {
                        wordCount.put(wordUpperCase, count + 1);
                    }
                }
            }
        }

        // print
        ArrayList<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordCount.entrySet());
        wordList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (int i = 0; i < 10; i++) {
            System.out.println(wordList.get(i));
        }
    }
}
