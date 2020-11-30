import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

abstract public class Player implements UpdateFleet {
    protected Board board;
    protected List<Ship> fleet = new ArrayList<>();
    protected Boolean isAlive;

    public Player(Board board) {
        this.isAlive = true;
        this.board = board;
        initFleet(this.fleet);
    }

    private void initFleet(List<Ship> fleet){
        // create a list of ships for every Player
        Carrier c1 = new Carrier(this);
        fleet.add(c1);
        BattleShip b1 = new BattleShip("1", this);
        fleet.add(b1);
        BattleShip b2 = new BattleShip("2", this);
        fleet.add(b2);
        Submarine s1 = new Submarine("1", this);
        fleet.add(s1);
        Submarine s2 = new Submarine("2", this);
        fleet.add(s2);
        Submarine s3 = new Submarine("3", this);
        fleet.add(s3);
        PatrolBoat pb1 = new PatrolBoat("1", this);
        fleet.add(pb1);
        PatrolBoat pb2 = new PatrolBoat("2", this);
        fleet.add(pb2);
        PatrolBoat pb3 = new PatrolBoat("3", this);
        fleet.add(pb3);
        PatrolBoat pb4 = new PatrolBoat("4", this);
        fleet.add(pb4);

    }

    public abstract String getPlayerType();

    public int getFleetLength(){
        return this.fleet.size();
    }

    public boolean getIsAlive(){
        return isAlive;
    }

    public abstract void createFleet(); //implementation would be different for user and computer

    abstract public void printBoard();// different implementation for computer and user

    @Override
    public void updateFleet(Ship ship){
        fleet.remove(ship);
        if (fleet.isEmpty()){
            this.isAlive = false;
        }
    }

    public Ship wasAttacked(int[] coord){
        /**
         * If the rival hit your ship iterate over all ships in the fleet and find the one that was hit
         */
        Iterator itr = fleet.iterator();
        while (itr.hasNext()){
            Ship s = (Ship) itr.next();
            if (s.isHit(coord)){
                s.reduceShipLength();
                return s;
            }
        }
        return null;
        /*
        for (Ship s: fleet) {
            if (s.isHit(coord)){
                s.reduceShipLength();
                break;
            }
        }
        */
    }

    public abstract int[] hit(Board opponentBoard); // different for user and computer


}
