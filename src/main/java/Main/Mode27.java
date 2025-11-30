package Main;

import java.util.ArrayList;
import java.util.List;
import Backend.BoardMethods.*;

import Backend.BoardMethods.SudokuBoard;
import Backend.BoardMethods.SudokuBoardService;

public class Mode27 extends SudokuBoardService implements Runnable {
    private SudokuBoard board;

    public Mode27(SudokuBoard board) {
        this.board = board;
    }

    public List<String> validateBoard() throws InterruptedException {
        System.out.println("Starting Mode27 validation...");

        List<String> errors = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // 9 rows threads
        for (int i = 0; i < 9; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                if (!rowValidation(index)) {
                    synchronized (errors) {
                        errors.add("Row " + (index + 1) + " invalid");
                    }

                }

            });

            threads.add(t);

        }
        // 9 col threads
        for (int i = 0; i < 9; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                if (!columnValidation(index)) {
                    synchronized (errors) {
                        errors.add("Column" + (index + 1) + " invalid");
                    }

                }

            });

            threads.add(t);

        }
        // 9 box threads
        for (int i = 0; i < 9; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                if (!boxValidation(index)) {
                    synchronized (errors) {
                        errors.add("Box" + (index + 1) + " invalid");
                    }

                }

            });

            threads.add(t);

        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return errors;

    }

    @Override
    public void run() {
        try {
            List<String> errors = validateBoard();
            for (String error : errors) {
                System.out.println(error);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePath = "src/main/java/Backend/BoardMethods/test.txt";
        SudokuBoard board = SudokuBoard.getInstance(filePath);

        Mode27 mode27 = new Mode27(board);
        mode27.run();
        System.out.println("Mode 27 completed");
        try {
            if (mode27.validateBoard().isEmpty()) {
                System.out.println("Board is valid");
            } else {
                System.out.println("Board is invalid");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
