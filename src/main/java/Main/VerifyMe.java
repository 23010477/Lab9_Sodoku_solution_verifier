package Main;

import Backend.BoardMethods.SudokuBoard;
import Backend.BoardMethods.SudokuBoardReader;
import Backend.BoardMethods.SudokuBoardService;
import Backend.BoardMethods.ValidatorFactory;

//El CLI HENAA

public class VerifyMe {

    public static void main(String[] args) {
        if (args.length != 2) { // [0] is the mode , [1] is the filepath.
            System.out.println("Please Provide Valid Arguments!");
            System.out.println("VALID example: java VerifyMe <Mode> <FilePath>");
            System.out.println("Available Modes: 'ZeroHelpers' 'ThreeHelpers' 'TwentySevenHelpers'");
            return;
        }

        String validationMode = args[0];
        String filePath = args[1];

        SudokuBoard board = SudokuBoard.getInstance(filePath);

        int[][] printableBoard = board.getBoard();
        System.out.println("Sudoku Board:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(printableBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        // Handle different validation modes
        switch (validationMode.toLowerCase()) {
            case "zerohelpers":
                System.out.println("Running ZeroHelpers mode (single-threaded)...");
                SudokuBoardService service = new SudokuBoardService();
                service.boardValidation();
                break;

            case "threehelpers":
                System.out.println("Running ThreeHelpers mode (3 concurrent threads)...");
                Runnable mode3 = ValidatorFactory.createValidator(3);
                Thread validationThread = new Thread(mode3);
                validationThread.start();
                try {
                    validationThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "twentysevenhelpers":
                System.out.println("TwentySevenHelpers mode not yet implemented.");
                break;

            default:
                System.out.println("Invalid mode: " + validationMode);
                System.out.println("Available Modes: 'ZeroHelpers' 'ThreeHelpers' 'TwentySevenHelpers'");
        }
    }

}
