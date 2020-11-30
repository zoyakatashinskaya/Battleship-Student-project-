import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UserTest {
    User testUser = new User();
    Ship testShip = testUser.fleet.get(0);

    @Test
    public void testUserFleetLength() {
        System.out.println("Inside testUserFleetLength()");
        assertEquals( 1, testUser.getFleetLength());
    }

    @Test
    public void testFleetUpdate() {
        System.out.println("Inside testFleetUpdate()");
        if (testUser.isAlive){
            System.out.println("User is alive");
        }
        testUser.updateFleet(testShip);
        if (!testUser.isAlive){
            System.out.println("User is not alive");
        }
        assertFalse(testUser.isAlive);
    }

}
