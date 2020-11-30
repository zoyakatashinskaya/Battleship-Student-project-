import java.util.concurrent.ThreadLocalRandom;

public class Computer extends Player {
    public ComputerBoard board = new ComputerBoard(10);

    public Computer() {
        super(new ComputerBoard(10));
    }

    @Override
    public String getPlayerType() {
        return "Computer";
    }

    @Override
    public void printBoard() {
        this.board.print();
    }

    @Override
    public void createFleet() {
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
    public void updateFleet(Ship ship) {
        super.updateFleet(ship);
        this.board.updateBoard(ship);
    }

    //todo: probably we can change name to generateCoordinates
    private int[][] askForInput(Ship ship){
        /**
         * scans the user input and checks if the format is correct
         * @return string array with coordinates [[0, 1], [0,9]] in correct format (upper case char and a digit)
         */
        while (true){
            //Scanner scanner = new Scanner(System.in);
            //0 => horizontal, 1 => vertical
            int position = ThreadLocalRandom.current().nextInt(0,  2);
            int column = 65;
            int row = 0;
            String start = "";
            String end = "";
            if(position == 0) {
                column += ThreadLocalRandom.current().nextInt(0,  10-ship.getLength());//max is exclusive
                row += ThreadLocalRandom.current().nextInt(0,  10);
                start = (char)column + "" + row;
                end = (char)(column + ship.getLength()-1) + "" + row;
            } else {
                column += ThreadLocalRandom.current().nextInt(0,  10);
                row += ThreadLocalRandom.current().nextInt(0,  10-ship.getLength());//max is exclusive
                start = (char)column + "" + row;
                end = (char)column + "" + (row + ship.getLength()-1);
            }
            System.out.println("Computer generated position " + start + " " + end); //todo: comment ths line out

            String[] coords = {start, end};

            // checks that input is in format "A2 A3"
            if (Validation.checkIfValidInputForShipCoords(coords)) {
                return Utils.parse(coords);
            } else {
                System.out.println("This is an invalid input. Please, try again.");
            }

        }
    }
    // fleet should be generated automatically

    @Override
    public int[] hit(Board opponentBoard) {
        while (true) {
            int column = 65 + ThreadLocalRandom.current().nextInt(0,  10);//max is exclusive
            int row = ThreadLocalRandom.current().nextInt(0,  10);

            String coord =  (char)column + "" + row;
            System.out.println("The computer attacks position: " + coord);

            // checks that input is in format "A2 A3"
            if (Validation.checkIfValidInputForHit(coord)) {
                int[] coords = Utils.coordToIndex(coord);
                int hitStatus = opponentBoard.processHit(coords);
                if(hitStatus != -1) {
                    return coords;
                } else {
                    System.out.println("The computer already entered this input, Trying again");
                }
            } else {
                System.out.println("This is an invalid input. Generate new value");
            }
        }
    }

}
