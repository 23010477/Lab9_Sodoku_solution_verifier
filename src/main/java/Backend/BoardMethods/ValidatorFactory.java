package Backend.BoardMethods;

import Main.*;

public class ValidatorFactory {

    public static Runnable createValidator(SudokuBoard board, String mode) {

        return switch (mode) {

            case "ZeroHelpers" -> new Mode0(board);
            case "ThreeHelpers" -> new Mode3(board);
            case "TwentySevenHelpers" -> new Mode27(board);
            default -> throw new IllegalArgumentException("Invalid mode");
        };
    }
}