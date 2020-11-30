public class Validation {
    // make static methods

    public boolean isValidInput(Ship ship, int[][] coordinates){
        /**
         * checks if the given input is valid for the given ship
         */
        if (coordinates[0][0] == coordinates[1][0]){ //if the same row "A2" "A4"
            if (Math.abs(coordinates[0][1]-coordinates[1][1]) == ship.getLength()-1){
                return true;
            }
        } else if (coordinates[0][1] == coordinates[1][1]){ //if the same line "A2" "B2"
            if (Math.abs(coordinates[0][0]-coordinates[1][0]) == ship.getLength()-1){
                return true;
            }
        }
        System.out.println("This is invalid coordinates for " + ship.getName());
        return false;
    }

    public static Boolean checkIfValidInputForShipCoords(String[] coordinates){
        /**
         * this method checks if the given input is in correct format (upper case char and a digit)
         */

        for (String x : coordinates) {
            // each coordinate is length 2
            if (x.length() != 2) {
                System.out.println("Address is too long");
                return Boolean.FALSE;
            }
            // first char A to J and second is a digit O to 9
            if (x.charAt(0) < 'A' || x.charAt(0) > 'J' || x.charAt(1) < '0' || x.charAt(1) > '9') {
                System.out.println("Address is incorrect");
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public static Boolean checkIfValidInputForHit(String coord){
        if (coord.length() != 2) {
            System.out.println("Address is too long");
            return Boolean.FALSE;
        }

        if (coord.charAt(0) < 'A' || coord.charAt(0) > 'J' || coord.charAt(1) < '0' || coord.charAt(1) > '9') {
            System.out.println("Address is incorrect");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
