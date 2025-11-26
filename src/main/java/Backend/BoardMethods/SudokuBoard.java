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


    public int[] getRow(int index){
        if(index>=0 && index <=8){
            int [] rowCopy = new int[9];
            System.arraycopy(board[index],0,rowCopy,0,9);
            return rowCopy;
        }
        else{throw new IllegalArgumentException("Invalid index");}

    }

    public int[] getColumn(int index){
        if(index>=0 && index <=8){
            int[] columnCopy = new int[9];
            for(int i = 0; i<9 ;i++){
                columnCopy[i] = board[i][index];
            }
            return columnCopy;
        }else{throw new IllegalArgumentException("Invalid index");}
    }




    public int[][] getBoard() {

        return board;
    }



}
