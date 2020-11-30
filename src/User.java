import java.util.Scanner;

public class User extends Player{

    public User() {
        super(new Board(10));
    }

    @Override
    public String getPlayerType() {
        return "User";
    }


    public int[][] askForInput(Ship ship){
        /**
         * scans the user input and checks if the format is correct
         * @return string array with coordinates [[0, 1], [0,9]] in correct format (upper case char and a digit)
         */
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter the position of your " + ship.getName() + " " + ship.getShipNumber() + ": ");

            String start = scanner.next();
            String end = scanner.next();
            String[] coords = {start, end};

            // checks that input is in format "A2 A3"
            if (Validation.checkIfValidInputForShipCoords(coords)) {
                return Utils.parse(coords);
            } else {
                System.out.println("This is an invalid input. Please, try again.");
            }

        }
    }

    @Override
    public void printBoard() {
        this.board.print();
    }

    @Override
    public void createFleet() {
        // the same as was previously in the Game.play()

        for (Ship s : this.fleet){
            while (true){
                // this function automatically checks if the input is formally correct (has char A - J and int 0 - 9)
                int[][] input = askForInput(s); //input is returned in format [[0,1],[0,2]]

                // this checks if the input is correct for the particular ship

                // check if the input is valid for the ship
                if (s.isValidInput(input)){
                    s.setCoordinates(input);
                    //s.printCoordinates();
                    //System.out.println("The input has correct format!");
                }

                //check the input is valid for the Board and insert the ship if the input is valid
                if (this.board.insert(s, input)){
                    //System.out.println("The input is validated by the Board!");
                    break;
                }

                System.out.println("The specified input is invalid");
            }
        }
    }

    @Override
    public int[] hit(Board opponentBoard) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the position you want to attack: ");

            String coord = scanner.next();

            // checks that input is in format "A2 A3"
            if (Validation.checkIfValidInputForHit(coord)) {
                int[] coords = Utils.coordToIndex(coord);
                int hitStatus = opponentBoard.processHit(coords);
                if(hitStatus != -1) {
                    return coords;
                } else {
                    System.out.println("You already entered this input, Please, try again");
                }
            } else {
                System.out.println("This is an invalid input. Please, try again.");
            }
        }
    }

}
