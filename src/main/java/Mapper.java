import refactor.Details;
import refactor.InternationalValues;
import refactor.Locale;
import refactor.Translation;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Mapper {
    private static Map<String, Function<InternationalValues, Details>> set = new HashMap<>();
    static {
        set.put("en_uk", InternationalValues::getUk);
        set.put("nl", InternationalValues::getNl);
        set.put("de", InternationalValues::getDe);
        set.put("fr", InternationalValues::getFr);
        set.put("es", InternationalValues::getEs);
        set.put("it", InternationalValues::getIt);
        set.put("at", InternationalValues::getAt);
        set.put("ch_fr", InternationalValues::getChFr);
        set.put("ch_de", InternationalValues::getChDe);
        set.put("ch_it", InternationalValues::getChIt);
        set.put("be_fr", InternationalValues::getBeFr);
        set.put("lux_de", InternationalValues::getLuDe);
        set.put("es_ca", InternationalValues::getCaEs);
        set.put("lux_fr", InternationalValues::getLuFr);
    }

    static List<Translation> mapNames(List<InternationalValues> values) {
        return getValues(values, Details::getName).entrySet().stream().map(v -> {
            String name = v.getValue().stream().distinct().collect(Collectors.joining(" "));
            return new Translation(Collections.singletonList(name), new Locale(v.getKey()));
        }).collect(Collectors.toList());
    }

    static List<Translation> mapDescriptions(List<InternationalValues> values) {
        return getValues(values, Details::getDescription).entrySet().stream().map(v -> {
            String description = v.getValue().stream().findFirst().orElse("");
            return new Translation(Collections.singletonList(description.substring(0, Math.min(description.length(), 253)).trim()), new Locale(v.getKey()));
        }).collect(Collectors.toList());
    }

    private static Map<String, List<String>> getValues(List<InternationalValues> values, Function<Details, String> getDetails) {
        return set.entrySet().stream().map(e -> {
            List<String> extracted = values.stream().map(v -> getDetails.apply(e.getValue().apply(v))).collect(Collectors.toList());
            String locale = e.getKey();
            return Map.entry(locale, extracted);
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
