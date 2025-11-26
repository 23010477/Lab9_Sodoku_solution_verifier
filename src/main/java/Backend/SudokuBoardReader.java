package Backend;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.classfile.FieldTransform;

public class SudokuBoardReader {


    public int[][]  ReadBoardFromFile(String filename){
        int[][] board = new int[9][9];



        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){


        String fileLine;
        int row = 0;

        fileLine = bufferedReader.readLine();




        }catch(IOException e){
            e.printStackTrace();
        }

        return board;
    }


}
