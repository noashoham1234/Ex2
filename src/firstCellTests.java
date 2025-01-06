
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This JUnit class represents a very partial test class for Ex1.
 * Make sure you complete all the needed JUnits
 */
public class firstCellTests {
    @Test
    void computeNumberTest() {
//        String s2 = "1011b2";
//        int v = Ex1.number2Int(s2);
//        assertEquals(v, 11);
//        String s10 = "1011bA";
//        v = Ex1.number2Int(s10);
//        s2 = Ex1.int2Number(v, 2);
//        int v2 = Ex1.number2Int(s2);
//        assertEquals(v, v2);
//        assertTrue(Ex1.equals(s10, s2));
    }

    void computeFormTest() {
        assertEquals(7.0, firstCell.computeForm("=1+2*3"));
        assertEquals(9.0, firstCell.computeForm("=(1+2)*3"));
        assertEquals(7.0, firstCell.computeForm("=1+(2*3)/(2-1)"));
        assertEquals(20.0, firstCell.computeForm("=(2+3)*(5-1)"));
        assertEquals(2.0, firstCell.computeForm("=10/(2+3)"));
        assertEquals(17.0, firstCell.computeForm("=10+2*5-3"));
        assertEquals(70.0, firstCell.computeForm("=2*(3+4)*5"));

    }

}
