import java.util.ArrayList;
import java.util.Arrays;

public class Square {

    boolean hasQueen;
    int[] location;
    int bSize;
    ArrayList<int[]> attacks;

    // empty square
    Square(int[] location, int bSize){
        this.hasQueen = true;
        this.location = location;
        this.bSize = bSize;
        this.attacks = new ArrayList<>();
    }

    public void intToSquare(int row, int column){
//        Square()

    }

    public void addQueen(){

    }

    // get all of the squared that are attacked by the queen at your position
    // all attackers and 1-based, NOT 0-based
    public static ArrayList<int[]> getAttacks(int[] position,int size){
        ArrayList<int[]> attacks = new ArrayList<>();

        // add horizontal, vertical and diagonal attacks
        ArrayList<int[]> hAttacks = getHAttacks(position,size);
        ArrayList<int[]> vAttacks = getVAttacks(position,size);
        ArrayList<int[]> dAttacks = getDAttacks(position,size);

        attacks.addAll(hAttacks);
        attacks.addAll(vAttacks);
        attacks.addAll(dAttacks);
        return attacks;

    }

    // vertical attacks
    public static ArrayList<int[]> getVAttacks(int[] position,int size){
        ArrayList<int[]> vAttacks = new ArrayList<>();
        int row = position[0];
        int col = position[1];
        // add all position from top to bottom that dont include {row,col}
        for (int i = 1;i<= size; i++){
            if(i!= row){
                int[] anAttack = {i,col};
                vAttacks.add(anAttack);
            }
        }
       printAttacks(vAttacks);
//        hAttacks.forEach( attack -> System.out.print(Arrays.toString(attack)+", "));
//        System.out.println();
        return vAttacks;
    }

    // horizontal attacks
    public static ArrayList<int[]> getHAttacks(int[] position,int size){
        ArrayList<int[]> hAttacks = new ArrayList<>();
        int row = position[0];
        int col = position[1];
        // add all position from left to right that dont include {row,col}
        for (int i = 1;i<= size; i++){
            if(i!= col){
                int[] anAttack = {row,i};
                hAttacks.add(anAttack);
            }
        }

       printAttacks(hAttacks);
        return hAttacks;

    }

    // diagonal attacks
    public static ArrayList<int[]> getDAttacks(int[] position,int size){
        ArrayList<int[]> dAttacks = new ArrayList<>();
        int row = position[0];
        int col = position[1];

        // get upper right diagonal (till close to top right corner)
        for (int i = row-1, j =col+1; (i >=1 && j<=size); i--, j++){
                int[] anAttack = {i,j};
                dAttacks.add(anAttack);
        }

//         get upper left diagonal (till close to top left corner)
        for (int i = row-1, j =col-1; (i >=1 && j>=1); i--, j--){
            int[] anAttack = {i,j};
            dAttacks.add(anAttack);
        }
//         get lower left diagonal (till close to top right corner)
        for (int i = row+1, j =col-1; (i <=size && j>=1); i++, j--){
            int[] anAttack = {i,j};
            dAttacks.add(anAttack);
        }
        // get lower right diagonal (till close to top right corner)
        for (int i = row+1, j =col+1; (i <=size && j<=size); i++, j++){
            int[] anAttack = {i,j};
            dAttacks.add(anAttack);
        }

        // add all position down
        printAttacks(dAttacks);
        return dAttacks;
    }
    public static void printAttacks(ArrayList<int[]> hAttacks){
        hAttacks.forEach( attack -> System.out.print(Arrays.toString(attack)+", "));
        System.out.println();
    }







}
