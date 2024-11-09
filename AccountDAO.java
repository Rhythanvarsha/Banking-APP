package dao;//DATA ACCESS OBJEX=CT

import java.sql.SQLException;

import exception.AccountnotFoundException;
import exception.InvalidAccountTypeException;
import model.Account;//to acces to model package account class

/*HERE IN INTERFACE WE MAY OR MAY NOT MENTION METHODS  AS ABSTRACT
*/
public interface AccountDAO  {
void createAccount(Account account) throws SQLException, InvalidAccountTypeException;//using account object we access all the method in account class

void updateAccount(Account account) throws SQLException, InvalidAccountTypeException;

boolean deleteAccount(int accountId) throws SQLException;

void viewAccount(int accountId)throws SQLException,InvalidAccountTypeException, AccountnotFoundException;    //AccountDAO

}
