import refactor.Details;
import refactor.InternationalValues;
import refactor.Locale;
import refactor.Translation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class Mapper {
    private static Map<String, Locale> set = new HashMap<>();
    static {
        set.put("getUk", new Locale("en_uk"));
        set.put("getNl", new Locale("nl"));
        set.put("getDe", new Locale("de"));
        set.put("getFr", new Locale("fr"));
        set.put("getEs", new Locale("es"));
        set.put("getIt", new Locale("it"));
        set.put("getAt", new Locale("at"));
        set.put("getChFr", new Locale("ch_fr"));
        set.put("getChDe", new Locale("ch_de"));
        set.put("getChIt", new Locale("ch_it"));
        set.put("getBeFr", new Locale("be_fr"));
        set.put("getLuDe", new Locale("lu_de"));
        set.put("getCaEs", new Locale("ca_es"));
        set.put("getLuFr", new Locale("lu_fr"));
    }

    private static Details callMethod(String method) {
        try {
            Class c = Class.forName("refactor.InternationalValues");
            Object o = c.getDeclaredConstructor().newInstance();
            Method m = c.getDeclaredMethod(method);
            m.setAccessible(true);
            return (Details) m.invoke(o);
        }
        catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
               NoSuchMethodException e) {
            throw  new RuntimeException(e);
        }
    }

    static List<Translation> mapNames(List<InternationalValues> values) throws ClassNotFoundException {
        List<Translation> translations = new ArrayList<>();
        for( Map.Entry<String, Locale> s : set.entrySet()) {
            String name = values.stream()
                    .map(iv -> callMethod(s.getKey()).getName())
                    .distinct().collect(Collectors.joining(" "));
            Translation translation = new Translation(Collections.singletonList(name), s.getValue());
            translations.add(translation);
        }

        return translations;
    }

    static List<Translation> mapDescriptions(List<InternationalValues> values) {
        List<Translation> translations = new ArrayList<>();
        for( Map.Entry<String, Locale> s : set.entrySet()) {
            String description = values.stream()
                    .map(iv -> callMethod(s.getKey()).getDescription().substring(0, Math.min(callMethod(s.getKey()).getDescription().length(), 253))
                    .trim()).findFirst().orElse("<NONE>");
            Translation translation = new Translation(Collections.singletonList(description), s.getValue());
            translations.add(translation);
        }

        return translations;
    }
}
