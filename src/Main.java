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

      int[] solutionTest = {6,4,7,1,8,2,5,3};
      int[] solutionTest1= {6,4,7,1,8,0,0,0};
        Board.isLegalPosition(solutionTest1,8);
//        Board.attacksHaveOverlap(new int[] {2,4,1,3},4);
//        int [] a = {1,1};
////        ArrayList<int[]> s = new ArrayList<>(Arrays.asList(1,2,3));
//        int [] b = {1,1};
//        System.out.println(Arrays.equals(a,b));

//        System.out.println(s.contains(a));








}

}