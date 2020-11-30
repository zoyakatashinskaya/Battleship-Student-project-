public abstract class Ship {
    private int[][] coordinates;
    private int length;
    private String name;
    private String shipNumber;
    private Player player;

    public Ship(int len, String name, String shipNumber, Player player) {
        this.length = len;
        this.name = name;
        this.shipNumber = shipNumber;
        this.player = player; // todo: add subscriber
        this.coordinates = null;
    }

    public int getLength(){
        return this.length;
    }

    public String getName(){
        return this.name;
    }

    public String getDisplayName() {
        return getName() + (getShipNumber() != null && !getShipNumber().isEmpty() ? " " + getShipNumber() : "");
    }

    public  String getShipNumber() {
        return this.shipNumber;
    }

    public String getLetter(){
        return this.name.substring(0, 1);
    }

    public void setCoordinates(int[][] coordinates){
        this.coordinates = coordinates;
    }

    public int[][] getCoordinates(){
        return this.coordinates;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int reduceShipLength() {
        /**
         *
         * If ship is hit it reduces it's length by 1
         * If length == 0 -> update fleet of the player
         */
        this.length = length - 1;
        if (this.length == 0) {
            player.updateFleet(this);
        }
        return this.length;
    }

    public boolean isHit(int[] hitCoord){
        /**
         * check if the ship was hit
         * Ex: coord are [0, 1] (A1)
         * The coordinates of the ship are sotred as [[0, 0], [0, 2]] (A0, A3)
         */
        // has the same column : [[0, 0], [0, 2]] (A0, A3)
        if (this.coordinates[0][0] == this.coordinates[1][0]
                && hitCoord[0] == this.coordinates[0][0]
                && hitCoord[1] >= this.coordinates[0][1]
                && hitCoord[1] <= this.coordinates[1][1]){
            return true;
        }
        // has the same row : A0 B0 [[0, 0], [1, 0]], hit : B0 ([1, 0])
        if (this.coordinates[0][1] == this.coordinates[1][1]
                && hitCoord[1] == this.coordinates[0][1]
                && hitCoord[0] >= this.coordinates[0][0]
                && hitCoord[0] <= this.coordinates[1][0]){
            return true;
        }
        return false;
    }

    public void printShipCoordinates() {
        if(getCoordinates() != null) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.print(" " + getCoordinates()[i][j]);
                }
                System.out.println("");
            }
        }
    }

    public boolean isValidInput(int[][] coordinates){
        /**
         * checks if the given input is valid for the given ship
         */
        if (coordinates[0][0] == coordinates[1][0]){ //if the same row "A2" "A4"
            if (Math.abs(coordinates[0][1]-coordinates[1][1]) == this.length-1){
                return true;
            }
        } else if (coordinates[0][1] == coordinates[1][1]){ //if the same line "A2" "B2"
            if (Math.abs(coordinates[0][0]-coordinates[1][0]) == this.length-1){
                return true;
            }
        }
        System.out.println("This is invalid coordinates for " + this.name);
        return false;
    }


}