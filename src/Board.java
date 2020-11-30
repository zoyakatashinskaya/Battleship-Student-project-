public class Board {
    protected String[][] board;
    protected int size;

    public Board(int size){
        this.size=size;
        this.board = new String[size][size];
    }
    public void print(){
        System.out.println("   |[A][B][C][D][E][F][G][H][I][J]");
        System.out.println("---|------------------------------");
        for(int i=0;i<this.size;i++){
            System.out.print("["+i+"]|");
            for(int j=0;j<this.size;j++){
                if(this.board[j][i]!=null) {
                    System.out.print("["+ this.board[j][i] + "]");
                }else{
                    System.out.print("[ ]");
                }
            }
            System.out.println("");
        }
    }

    private String getPositionValue(boolean isForOpponent, String positionValues) {
        if(isForOpponent) {
            return "O".equals(positionValues) || "X".equals(positionValues) ? positionValues : " ";
        } else {
            return positionValues;
        }
    }

    // todo: discuss if we should leave it this way or separate validation and insertion.

    public boolean insert(Ship ship, int[][] coordinates){
        return this.validate(ship, coordinates);

    }

    private boolean validate(Ship ship, int[][] coord){
        //Check if boat is in the field
        boolean check = true;
        for (int i=0;i<=1;i++){
            for (int j=0;j<=1;j++){
                check=check&&coord[i][j]>=0&&coord[i][j]<this.size;
            }
        }
        // todo: in case the field is occupied add an error message
        //Check if Boat is not Paralell
        boolean isVertical=coord[0][0]==coord[1][0];
        boolean isHorizontal =coord[0][1]==coord[1][1];
        check=check&&(isVertical||isHorizontal);
        //Check if Boat lenght is right
        if(isVertical){
            check=check&& Math.abs(coord[0][1]-coord[1][1])==ship.getLength()-1;
        }else{
            check=check&& Math.abs(coord[0][0]-coord[1][0])==ship.getLength()-1;
        }
        //Check if field is empty where boat is placed
        int start=0;
        int stop=0;
        int height = 0;
        if(isVertical){
            height = coord[0][0];
            if(coord[0][1]>coord[1][1]){
                start=coord[1][1];
                stop=coord[0][1];
            }else{
                start=coord[0][1];
                stop=coord[1][1];
            }
            for(int i=start;i<=stop;i++){
                check=check&&this.board[height][i]==null;
            }
        }else{
            height = coord[0][1];
            if(coord[0][0]>coord[1][0]){
                start=coord[1][0];
                stop=coord[0][0];
            }else{
                start=coord[0][0];
                stop=coord[1][0];
            }
            for(int i=start;i<=stop;i++){
                check=check&&this.board[i][height]==null;
            }
        }
        if(check){
            this.insertInFields(ship, isVertical, start, stop, height);
        }
        return check;
    }

    private void insertInFields(Ship ship, boolean isVertical, int start, int stop, int height) {
        if (isVertical) {
            for (int i = start; i <= stop; i++) {
                this.board[height][i] = ship.getLetter();
            }
        } else {
            for (int i = start; i <= stop; i++) {
                this.board[i][height] = ship.getLetter();
            }
        }
    }



    //0-miss,1-hit,-1-already hit
    public int processHit(int[] coord){
        if(this.board[coord[0]][coord[1]]!=null){
            if("X".equals(this.board[coord[0]][coord[1]]) || "O".equals(this.board[coord[0]][coord[1]])){
                return -1;
            }else {
               this.board[coord[0]][coord[1]]="X";
               return 1;
            }
        } else {
            this.board[coord[0]][coord[1]]="O";
            return 0;
        }

    }
}
