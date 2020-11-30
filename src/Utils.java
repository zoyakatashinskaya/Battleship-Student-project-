public class Utils {

    public static int[] coordToIndex(String coord){
        int [] output = new int[2];
        output[0]=letterToNumber(coord.charAt(0));
        output[1]=Integer.parseInt(String.valueOf(coord.charAt(1)));
        return output;
    }

    public static int[][] parse(String[] coordinates){
        int [][] output = new int[2][2];
        output[0][0]=letterToNumber(coordinates[0].charAt(0));
        output[0][1]=Integer.parseInt(String.valueOf(coordinates[0].charAt(1)));
        output[1][0]=letterToNumber(coordinates[1].charAt(0));
        output[1][1]=Integer.parseInt(String.valueOf(coordinates[1].charAt(1)));
        return output;
    }

    private static int letterToNumber(char l){
        char c = Character.toLowerCase(l);
        return ((int)c)-97;
    }
    public static int[] getVerticalHeightStartStop(int[][] coord){
        boolean isVertical=coord[0][0]==coord[1][0];
        boolean isHorizontal =coord[0][1]==coord[1][1];
        //Check if Boat lenght is right
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
        }else{
            height = coord[0][1];
            if(coord[0][0]>coord[1][0]){
                start=coord[1][0];
                stop=coord[0][0];
            }else{
                start=coord[0][0];
                stop=coord[1][0];
            }
        }
        int vertical = isVertical?1:0;
        return new int[]{vertical,start, stop, height};
    }

}
