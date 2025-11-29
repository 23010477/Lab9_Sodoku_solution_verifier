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
        System.out.println("Starting Mode3 validation...");

        List<String> rowErrors = new ArrayList<>();
        List<String> colErrors = new ArrayList<>();
        List<String> boxErrors = new ArrayList<>();

        Thread rowThread = new Thread(() -> {
            System.out.println("Row thread started");
            for (int i = 0; i < 9; i++) {
                boolean result = rowValidation(i);
                if (!result) {
                    rowErrors.add("Error in Row " + (i + 1));
                    System.out.println("Row " + (i + 1) + " invalid");
                }
            }
            System.out.println("Row thread finished");
        });

        Thread columnThread = new Thread(() -> {
            System.out.println("Column thread started");
            for (int i = 0; i < 9; i++) {
                boolean result = columnValidation(i);
                if (!result) {
                    colErrors.add("Error in Column " + (i + 1));
                    System.out.println("Column " + (i + 1) + " invalid");
                }
            }
            System.out.println("Column thread finished");
        });

        Thread boxThread = new Thread(() -> {
            System.out.println("Box thread started");
            for (int i = 0; i < 9; i++) {
                boolean result = boxValidation(i);
                if (!result) {
                    boxErrors.add("Error in Box " + (i + 1));
                    System.out.println("Box " + (i + 1) + " invalid");
                }
            }
            System.out.println("Box thread finished");
        });

        // Start threads
        rowThread.start();
        columnThread.start();
        boxThread.start();

        // Wait for threads to finish
        rowThread.join();
        columnThread.join();
        boxThread.join();
        
        System.out.println("All threads finished");

        // Merge results
        List<String> finalErrors = new ArrayList<>();
        finalErrors.addAll(rowErrors);
        finalErrors.addAll(colErrors);
        finalErrors.addAll(boxErrors);

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

    public static void main(String[] args) {
        String filePath = "src/main/java/Backend/BoardMethods/test.txt";
        SudokuBoard board = SudokuBoard.getInstance(filePath);

        Mode3 mode3 = new Mode3(board);
        mode3.run();
        System.out.println("Mode 3 completed");
        try{
            if(mode3.validateBoard().isEmpty()) {
                System.out.println("Board is valid");
            } else {
                System.out.println("Board is invalid");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
