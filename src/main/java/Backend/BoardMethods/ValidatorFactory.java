package Backend.BoardMethods;

import Main.*;

public class ValidatorFactory {

    public static Runnable createValidator(int mode) {
        SudokuBoardService service = new SudokuBoardService();

        return switch (mode) {
            case 0 -> new Mode0(service);
            case 3 -> new Mode3(service);
            case 27 -> new Mode27(service);
            default -> throw new IllegalArgumentException("Invalid mode");
        };
    }
}