package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import Backend.BoardMethods.*;

public class Mode3 extends SudokuBoardService implements Runnable {
    private SudokuBoard board;
    public Mode3(SudokuBoard board ) {
        this.board = board;
    }

    public List<String> validateBoard() throws InterruptedException {

        List<String> rowErrors = new ArrayList<>();
        List<String> colErrors = new ArrayList<>();
        List<String> boxErrors = new ArrayList<>();

        Thread rowThread = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                boolean result = rowValidation(i);
                if (!result) {
                    rowErrors.add("Error in Row " + (i + 1));
                    System.out.println("Row " + (i + 1) + " invalid");
                }
            }
        });

        Thread columnThread = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                boolean result = columnValidation(i);
                if (!result) {
                    colErrors.add("Error in Column " + (i + 1));
                    System.out.println("Column " + (i + 1) + " invalid");
                }
            }
        });

        Thread boxThread = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                boolean result = boxValidation(i);
                if (!result) {
                    boxErrors.add("Error in Box " + (i + 1));
                    System.out.println("Box " + (i + 1) + " invalid");
                }
            }
        });

        // Start threads
        rowThread.start();
        columnThread.start();
        boxThread.start();

        // Wait for threads to finish
        rowThread.join();
        columnThread.join();
        boxThread.join();
        
        // Merge results
        List<String> finalErrors = new ArrayList<>();
        finalErrors.addAll(rowErrors);
        finalErrors.addAll(colErrors);
        finalErrors.addAll(boxErrors);

        if(finalErrors.isEmpty()) {
            System.out.println("Board is valid");
        } else {
            System.out.println("Board is invalid");
        }
        return finalErrors;
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
}
