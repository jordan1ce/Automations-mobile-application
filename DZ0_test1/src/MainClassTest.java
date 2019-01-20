import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber() {
        // Можно if не использовать и сразу выводить Assert, но так слишком просто :) Лишний лог своего рода,
        // показывающий реальное значение getLocalNumber.
        if (getLocalNumber() == 14) {
            System.out.println("Value is correct");
        } else {
            System.out.println("Value is failed. Expected 14, but value = " + getLocalNumber());
            Assert.assertTrue("Check value! It != 14", getLocalNumber() == 14);
        }
    }
}

