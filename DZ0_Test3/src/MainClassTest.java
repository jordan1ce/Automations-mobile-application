import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test

    public void testGetClassString() {
        if (getClassString() == "hello") {
            System.out.println("We founded hello in method getClassString");
        }
        else if (getClassString() == "Hello"){
            System.out.println("We founded Hello in method getClassString");
        }
        else {
            System.out.println("We couldnt found hello or Hello in method getClassString. We found: " + getClassString());
            Assert.fail("We couldnt found hello or Hello in method getClassString");
        }
    }
}
