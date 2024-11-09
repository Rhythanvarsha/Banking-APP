//to get particluar bank using the bank_id
//	//if ther eare ?use prepared statement
//	//returned value from select is stored in result set
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.BankingException;
import model.Account;
import model.Bank;
import utility.DBConnection;

public class BankDAOimpl implements BankDAO {

	@Override
	public Bank getBankId(int bankId) throws SQLException, BankingException {
String query="select * from Bank  where bank_id=?";
		
		try(Connection con=DBConnection.getConnection();PreparedStatement ps=con.prepareStatement(query)){
			ps.setInt(1, bankId);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				return new Bank(bankId,rs.getString("bank_name"),rs.getString("bank_branch"));
			}
			throw new BankingException("Bank not found for ID: "+bankId);
			
		}
	}
	

}