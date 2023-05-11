package refactor;

import java.util.List;

public class Translation {

    private final List<String> values;
    private final Locale locale;

    public Translation(List<String> values, Locale locale) {
        this.values = values;
        this.locale = locale;
    }

    public List<String> getValues() {
        return values;
    }

    public Locale getLocale() {
        return locale;
    }
}
