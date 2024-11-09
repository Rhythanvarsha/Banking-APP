package utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//2 methods


public class TransactionHistoryUtil {
    private static final String FILE_PATH="transaction_history.txt";
    private static final SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-mm-dd HH:MM:SS");
    
//CREATING A METHOD TO SAVE ALL THE TRANSACTION DETAILS IN A FILE
    public static void saveTransaction(String transactionType,int accountId,double amount) throws IOException {
    	String timestamp=dateFormat.format(new Date());
        String record=String.format("%s | %s| Account ID:%d "+"|Amount:%.2f",timestamp,transactionType,accountId,amount);
      try(BufferedWriter writer=new BufferedWriter(new FileWriter(FILE_PATH,true)))
       {
        	     writer.write(record);
        	     writer.newLine();
       }
    catch(IOException e) {
    	System.err.println("Error writing to transatcion history file");
    }
}
//Method to retrive and display transaction history->we do n no of transaction to store it we use arrayList ->
    public static List<String> retriveTrasactionHistory() throws FileNotFoundException, IOException{
    	List<String> history=new ArrayList<String>();
    	
    	try(BufferedReader reader=new BufferedReader(new FileReader(FILE_PATH))){
    		String line;
    		while((line=reader.readLine())!=null) 
    		{
    			history.add(line);
    		}
    		
    		}
    	catch(IOException e) {
        	System.err.println("Error reading to transatcion history file");
        }
    	
    	
		return history;
    	
    	
    }
    public static void saveTransaction(String transactionType,int from_account,int to_account,double amount) throws IOException{
        String timestamp=dateFormat.format(new Date());
        String record=String.format("%s |%s| From AccountId: %d To Account %d "+"|Amount: %.2f",timestamp,transactionType,from_account,to_account,amount);
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(FILE_PATH,true))){
            writer.write(record);
            writer.newLine();
            
        }
        catch(IOException e){
            System.err.println("Error writing to transcation history file");
        }
    }//transaction history

}
