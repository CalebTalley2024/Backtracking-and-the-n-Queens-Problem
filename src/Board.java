import java.util.ArrayList;
import java.util.Arrays;

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

    // convert number positions arrayList to an arrayList of square objects
    public ArrayList<Square> convertArrToSquareArr(ArrayList<Integer> numPos){
        ArrayList<Square> positions = new ArrayList<>();
//        numPos.forEach(num -> );
        return positions;
    }
    public void getQueenAttacks(int[][] posiions, int size){


    }




    // takes in position and n
    // returns T iff no two Queens are attackinig each other
    public void isLegalPosition(Board board,int n){}

    //finds the next legal position
    public void nextLegalPosition(Board board,int n){}

    // find the first solution for n ∈ [4,100]
    // make sure it's the first solution
    // get same solution each time

    public static void printBoard(ArrayList<Square> positions, int size){
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
                chessBoard[row][col] = '*';
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
