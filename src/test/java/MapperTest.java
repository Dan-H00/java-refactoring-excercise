import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import refactor.InternationalValues;

import java.util.Arrays;
import java.util.List;

public class MapperTest {
    private List<InternationalValues> values;

    @BeforeEach
    public void setUp() {
        values = Arrays.asList(
                new InternationalValues()
        );
    }

    @Test
    public void testNameMap() {
        System.out.println(Mapper.mapNames(values));
    }

    @Test
    public void testDescriptionMap() {
        System.out.println(Mapper.mapDescriptions(values));
    }
}
