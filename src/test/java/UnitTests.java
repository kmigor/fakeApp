import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnitTests {
    @Test
    void testOne(){
        Assertions.assertTrue(true);
    }

    @Test
    void testTwo(){
        Assertions.assertEquals(2, 1+1);
    }
}
