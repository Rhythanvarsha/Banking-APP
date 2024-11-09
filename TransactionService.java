//multithreading0->used make efficient,secure,avoid confusions,realiable

package service;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import dao.TransactionDAO;
import dao.TransactionDAOImpl;
import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.TransactionHistoryUtil;

public class TransactionService {
  private final TransactionDAO transactionDAO;

  private final ExecutorService executorService ;
  
  public TransactionService()
  {
	  this.transactionDAO=new TransactionDAOImpl();
	  this.executorService=Executors.newFixedThreadPool(5);//this executorService package creates own thread inbuilt threads are created can create n no of threads using this method here we created 5 threads
  }//executorService ->submit method in executorService has two interface i.e runnable ,callable which is functional interface
  public Future<?> deposit(int accountId,double amount)
  {
	 //lambda expression intro->java8 
	return executorService.submit(() ->{
		try{
			transactionDAO.deposit(accountId,amount);
		    TransactionHistoryUtil.saveTransaction("Deposit", accountId, amount);}
         catch( InvalidTransactionAmountException | SQLException| TransactionFailureException | IOException e) {
        	 System.err.println("Error during Deposit :"+e.getMessage());
         }
	
	
	});
  }
  public Future<?> withdraw(int accountId,double amount){
		return executorService.submit(()->{
			try {
				transactionDAO.withdraw(accountId, amount);
				TransactionHistoryUtil.saveTransaction("Withdraw", accountId, amount);
			}
			catch(SQLException | TransactionFailureException | InvalidTransactionAmountException | IOException e) {
				System.err.println("Error during withdraw: "+e.getMessage());
			}
		});}
public void shutDownExecutorService() {
	
	executorService.shutdown();
}
public Future<?>transferFunds(int from_account,int to_account,double amount){
    
    return executorService.submit(()->{
        try{
        transactionDAO.transferFunds(from_account,to_account, amount);
        TransactionHistoryUtil.saveTransaction("Transfer Funds",from_account,to_account,amount);
        }
        catch(IOException e){
            System.err.println("Error during withdraw:"+e.getMessage());
        } catch (InvalidTransactionAmountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransactionFailureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    });
}
}
//return type ->future it waits until task is completd