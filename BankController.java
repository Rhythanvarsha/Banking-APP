package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import exception.AccountnotFoundException;
import exception.BankingException;
import exception.InvalidAccountTypeException;
import exception.InvalidTransactionAmountException;
import model.Account;
import model.Bank;
import model.CurrentAccount;
import model.SavingsAccount;
import service.AccountService;
import service.BankService;
import service.TransactionService;

public class BankController {
	private final AccountService accountService;
	private final BankService bankService;
	private final BufferedReader br;
	private final TransactionService transactionService;
	
	public BankController() {
		this.accountService=new AccountService();
		this.bankService=new BankService();
		this.transactionService=new TransactionService();
		this.br=new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void start() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException, BankingException, AccountnotFoundException, InterruptedException, ExecutionException, InvalidTransactionAmountException{
		boolean running=true;
		while(running) {
			displayMenu();
			//System.out.println("Enter the Choice");
			int choice=Integer.parseInt(br.readLine());
			switch(choice) {
			case 1:
				createAccount();
				break;
			case 2:
				updateAccount();
				break;
			case 3:
				deleteAccount();
				break;
			case 4:
				 viewAccount();
				 break;
			case 5:
				Deposit();
				break;
			case 6:
				withdraw();
				break;
			case 7:
				TransferFunds();
				break;
			case 0:	
				running=false;
				transactionService.shutDownExecutorService();
				System.out.println("Exciting the app!GoodBye");
				break;
			}
		}
	}
	
	public void displayMenu() {
		System.out.println("----------Banking Application------------");
		System.out.println("1.Create Account");
		System.out.println("2.Update Account");
		System.out.println("3.Delete Account");
		System.out.println("4.View Account");
		System.out.println("5.Deposit Account");
		System.out.println("6.Withdrawal Account");
		System.out.println("7.Transfer funds");
		System.out.println("0.Exit");
		System.out.println("Enter the Choice");
	}
	
	
	
//withdraw
	private void withdraw() throws NumberFormatException, IOException, InterruptedException, ExecutionException, SQLException, InvalidTransactionAmountException {
		System.out.println("Entre Account iD: ");
		int id=Integer.parseInt(br.readLine());
		System.out.println("Enter the Amount to the dedited/withdraw: ");
		double amount=Double.parseDouble(br.readLine());
		Future<?> future=transactionService.withdraw(id, amount);
		future.get();
		System.out.println("Amount is withdrawn Successfully");
	}
//transfer funds
	private void TransferFunds() throws IOException, InterruptedException, ExecutionException{
	       System.out.println("Enter  From Account id:");
	       int from_id= Integer.parseInt(br.readLine());
	        System.out.println("Enter  To Account id:");
	       int to_id=Integer.parseInt(br.readLine());
	       System.out.println("Enter the amount to be transfered");
	       double amount=Double.parseDouble(br.readLine());
	       Future<?> future=transactionService.transferFunds(from_id,to_id,amount);
	       future.get();
	       System.out.println("Tranfer of Fund is done Successfully");
	       
	   }
//create	
	
	public void createAccount() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException, BankingException{
		System.out.println("Enter Customer ID: ");
		int cusId=Integer.parseInt(br.readLine());
		
		System.out.println("Enter Bank ID");
		int bankId=Integer.parseInt(br.readLine());
		
		Bank bank=bankService.getBankById(bankId);
		
		System.out.println("Enter Account Type(Savings/Current): ");
		String accountType=br.readLine();
		
		System.out.println("Enter Initial Balance: ");
		double bal=Double.parseDouble(br.readLine());
		
		if("Savings".equalsIgnoreCase(accountType)) {
			System.out.println("Enter Interset Rate: ");
			double interset=Double.parseDouble(br.readLine());
			accountService.createAccount(new SavingsAccount(0,cusId,bank,accountType,bal,interset));
		}
		else if("Current".equalsIgnoreCase(accountType)) {
			System.out.println("Enter OverdraftLimit: ");
			double overdraft=Double.parseDouble(br.readLine());
			accountService.createAccount(new CurrentAccount(0,cusId,bank,accountType,bal,overdraft));
		}
		else {
			System.out.println("Invalid Account Type");
		}
	}
//deposit	
	private void Deposit() throws NumberFormatException, IOException, InterruptedException, ExecutionException {
		System.out.println("Entre Account iD: ");
		int id=Integer.parseInt(br.readLine());
		System.out.println("Enter the Amount to the Deposited: ");
		double amount=Double.parseDouble(br.readLine());
		Future<?> future=transactionService.deposit(id, amount);
		future.get();
		System.out.println("Amount deposited Successfully");
	}
//update
	  public void updateAccount() throws NumberFormatException, IOException, SQLException, InvalidAccountTypeException, BankingException{
		    System.out.println("Enter Account ID to update: ");
		    int accountId = Integer.parseInt(br.readLine());

		    System.out.println("Enter the Customer ID:");
		    int custId = Integer.parseInt(br.readLine());

		    System.out.println("Enter Bank ID: ");
		    int bankId = Integer.parseInt(br.readLine());

		    // Fetch bank details using bankService
		    Bank bank = bankService.getBankById(bankId);

		    System.out.println("Enter New Balance: ");
		    double newBalance = Double.parseDouble(br.readLine());

		    System.out.println("Enter Account Type (Savings/Current): ");
		    String accountType = br.readLine();

		    if ("Savings".equalsIgnoreCase(accountType)) {
		        System.out.println("Enter New Interest Rate:");
		        double newInterest = Double.parseDouble(br.readLine());
		        
		        // Creating SavingsAccount instance and updating
		        accountService.updateAccount(new SavingsAccount(accountId, custId, bank, accountType, newBalance, newInterest));
		    } 
		    else if ("Current".equalsIgnoreCase(accountType)) {
		        System.out.println("Enter New Overdraft Limit:");
		        double newOverdraft = Double.parseDouble(br.readLine());
		        
		        // Creating CurrentAccount instance and updating
		        accountService.updateAccount(new CurrentAccount(accountId, custId, bank, accountType, newBalance, newOverdraft));
		    } 
		    else {
		        System.out.println("Invalid Account Type. Please enter 'Savings' or 'Current'.");
		    }
		}
//deleteAccount
		public void deleteAccount() throws SQLException, IOException, BankingException {
		    System.out.println("Enter Account ID to delete: ");
		    int accountId = Integer.parseInt(br.readLine());

		    // Call accountService to delete the account by ID
		    boolean isDeleted = accountService.deleteAccount(accountId);

		    if (isDeleted) {
		        System.out.println("Account deleted successfully.");
		    } else {
		        System.out.println("Account deletion failed. Account ID may not exist.");
		    }
		}
		
//View Account
		public void viewAccount() throws IOException, SQLException, InvalidAccountTypeException, BankingException, AccountnotFoundException{
			System.out.println("Enter the AccountID :");
			int accountId=Integer.parseInt(br.readLine());

			accountService.viewAccount(accountId);
			System.out.println("Account viewed Successfully!");
		  }//bankcontroller
	

}