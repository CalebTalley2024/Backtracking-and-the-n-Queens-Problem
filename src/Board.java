import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {

    // positions of all queens
    ArrayList<Square> positions;
    int size;

    Board(){
        this.positions = new ArrayList<>();
        this.size = positions.size();
    }

    // turns a list of numbers into a position
    // with each number, make a square and set attack parameters

//    public ArrayList<Square> listToPosition (){
//
//    }


    public void getQueenAttacks(int[][] posions, int size){


    }




    // takes in position and n
    // returns T iff no two Queens are attackinig each other
    public void isLegalPosition(Board board,int n){

        // check that there are no zeros before the first queen

        // check if any of the  queen attacks overlap
    }
    public static boolean attacksHaveOverlap(int[] intPosition, int n){
        printBoard(intPosition,n);
        ArrayList<Square> position = convertArrToSquareArr(intPosition,n);
        ArrayList<int[]> allAttacks = new ArrayList<>();
        // add the attacks from every queen into allAttacks array
        for( Square aSquare: position){
            ArrayList<int[]> oneQueenAttacks = aSquare.attacks;
            allAttacks.addAll(oneQueenAttacks);
        }
//        check to see if there is any overlap
//        boolean overlap = checkForDuplicates(allAttacks);

        return overlap;
    }

    // find out if there any attacks in your array that have been duplicated
//    public static boolean checkForDuplicates(ArrayList<int[]> attacks){
//        boolean overlap = false;
//        // put all of your attack positions in a Set Object.
//        // since a set object does not allow duplicates, we can just compare the sizes of both the set and the ArrayList
//
//        Set<int[]> attacksSet = new HashSet<>();
//        attacksSet.addAll(attacks);
//
//        // if the amount of attacks in our Set is different, we know that a duplicate was erased
//        if(attacks.size() != attacksSet.size()){
//            overlap = true;
//            System.out.println(" Queens are attacking each other ");
//
//        }
//        else{
//            overlap = false;
//            System.out.println("No Queens are attacking");
//
//        }
//        return overlap;
//
//    }

    public void checkForAttacks(){


    }

    //finds the next legal position
    public void nextLegalPosition(Board board,int n){}

    // find the first solution for n ∈ [4,100]
    // make sure it's the first solution
    // get same solution each time

    // convert number positions arrayList to an arrayList of square objects
    public static  ArrayList<Square> convertArrToSquareArr(int[] numPos,int size){
        ArrayList<Square> position = new ArrayList<>();
        for(int i =0; i<size; i++){
            // make i + 1 our row (+1 for 1-based positions)
            int intRow = i+1;
            int intCol = numPos[i];
            Square aSquare = Square.intToSquare(intRow,intCol,size);
            position.add(aSquare);
        }
        return position;
    }
    public static void printBoard(int[] intPositions, int size){
        ArrayList<Square> positions = convertArrToSquareArr(intPositions,size);
        char[][] chessBoard = new char[size][size];
        // make default empty game board
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                chessBoard[i][j] = '0';
            }
        }

        // update the game board with your state/position
        for(int i = 0; i<size; i++){
            Square aSquare = positions.get(i);
            // change the character in the chessboard when you have a queen position
            if(aSquare.hasQueen){
                int[] location = aSquare.location;
                int row = location[0];
                int col = location[1];
                // subtract one here due to 1-based input
                chessBoard[row-1][col-1] = '*';
            }
        }
        loadBoard(chessBoard);
    }
    public static void loadBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.printf(" %c ",c); // prints row of the array on the same line
            }
            System.out.println(); // prints a new lime
        }
    }
}
