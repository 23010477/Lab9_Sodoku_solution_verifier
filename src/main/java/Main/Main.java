package Main;

import Backend.BoardMethods.SudokuBoard;
import Backend.BoardMethods.ValidatorFactory;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        // Check if correct number of arguments provided
        if (args.length != 2) {
            System.out.println("Please Provide Valid Arguments!");
            System.out.println("VALID example: java Main <Mode> <FilePath>");
            System.out.println("Available Modes: 'ZeroHelpers' 'ThreeHelpers' 'TwentySevenHelpers'");
            return;
        }
        
        String validationMode = args[0];
        String filePath = args[1];
        
        // Convert mode string to integer
        int mode = parseMode(validationMode);
        if (mode == -1) {
            System.out.println("Invalid mode: " + validationMode);
            System.out.println("Available Modes: 'ZeroHelpers' 'ThreeHelpers' 'TwentySevenHelpers'");
            return;
        }
         
        try {
            // Load the Sudoku board
            SudokuBoard board = SudokuBoard.getInstance(filePath);
            
            // Display the board
            System.out.println("Sudoku Board:");
            System.out.println("-------------");
            int[][] boardArray = board.getBoard();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(boardArray[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            
            // Create and run the validator
            System.out.println("Validation Mode: " + validationMode);
            System.out.println("Running validation...");
            System.out.println();
            
            Runnable validator = ValidatorFactory.createValidator(board, mode);
            validator.run();
            
            // Check validation results
            System.out.println();
            System.out.println("Validation Results:");
            System.out.println("------------------");
            
            boolean isValid = checkValidationResults(validator, mode);
            
            if (isValid) {
                System.out.println("✓ Board is VALID - All rows, columns, and boxes are correct!");
            } else {
                System.out.println("✗ Board is INVALID - Errors found above");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Converts mode string to integer mode value
     * @param modeString The mode string (ZeroHelpers, ThreeHelpers, TwentySevenHelpers)
     * @return Integer mode (0, 3, 27) or -1 if invalid
     */
    private static int parseMode(String modeString) {
        return switch (modeString.toLowerCase()) {
            case "zerohelpers" -> 0;
            case "threehelpers" -> 3;
            case "twentysevenhelpers" -> 27;
            default -> -1;
        };
    }
    
    /**
     * Checks validation results based on the validator type
     * @param validator The validator that was run
     * @param mode The mode used (0, 3, or 27)
     * @return true if board is valid, false otherwise
     */
    private static boolean checkValidationResults(Runnable validator, int mode) {
        try {
            if (mode == 0 && validator instanceof Mode0) {
                Mode0 mode0 = (Mode0) validator;
                List<String> errors = mode0.boardValidate();
                return errors.isEmpty();
            } else if (mode == 3 && validator instanceof Mode3) {
                Mode3 mode3 = (Mode3) validator;
                List<String> errors = mode3.validateBoard();
                return errors.isEmpty();
            } else if (mode == 27 && validator instanceof Mode27) {
                Mode27 mode27 = (Mode27) validator;
                List<String> errors = mode27.validateBoard();
                return errors.isEmpty();
            }
        } catch (InterruptedException e) {
            System.out.println("Validation was interrupted: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error checking validation results: " + e.getMessage());
            return false;
        }
        return false;
    }
}
