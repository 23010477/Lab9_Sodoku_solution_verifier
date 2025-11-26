package Backend.BoardMethods;

public class SudokuBoard {
    private final int[][] board;
    private static SudokuBoard instance;




    private SudokuBoard (int [][] board){
        this.board = board;
    }

    public synchronized static SudokuBoard getInstance(int[][] board){
        if(instance == null){
            instance = new SudokuBoard(board);
        }
        return instance;
    }





    public int[][] getBoard() {

        return board;
    }



}
