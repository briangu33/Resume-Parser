import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Gu (brian.gu@addepar.com)
 */
public class KeywordFinder {

    private String text;
    private String keyword;
    private String[] forbiddenSuffixes;
    private String[] optionalSuffixes;

    public KeywordFinder(String keyword, String text) {
        this.keyword = keyword;
        this.text = text;
        this.forbiddenSuffixes = new String[0];
        this.optionalSuffixes = new String[0];
    }

    public KeywordFinder(String keyword, String text, String[] forbiddenSuffixes) {
        this.keyword = keyword;
        this.text = text;
        this.forbiddenSuffixes = forbiddenSuffixes;
        this.optionalSuffixes = new String[0];
    }

    public KeywordFinder(String keyword, String text, String[] forbiddenSuffixes, String[] optionalSuffixes) {
        this.keyword = keyword;
        this.text = text;
        this.forbiddenSuffixes = forbiddenSuffixes;
        this.optionalSuffixes = optionalSuffixes;
    }

    public Map<String, Integer> getInstances() {
        HashMap<String, Integer> instances = new HashMap<String, Integer>();
        instances.put(keyword, 0);
        int mostRecentOccurrenceIndex = -1;
        while (!(text.indexOf(keyword, mostRecentOccurrenceIndex + 1) == -1)) {
            mostRecentOccurrenceIndex = text.indexOf(keyword, mostRecentOccurrenceIndex + 1);

            if (isForbiddenSuffix(mostRecentOccurrenceIndex + keyword.length())) {
                continue;
            }

            instances.put(keyword, instances.get(keyword) + 1);

            for (String optionalSuffix : optionalSuffixes) {
                if (isOptionalSuffix(mostRecentOccurrenceIndex + keyword.length(), optionalSuffix)) {
                    if (instances.containsKey(optionalSuffix)) {
                        instances.put(optionalSuffix, instances.get(optionalSuffix) + 1)
                    } else {
                        instances.put(optionalSuffix, 1);
                    }
                }
            }
        }
        return instances;
    }

    public boolean isOptionalSuffix(int index, String optionalSuffix) {
        if (index + optionalSuffix.length() <= text.length()
                && text.substring(index, index + optionalSuffix.length()).equals(optionalSuffix)) {
            return true;
        }
        return false;
    }

    public boolean isForbiddenSuffix(int index) {
        for (String forbiddenSuffix : forbiddenSuffixes) {
            if (index + forbiddenSuffix.length() <= text.length()
                    && text.substring(index, index + forbiddenSuffix.length()).equals(forbiddenSuffix)) {
                return true;
            }
        }
        return false;
    }

}
