package service;

import java.sql.SQLException;

import dao.AccountDAO;
import dao.AccountDAOImpl;
import dao.BankDAO;
import dao.BankDAOimpl;
import exception.BankingException;
import exception.InvalidAccountTypeException;
import model.Account;
import model.Bank;

//handling business logic

public class BankService {

	private BankDAO bankDAO;
	
	
	public BankService() {
		this.bankDAO=new BankDAOimpl();
		
		
	
	
	}
	
	public Bank getBankById(int bankId) throws SQLException, BankingException {
		// TODO Auto-generated method stub
		return bankDAO.getBankId(bankId);
	}
	
	
	
	
}
//upcasting or this is a type of polymorphism=>the parent class it can be any class that parent class object holds child class object**child class will override the parent class that is chid class will excecuted  

	//controller->service->dao->utility->