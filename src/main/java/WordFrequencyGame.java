import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        try {
            //split the input string with 1 to n pieces of spaces
            List<Input> frequencies = countFrequencies(words);
            frequencies.sort((w1, w2) -> Integer.compare(w2.getWordCount(), w1.getWordCount()));
            return composeOutput(frequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private static String composeOutput(List<Input> frequencies) {
        return frequencies.stream()
                .map(w -> w.getValue() + " " + w.getWordCount())
                .collect(Collectors.joining("\n"));
    }

    private List<Input> countFrequencies(String[] words) {
        return Arrays.stream(words)
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().intValue()))
                .collect(Collectors.toList());
    }
}
