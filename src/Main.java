import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    // test variables
    int[] test1 = {1, 6, 8, 3, 7, 4, 2, 5, 8};
    int n1 = 8;
    int[] test2 = {2,4,1,3};
    int n2 = 4;
    // Question 1
    boolean isLegal = Board.isLegalPosition(test1,n1);
//    System.out.println(isLegal);


    // Question 2
    int[] next = Board.nextLegalPosition(test2,n2);
//    Board.printIntArray(next);

    // Question 3
//      Board.question3();
    // Question 4
//      Board.question4();
  }



}