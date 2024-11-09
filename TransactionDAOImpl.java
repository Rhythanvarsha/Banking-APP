package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;
import utility.DBConnection;

public class TransactionDAOImpl implements TransactionDAO{
//synchronized ->at a time this method cannot execute for n no of times
	@Override
	public synchronized void deposit(int accountId, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException {
	  if(amount<=0) {
		  throw new InvalidTransactionAmountException("Deposit Amount must be greater than zero");
	  }
	  try(Connection con=DBConnection.getConnection())
	  {
           CallableStatement st=con.prepareCall("{CALL deposit_funds(?,?)}");//callable for exectuing procedure
           st.setInt(1, accountId);
           st.setDouble(2, amount);
           st.execute();
	  }
	  catch(SQLException e) {
		  throw new TransactionFailureException("Deposit Failed: "+e.getMessage());
	  }
		
	}

	public synchronized void withdraw(int accountId, double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException {
		
		if (amount <= 0) {
		        throw new InvalidTransactionAmountException("Withdrawal amount must be greater than zero");
		    }
		    try (Connection con = DBConnection.getConnection()) {
		 
		        CallableStatement st = con.prepareCall("{CALL withdraw_funds(?, ?)}");
		        st.setInt(1, accountId);
		        st.setDouble(2, amount);
		    
		        st.execute();
		    } catch (SQLException e) {
		    
		        throw new TransactionFailureException("Withdrawal Failed: " + e.getMessage());
		    }
		}

	@Override
	
		public synchronized  void transferFunds(int fromAccount, int toAccount, double amount) throws TransactionFailureException, InvalidTransactionAmountException {
	         if(amount<=0){
	          throw new InvalidTransactionAmountException(" Transfer mustbe greater than 0");
	      }
	      try(Connection con=DBConnection.getConnection()){
	          CallableStatement st=con.prepareCall("{Call transfer_funds(?,?,?)}");
	          st.setInt(1,fromAccount);
	          st.setInt(2,toAccount);
	          st.setDouble(3,amount);
	          st.execute();
	          
	      }
	      catch(SQLException e){
	          throw new TransactionFailureException("transfer Failed:"+e.getMessage());
	      }
	      
	    }
		
	}







//multithreading ->used in all bank transaction,where thye define the no of threads to happen.If multiple transaction happens it becomes some error
//service->handle business logic(useed to do process in data).exampl->if we want to calculate interset we use service file
