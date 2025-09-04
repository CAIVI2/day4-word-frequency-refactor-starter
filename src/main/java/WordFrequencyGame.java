import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String ANY_SAPCE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        if (inputStr.split(ANY_SAPCE_SEPARATOR).length == 1) {
            return inputStr + " 1";
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(ANY_SAPCE_SEPARATOR);

                List<Input> frequencies = countFrequencies(words);

                frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                return composeOutput(frequencies);
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private static String composeOutput(List<Input> frequencies) {
        return frequencies.stream()
                .map(w -> w.getValue() + " " + w.getWordCount())
                .collect(java.util.stream.Collectors.joining("\n"));
    }

    private List<Input> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word, java.util.stream.Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().intValue()))
                .collect(java.util.stream.Collectors.toList());
    }
}
