package Main;

import java.util.ArrayList;
import java.util.List;
import Backend.BoardMethods.*;

import Backend.BoardMethods.SudokuBoard;
import Backend.BoardMethods.SudokuBoardService;

public class Mode27  extends SudokuBoardService implements Runnable{
	private SudokuBoard board;
    public Mode27(SudokuBoard board ) {
        this.board = board;
    }
    
    public List<String> validateBoard() throws InterruptedException {
    	System.out.println("Starting Mode3 validation...");

        List<String> errors = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        
        //9 rows threads 
        for(int i=0; i<9; i++) {
        	int index =i;
        	Thread r = new Thread(() ->{
        		if(!rowValidation(index)) {
        			  synchronized (errors) {
        				  errors.add("Row " + (index + 1) + " invalid");
        			  }
        			
        			
        		}
        		
        	});
        	
            threads.add(r);
        	
        }
        // 9 col threads 
        for(int i=0; i<9; i++) {
        	int index=i;
        	Thread c = new Thread(() ->{
        		if(!columnValidation(index)) {
        			  synchronized (errors) {
        				  errors.add("Column" + (index + 1) + " invalid");
        			  }
        			
        			
        		}
        		
        	});
        	
            threads.add(c);
        	
        }
        // 9 box threads 
        for(int i=0;i<9;i++) {
        	int index= i ;
        	Thread b = new Thread(() ->{
        		if(!boxValidation(index)) {
        			  synchronized (errors) {
        				  errors.add("Box" + (index + 1) + " invalid");
        			  }
        			
        			
        		}
        		
        	});
        	
            threads.add(b);
        	
        }
		return errors;
        
        
    }
		
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
