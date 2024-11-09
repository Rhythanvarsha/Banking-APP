package service;

import java.sql.SQLException;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import exception.AccountnotFoundException;
import exception.InvalidAccountTypeException;
import model.Account;
import model.CurrentAccount;
//his packag eis used o handle business logic
public class AccountService {
	private final AccountDAO accountDAO;
	
	public AccountService() {
		this.accountDAO=new AccountDAOImpl();//upcasting the parent class object can hold child class obj using which we can override child class->using this child class method will execute called method overide parent class
	}
	public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {
		accountDAO.createAccount(account);
	}
	public void updateAccount(Account account)throws SQLException, InvalidAccountTypeException {
		// TODO Auto-generated method stub
		accountDAO.updateAccount(account);
	}
	public boolean deleteAccount(int accountId) throws SQLException {
	    return accountDAO.deleteAccount(accountId);
	}
	public void viewAccount(int accountId) throws SQLException, InvalidAccountTypeException, AccountnotFoundException{
        accountDAO.viewAccount(accountId);
    }    //AccountService
    
}
//controoler->servieec->dao ->service->controller
