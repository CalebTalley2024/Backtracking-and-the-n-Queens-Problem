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


    public static void nextLegalPosition(int[] position, int n){
        /*
         *  Find next move from.....
         *    1. illegal pos  (could have zeros in btw top row)
         *    2. legeal pos
         *    3. legal pos after full solution
         * 2. */
        boolean haveNextPos = false;
        // if the input is a legal position
        if(isLegalPosition(position,n)){
            while(!haveNextPos){
                nextFromLegalPosition( position,n);
                if (isLegalPosition(position,n)){
                    haveNextPos = true;
                }
            }
        }
        // if the input is an illegal position
        else {
            while(!haveNextPos){
                nextFromIllegalPosition( position,n);
                if (isLegalPosition(position,n)){
                    haveNextPos = true;
                }
            }
        }
    }

    public static void nextFromLegalPosition(int[] position, int n){
        int[] pos = position.clone();
        int lastRow= n-1;
        // get last queen index
        int lastQueenIndex = getLastQueenIndex(pos, n);
        // i is the row
        // position[i] is the column
        // if not a full solution, add a queen to the next index

        // check if you are at the last row
        if(lastQueenIndex ==lastRow) {
            // if you are on the last row , get rid of the last queen and move the next to last queen to the right
            // column shouldnt matter if this is a legal position
            pos[lastQueenIndex] = 0;
            pos[lastQueenIndex - 1]++;
        }
        // if youre not in the last column,
        else{
            // if your queen is  on the last column, add a queen below on the far left
            pos[lastQueenIndex + 1] = 1;
            // recursive call


        }
        if(!isLegalPosition(pos,n)){
            nextLegalPosition(pos,n);
        }
        // print the array
        printIntArray(pos);

    }

    // full solution: all the squares
    public static void nextFromIllegalPosition(int[] position, int n){
        int lastColumn =  n-1;
        // get last queen index
        int lastQueenIndex = getLastQueenIndex(position, n);
        // i is the row
        // position[i] is the column
        // if not a full solution, add a queen to the next index

        // check if you are at the last row
        if(lastQueenIndex ==lastColumn) {
            // if you are on the last row , get rid of the last queen and move the next to last queen to the right
            // column shouldnt matter if this is a legal position
            position[lastQueenIndex] = 0;
            position[lastQueenIndex - 1]++;
        }
        // if youre not in the last column,
        else{
            // if your queen is  on the last column, add a queen below on the far left
            position[lastQueenIndex]++;
        }

    };

    public static void printIntArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
    // takes in position and n
    // returns True if there are no invalid 0s and no queens attacking each other
    public static boolean isLegalPosition(int[] intPosition,int n){

        printBoard(intPosition,n);

        boolean isLegal = true;
        // check that there are no zeros before the first queen

        boolean invalidZero = hasInvalidZeros(intPosition,n);

        // check if any of the  queen attacks overlap
        boolean overlap = hasOverlap(intPosition,n);

        if(invalidZero || overlap){
            System.out.println("This position is invalid");
            isLegal = false;
        }
        {
            System.out.println("This position is valid");
        }
        return isLegal;
    }


    public static int getLastQueenIndex(int[] intPosition, int n){
        int lastQueenIndex = 0;
        for(int i  = n-1; i>=0; i--){

            if(intPosition[i] != 0 ){
                lastQueenIndex = i;
                break;
            }
        }
        return lastQueenIndex;
    }
    // helpers for isLegal Position
    public static boolean hasInvalidZeros(int[] intPosition, int n){
        boolean invalid = false;
        // check where the last position there is a queen
        int lastQueenIndex = getLastQueenIndex(intPosition,n);
        // make sure that there are no zeros before that last queen
        for(int j = 0; j< lastQueenIndex;j++){
            if(intPosition[j] == 0){
                invalid = true;

            }
        }
//        if(invalid){
//            System.out.println("There is an empty spot at the top");
//        }
//        else {
//            System.out.println("Valid");
//        }
        return invalid;
    }
    public static boolean hasOverlap(int[] intPosition, int n){

        ArrayList<Square> position = convertArrToSquareArr(intPosition,n);
        ArrayList<int[]> allAttacks = new ArrayList<>();
        // add the attacks from every queen into allAttacks array
        for( Square aSquare: position){
            ArrayList<int[]> oneQueenAttacks = aSquare.attacks;
            allAttacks.addAll(oneQueenAttacks);
        }
//        check to see if there is any overlap
        boolean overlap = checkForAttacks(position, allAttacks,n);

        return overlap;
    }
    // find out if there any attacks in your array are not positions occupied by a queen
    public static boolean checkForAttacks(ArrayList<Square>position, ArrayList<int[]> allAttacks, int size){
        boolean overlap = false;
        // put all of your attack positions in a Set Object.
        // add all attacks to a set
        ArrayList<int[]> attacksSet = new ArrayList<>();
        attacksSet.addAll(allAttacks);

        // if you find an overlap, change overlap variable to true
        // this will work for {0,0} square too since this is off the board (meaning that it will never get attacked)
        for(int[] anAttack: allAttacks){

            if(overlap){
                break;
            }
            for( Square aSquare: position){
                int [] location = aSquare.location;
                if(Arrays.equals(location,anAttack)){
                    overlap = true;
                    break;
                }
            }
        }

        // take into account: if there is no overlap the Set will be the amount of the attacks + the number of positions

        ///////// make case for (0,0)

        // add queen square positions to attacks

//            if(overlap){
//                System.out.println(" at least 2 Queens are attacking each other" );
//            }
//            else{
//                System.out.println("No Queens are attacking");
//            }




        return overlap;

    }
    //finds the next legal position


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
        System.out.println();
    }
}
