public class ComputerBoard extends Board{

    public String[][] publicBoard;

    public ComputerBoard(int size){
        super(size);
        this.publicBoard= new String[size][size];

    }
    @Override
    public void print(){
        System.out.println("   |[A][B][C][D][E][F][G][H][I][J]");
        System.out.println("---|------------------------------");
        for(int i=0;i<this.size;i++){
            System.out.print("["+i+"]|");
            for(int j=0;j<this.size;j++){
                if(this.publicBoard[j][i]!=null) {
                    System.out.print("["+this.publicBoard[j][i] + "]");
                }else{
                    System.out.print("[ ]");
                }
            }
            System.out.println("");
        }

    }

    /*

    @Override
    public boolean fieldWasAttacked(int[] coord) {
        if (this.publicBoard[coord[0]][coord[1]] != null){
            return true;
        } else {return false;}
    }


     */
    @Override
    public int processHit(int[] coord) {
        if(this.board[coord[0]][coord[1]]!=null){
            if("X".equals(this.publicBoard[coord[0]][coord[1]]) || "O".equals(this.publicBoard[coord[0]][coord[1]])){
                return -1;
            } else {
                this.publicBoard[coord[0]][coord[1]]="X";
                return 1;
            }
        }else{
            if("X".equals(this.publicBoard[coord[0]][coord[1]]) || "O".equals(this.publicBoard[coord[0]][coord[1]])){
                return -1;
            }
            this.publicBoard[coord[0]][coord[1]]="O";
            return 0;
        }
    }


    public void updateBoard(Ship ship){
        int[][] coord = ship.getCoordinates();
        // 0: isVertical, 1: start, 2: stop, 3: height
        int[] specs =Utils.getVerticalHeightStartStop(coord);
        int vertical = specs[0];
        int start = specs[1];
        int stop = specs[2];
        int height = specs[3];
        if(vertical==1){
            //isVertical
            for(int i=start;i<=stop;i++){
                this.publicBoard[height][i]=ship.getLetter();
            }

        }else{
            //isHorizontal
            for(int i=start;i<=stop;i++){
                this.publicBoard[i][height]=ship.getLetter();
            }
        }

    }
}