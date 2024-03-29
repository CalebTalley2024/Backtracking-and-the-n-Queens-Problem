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

    public static void question4(){
        for(int i = 4; i<=12;i++){
            getAllFullSolutions(i);
        }

    }
    public static void question3(){
        for(int i = 4; i<= 14;i++){
            getFirstFullSolution(i);
        }
    }
    public static int[] nextLegalPosition(int[] position, int n){
        /*
         *  Find next move from.....
         *    1. illegal pos  (could have zeros in btw top row)
         *    2. legeal pos
         *    3. legal pos after full solution
         * 2. */
        boolean haveNextPos = false;
        // if the input is a legal position
        int[] pos = position.clone();
        // if the board not empty, find the next position
        // if the baord is empty, then just return the board position
        if(!boardIsEmpty(pos)) {

            if (isLegalPosition(pos, n)) {
                while (!haveNextPos) {
                    pos = nextFromLegalPosition(pos, n);
                    if (isLegalPosition(pos, n)) {
                        haveNextPos = true;
                    }
//                printBoard(pos,n);
                }
            }
            // if the input is an illegal position
            else {
                while (!haveNextPos) {
                    pos = nextFromIllegalPosition(pos, n);
                    if (isLegalPosition(pos, n)) {
                        haveNextPos = true;
                    }
//                printBoard(pos,n);
                }
            }
        }

//        System.out.print(" Next position: "  );
//        printIntArray(pos);
        return pos;
    }
    public static boolean isLegalPosition(int[] intPosition,int n){

//        printBoard(intPosition,n);

        boolean isLegal = true;
        // check that there are no zeros before the first queen

        boolean invalidZero = hasInvalidZeros(intPosition,n);

        // check if any of the  queen attacks overlap
        boolean overlap = hasOverlap(intPosition,n);

        if(invalidZero || overlap){
//            System.out.println("This position is invalid");
            isLegal = false;
        }
        else  {
//            System.out.println("This position is valid");
        }
        return isLegal;
    }

    ////// Helper functions
    // get all of the full solutions for an nxn board
    public static ArrayList<int[]> getAllFullSolutions (int n){
        int[] pos = newBoard(n);
        ArrayList<int[]> solutions = new ArrayList<>();
        // when the board is empty, pos will just be an empty array
        while (!boardIsEmpty(pos)){
                pos = getNextFullSolution(pos,n);
            // if we are not at the end of the algorithm,  we add our solutions to our arrayList
                if(!boardIsEmpty(pos))
                solutions.add(pos);

        }
//        System.out.println();
        int numSolutions = solutions.size();
        System.out.printf("There are %d solutions to the %d-Queens Problem",numSolutions,n);
        System.out.println();
        return solutions;
    }


    // get the first solution with queens on every diagonal
    public static int[] getFirstFullSolution( int n){
    // first we get our initial board of size n
        // this board will contain all 0s except for the first row, which will have a 1 (the left coner)
        int[] pos = newBoard(n);
        pos = getNextFullSolution(pos,n);
        System.out.printf(" The 'first' solution to the n-Queens Problem for n = "+ n + " is ");
        printIntArray(pos);
        System.out.println();
        return pos;
    }
    public static  int[] getNextFullSolution(int[] pos, int size ){
        int n = size;
        boolean isSolution = false;
        while(!isSolution){
            // we pick the first legal position that does not contain any zeros
            pos = nextLegalPosition(pos,n);

            // terminate if we either get a full solution or when the board does not have any more legal positions to go through

            if(!containZero(pos)|| boardIsEmpty(pos)){
                isSolution = true;
            }
        }
//        System.out.printf(" The 'first' solution to the n-Queens Problem for n = "+ n + " is ");
//        printIntArray(pos);
//        System.out.println();
        return pos;

    }
    // check to see if an array has any zeros
    public static boolean containZero(int[] array ){
        boolean hasZero = false;
        for(int aNum: array){
            if(aNum ==0){
                hasZero = true;
            }
        }
//        System.out.println(hasZero);
        return hasZero;

    }
    public static int[] newBoard(int size){
    int[] initPositions= new int[size];
    // we first fill our array with zeros
    Arrays.fill(initPositions,0);
    // we set our first row's column to the corner.
        // this will help nextLegalPostiion make the next move
    initPositions[0] = 1;
//    printIntArray(initPositions);
    return initPositions;
    }


    public static int[] nextFromLegalPosition(int[] position, int n){
        int[] pos = position.clone();
        // if the board not empty, find the next position
        // if the baord is empty, then just return the board position
        if(!boardIsEmpty(pos)) {
            int lastRow = n - 1;
            // lastColumn is n because the columns are 1-based
            int lastColumn = n;
            // get last queen index
            int lastQueenIndex = getLastQueenIndex(pos, n);
            // i is the row
            // position[i] is the column
            // if not a full solution, add a queen to the next index

            // check if you are at the last row
            if (lastQueenIndex == lastRow) {
                // if you are on the last row , get rid of the last queen and move the next to last queen to the right
                // column shouldnt matter if this is a legal position
                pos[lastQueenIndex] = 0;
                // account for if both the index and the previous index are at the right edge (the last column)
                if (pos[lastQueenIndex - 1] == lastColumn) {
                    // make second to last row zero
                    pos[lastQueenIndex - 1] = 0;
                    // shift third to last queen to the right
                    // (we dont have to check any more since we assume that anything above is already solved )
                    // this means that the second to last queen would be the only one on the right edge of the board
                    if(lastQueenIndex -2 != -1){
                        pos[lastQueenIndex - 2]++;
                    }
                }
                // if legal, just shift the next to last queen to the right
                else {
                    pos[lastQueenIndex - 1]++;
                }

            }
            // if youre not in the last column,
            else {
                // if your queen is  on the last column, add a queen below on the far left
                pos[lastQueenIndex + 1] = 1;
                // recursive call


            }
            if (!isLegalPosition(pos, n)) {
                pos = nextLegalPosition(pos, n);
            }
        }
        return pos;
        // print the array


    }

    // full solution: all the squares
    public static int[] nextFromIllegalPosition(int[] position, int n){
        int[] pos = position.clone();
        // if the board not empty, find the next position
        // if the baord is empty, then just return the board position
        if(!boardIsEmpty(pos)) {


            int lastColumn = n;
            // get last queen index
            int lastQueenIndex = getLastQueenIndex(pos, n);
            // i is the row
            // position[i] is the column
            // if not a full solution, add a queen to the next index

            // check if you are at the last = col
            if (pos[lastQueenIndex] == lastColumn) {
                // if you are on the last row , get rid of the last queen and move the next to last queen to the right
                // column shouldnt matter if this is a legal position
                pos[lastQueenIndex] = 0;
                // account for if both the index and the previous index are at the end
                if (pos[lastQueenIndex - 1] == lastColumn) {
                    // make second to last row zero
                    pos[lastQueenIndex - 1] = 0;
                    // shift third to last queen to the right
                    // (we dont have to check any more since we assume that anything above is already solved )
                    // this means that (if valid) the second to last queen would be the only one on the right edge of the board
                    if(lastQueenIndex -2  != -1){
                        pos[lastQueenIndex - 2]++;
                    }

                }
                // if legal, just shift the next to last queen to the right
                else {
                    pos[lastQueenIndex - 1]++;
                }
            }
            // if youre not in the last column,
            else {
                // if your queen is  on the last column, add a queen below on the far left
                pos[lastQueenIndex]++;
            }
        }
        return pos;
    };

    // board is empty: show that a board only has 0s
    public static boolean boardIsEmpty(int[] position){
        boolean isEmpty = true;
        // if you find a non-zero term in the array, then the board is NOT empty
        for(int square: position){
            if(square != 0){
                isEmpty = false;
            }
        }
        return isEmpty;

    }

    public static void printIntArray(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
    // takes in position and n
    // returns True if there are no invalid 0s and no queens attacking each other


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

        // add queen square positions to attacks

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
