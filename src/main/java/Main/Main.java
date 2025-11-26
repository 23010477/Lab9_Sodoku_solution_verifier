package Main;

import Backend.BoardMethods.SudokuBoard;
import Backend.BoardMethods.SudokuBoardReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) { //AI GENERATED TEST --      DELETE LATER

        // 1. Read board from CSV
        SudokuBoardReader reader = new SudokuBoardReader();
        int[][] loadedBoard = reader.ReadBoardFromFile("C:\\Users\\CozyAk\\IdeaProjects\\Lab9-Sodoku_solution_verifier\\src\\main\\java\\Backend\\BoardMethods\\test.txt");

        // 2. Initialize the singleton SudokuBoard
        SudokuBoard board = SudokuBoard.getInstance(loadedBoard);

        // 3. Print the full board
        System.out.println("Full Board:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.getBoard()[i][j] + " ");
            }
            System.out.println();


        }

        int rowIndex = 0; // first row
        int[] row = board.getRow(rowIndex);
        System.out.print("\nRow " + rowIndex + ": ");
        for (int val : row) {
            System.out.print(val + " ");
        }
        System.out.println();


        int colIndex = 1; // second column
        int[] column = board.getColumn(colIndex);
        System.out.print("Column " + colIndex + ": ");
        for (int val : column) {
            System.out.print(val + " ");
        }
        System.out.println();

        int boxIndex = 4; // middle box
        int[][] box = board.get3x3Box(boxIndex);
        System.out.println("Box " + boxIndex + ":");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(box[i][j] + " ");
            }
            System.out.println();
        }

    }
}

