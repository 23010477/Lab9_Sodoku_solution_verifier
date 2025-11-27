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
        int[] column=SudokuBoard.getInstance().getColumn(index);
        int length=column.length;
        try{
            for(int i=0;i<length;i++){
                if(column[i]==0)
                    continue;
                for(int j=i+1;j<length;j++)
                { if(column[j]==0)
                    continue;
                    if(column[i]==column[j])
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
public boolean boxValidation(int index){
 SudokuBoard SD =SudokuBoard.getInstance();
 int[][] box=SD.get3x3Box(index);
 try{
     for(int i=0;i<3;i++){
         for(int j=0;j<3;j++){
             if(box[i][j]==0)continue;
             for(int m=0;m<3;m++){
                 for(int n=0;n<3;n++){
                     if(box[m][n]==0){
                         continue;
                     }
                     if(i==m &&j==n){
                         continue;
                     }
                    if(box[i][j]==box[m][n]) {
                        return false;
                    }
                 }
             }
         }
     }
 }catch(IndexOutOfBoundsException e){
     e.printStackTrace();
 }


    return true;
}
public boolean boardValidation(){
 int[][] board=SudokuBoard.getInstance().getBoard();
 try {
     for(int i=0;i<9;i++){
         if(!rowValidation(i)){
             return false;
         }
     }
     for(int j=0;j<9;j++){
         if(!columnValidation(j)){
             return false;
         }
     }
     for(int m=0;m<9;m++){
         if(!boxValidation(m)){
             return false;
         }
     }
 }catch(IndexOutOfBoundsException e){
     e.printStackTrace();
 }
    return true;
}
}
