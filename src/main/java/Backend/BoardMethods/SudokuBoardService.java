package Backend.BoardMethods;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoardService {

public boolean rowValidation(int index){
int[] row=SudokuBoard.getInstance().getRow(index);
int length=row.length;
 try{
     for(int i=0;i<length;i++){
         if(row[i]==0)
             continue;
         for(int j=i+1;j<length;j++)
         { if(row[j]==0)
             continue;
             if(row[i]==row[j])
             {
                 return false;
             }
         }
     }
 }catch(IndexOutOfBoundsException e){
     e.printStackTrace();
 }
return true;
}

public boolean columnValidation(int index) {

    return true;
}
public boolean boxValidation(int index){

    return true;
}
public boolean boardValidation(int index){

    return true;
}
}
