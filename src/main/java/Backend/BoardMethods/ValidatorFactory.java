package Backend.BoardMethods;

import Main.*;

public class ValidatorFactory {

    public static Runnable createValidator(SudokuBoard board, int mode) {

        return switch (mode) {
            case 0 -> new Mode0(board);
            case 3 -> new Mode3(board);
            // case 27 -> new Mode27(board);
            default -> throw new IllegalArgumentException("Invalid mode");
        };
    }
}