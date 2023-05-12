import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
        Mapper mapper = new Mapper();
        System.out.println(mapper.mapNames(values));
    }
}
