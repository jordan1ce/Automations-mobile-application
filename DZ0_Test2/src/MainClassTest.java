import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {
    @Test
    public void testGetClassNumber(){
        MainClass main = new MainClass();
        if (main.getClassNumber() > 45) {
            System.out.println("Value is correct");
        } else {
            System.out.println("Value is failed. Expected value > 45, but it = " + main.getClassNumber());
            Assert.assertTrue("Value is out of 45", main.getClassNumber() > 45);
        }
    }
}
