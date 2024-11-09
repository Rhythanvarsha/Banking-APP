package dao;

import java.sql.SQLException;

import exception.InvalidTransactionAmountException;
import exception.TransactionFailureException;

public interface TransactionDAO {
 void deposit(int accountId,double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException;
 void withdraw(int accountId,double amount) throws InvalidTransactionAmountException, SQLException, TransactionFailureException;
 void transferFunds(int from_account,int to_account,double amount) throws InvalidTransactionAmountException,TransactionFailureException;
}
