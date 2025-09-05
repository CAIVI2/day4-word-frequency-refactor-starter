import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String calculateWordFrequencies(String inputStr) {
        String[] words = inputStr.split(ANY_SPACE_SEPARATOR);
        if (words.length == 1) {
            return inputStr + " 1";
        }
        try {
            List<Input> frequencies = countFrequencies(words);
            return composeOutput(frequencies);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String composeOutput(List<Input> frequencies) {
        return frequencies.stream()
                .sorted((w1, w2) -> Integer.compare(w2.wordCount(), w1.wordCount()))
                .map(w -> w.value() + " " + w.wordCount())
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