package dao;
//USED TO IMPLEMENT INTERFACE ACCOUNTDAO

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.AccountnotFoundException;
import exception.BankingException;
import exception.InvalidAccountTypeException;
import model.Account;
import utility.DBConnection;

public class AccountDAOImpl  implements AccountDAO{
public void createAccount(Account account) throws SQLException, InvalidAccountTypeException {//get all the details of the account from user and insert into table
		String sql="insert into Account(customer_id,bank_id,account_type,balance)values(?,?,?,?)";
		//to insert make connectivity in utility package
		try(Connection con=DBConnection.getConnection();
    PreparedStatement ps=con.prepareStatement(sql))//closing try block
		{
			ps.setInt(1,account.getCustomerId());//set values using ps statement
			ps.setInt(2,account.getBank().getBankId());
			ps.setString(3, account.getAccountType());
			ps.setDouble(4, account.getBalance());
				//to execute the query use execute update
			int result=ps.executeUpdate();
			if(result==0) {//an exection created so creta or throw exception invalidaccount created by us
				throw new InvalidAccountTypeException(""+"Account type not recognized");
			}
			

		}
		
		
	}
@Override
public void updateAccount(Account account) throws SQLException, InvalidAccountTypeException {
    String updateQuery = "UPDATE Account SET bank_id = ?, account_type = ?, balance = ? WHERE customer_id = ?";
    System.out.println("Preparing to update account for customer_id: " + account.getCustomerId());
    
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(updateQuery)) {
        
        ps.setInt(1, account.getBank().getBank_id());
        ps.setString(2, account.getAccountType());
        ps.setDouble(3, account.getBalance());
        ps.setInt(4, account.getCustomerId());
        
        int updateResult = ps.executeUpdate();
        if (updateResult > 0) {
            System.out.println("Account updated successfully.");
        } else {
            System.out.println("Update failed; no rows affected.");
        }
    }
}
@Override
public boolean deleteAccount(int accountId) throws SQLException {
    System.out.println("Attempting to delete account with account_id: " + accountId);
    String deleteQuery = "DELETE FROM Account WHERE account_id=?";
    try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(deleteQuery)) {
        ps.setInt(1, accountId);
        int delres = ps.executeUpdate();
        System.out.println("Delete result: " + delres);
        return delres > 0;
    }
}
public void viewAccount(int accountId) throws SQLException, AccountnotFoundException {
	String sql="select * from account where account_id = ?";
	try(Connection con = DBConnection.getConnection();
			PreparedStatement ps=con.prepareStatement(sql))
	{
		ps.setInt(1, accountId);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			System.out.println("Account ID: "+rs.getString("account_id")+"\n");
			System.out.println("Customer ID: "+rs.getString("customer_id")+"\n");
			System.out.println("Bank ID: "+rs.getString("bank_id")+"\n");
			System.out.println("Account Type: "+rs.getString("account_type")+"\n");
			System.out.println("Balance: "+rs.getString("balance")+"\n");
			
		}else {
			throw new AccountnotFoundException(""+"Account ID Invalid");
			
		}
	}
	
	
}
//AccountDAOImpl

}
