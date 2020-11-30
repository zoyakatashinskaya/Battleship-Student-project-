public class Main {
    public static void main(String[] args) {

        //hello message
        System.out.println("Welcome to Battleship!!!\n");

        Game newGame = Game.getInstance();
        newGame.play();
    }
}

// input from the assignment
// E9 J9 //E7 J7
// A2 A5
// G0 J0
// D0 D2 //submarine
// F2 H2 //submarine
// J5 J7 //submarine
// A0 B0
// A7 B7
// F5 F6
// J2 J3