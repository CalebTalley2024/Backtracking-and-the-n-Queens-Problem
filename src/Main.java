import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//        Square.getVAttacks(new int[]{2,2},4);
//        Square.getHAttacks(new int[]{2,2},4);
//        Square.getDAttacks(new int[]{2,2},4);
//        Square.getAttacks(new int[]{1,1},4);


//       Board.printBoard(new int[] {1,2,3,4,5,6,7,8},8);

      ArrayList<Square> ss =   Board.convertArrToSquareArr(new int[] {1,2,3,0,5,6,7,8}, 8);

        Board.attacksHaveOverlap(new int[] {1,4,6,8,5,3,1,7},8);








}

}