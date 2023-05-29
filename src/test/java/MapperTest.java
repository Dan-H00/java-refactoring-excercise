import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import refactor.InternationalValues;
import refactor.Translation;

import java.util.Arrays;
import java.util.List;

public class MapperTest {
    private List<InternationalValues> values;

    @BeforeEach
    public void setUp() {
        values = Arrays.asList(new InternationalValues(), new InternationalValues());
    }

    @Test
    public void testNameMap() throws ClassNotFoundException {
        List<Translation> translations = Mapper.mapNames(values);
        for(Translation tr : translations) {
            System.out.println(tr.getValues());
        }
    }

    @Test
    public void testDescriptionMap() {
        List<Translation> translations = Mapper.mapDescriptions(values);
        for(Translation tr : translations) {
            System.out.println(tr.getValues());
        }
    }
}
