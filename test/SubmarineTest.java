import org.junit.Test;

import static org.junit.Assert.*;

public class SubmarineTest {
    User testUser = new User();
    Submarine sub = new Submarine("1", testUser);
    int shipLength = sub.getLength();

    @Test
    public void testSubmarineLength(){
        System.out.println("Inside testSubmarineLength()");
        assertEquals( 3, sub.getLength());
    }

    @Test
    public void testReduceShipLength(){
        System.out.println("Length of ship is " + sub.getLength());
        for (int j = 0; j < shipLength; j++) {
            System.out.println("Submarine length -1");
            sub.reduceShipLength();
            System.out.println("Length of ship is " + sub.getLength());
        }
        assertEquals(0, sub.getLength());
    }

    @Test
    public void testIsHitWhenIsHit(){
        int[][] coordinates = new int[2][2];
        coordinates[0][0] = coordinates[0][1] = coordinates[1][0] = 0;
        coordinates[1][1] = 2; // A0 - A2
        int[] hitCoord = new int[2];
        hitCoord[0] = 0;
        hitCoord[1] = 0; //A0
        sub.setCoordinates(coordinates);
        assertTrue(sub.isHit(hitCoord));
        int[] hitWrongCoord = new int[2];
        hitWrongCoord[0] = 1;
        hitWrongCoord[1] = 0;
        assertFalse(sub.isHit(hitWrongCoord));
    }


}